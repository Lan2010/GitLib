package com.tianzhixing.app.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.tianzhixing.app.common.ReturnCode;
import com.tianzhixing.app.component.grpc.GrpcConnection;
import com.tianzhixing.app.component.nats.NatsComponent;
import com.tianzhixing.app.dao.mapper.ChargeMapper;
import com.tianzhixing.app.dao.mapper.UserMapper;
import com.tianzhixing.app.exception.GrpcException;
import com.tianzhixing.app.pojo.ChargeInfo;
import com.tianzhixing.app.pojo.ResultMessage;
import com.tianzhixing.app.pojo.User;
import com.tianzhixing.app.service.ChargeService;
import com.tianzhixing.app.service.StarService;
import com.tianzhixing.app.service.UserService;
import com.tianzhixing.app.service.impl.grpc.DeviceOperation;
import com.tianzhixing.app.util.CommonUtils;
import com.tianzhixing.kernel.grpc.proto.ResponseEntity;
import com.tianzhixing.kernel.grpc.proto.device.operation.Result;

@Service
public class ChargeServiceImpl implements ChargeService {
	private static Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);
	@Resource
	private GrpcConnection grpcConnection;
	@Resource
	private UserService userService;
	@Resource
	private StarService starService;
	@Resource
	private DeviceOperation deviceOperation;

	@Resource
	private ChargeMapper chargeMapper;
	@Resource
	private UserMapper userMapper;
	@Resource
	public NatsComponent natsComponent;

	@Override
	public ResultMessage bind(HttpServletRequest request, Integer userId, String mac, Short mt)
			throws SQLException, GrpcException {
		if (StringUtils.isEmpty(mac)) {
			return new ResultMessage(ReturnCode.FAILED, "扫码信息不对！");
		}
		String devid = GetParam(mac, "devid");
		if (StringUtils.isEmpty(devid)) {
			return new ResultMessage(ReturnCode.FAILED, "扫码信息不对！");
		}
		ChargeInfo charge_info = chargeMapper.getBindInfo(devid);
		if (charge_info == null) {
			return new ResultMessage(ReturnCode.FAILED, "扫码信息不对！");
		}
		if (charge_info.getState() == 2) { // 当为绑定状态的时候
			if (String.valueOf(userId).equals(charge_info.getBind_user_id())) {
				return new ResultMessage(ReturnCode.FAILED, "您已经绑定该充电宝");
			} else {
				return new ResultMessage(ReturnCode.FAILED, "该充电宝已经被别人绑定");
			}
		}
		User user = userService.getUserById(userId);
		if (user == null) {
			return new ResultMessage(ReturnCode.FAILED, "用户信息异常！");
		}
		String device_id = charge_info.getDevice_id();
		String device_type = charge_info.getDevice_type();
		String device_mac = charge_info.getDevice_mac();
		String device_model = charge_info.getDevice_model();

		if (!StringUtils.isEmpty(user.getUserToken()) && !StringUtils.isEmpty(device_id)
				&& !StringUtils.isEmpty(device_type) && !StringUtils.isEmpty(device_mac)
				&& !StringUtils.isEmpty(device_model)) {
			Result result = deviceOperation.bind2Grpc(user.getUserToken(), device_id, device_type, device_mac,
					device_model);
			ResponseEntity responseEntity = result.getResponseEntity();
			Integer code = responseEntity.getCode();
			String message = responseEntity.getMessage();
			if (code != 200) {
				ResultMessage resultMessage = new ResultMessage(ReturnCode.FAILED, message);
				return resultMessage;
			}
		}
		ChargeInfo chargeInfo = new ChargeInfo();
		chargeInfo.setBind_user_id(String.valueOf(userId));
		chargeInfo.setBind_datetime(String.valueOf(System.currentTimeMillis() / 1000));
		chargeInfo.setDevice_id(devid);
		chargeInfo.setOp_type("bind");
		chargeInfo.setState((short) 2);

		Integer result01 = chargeMapper.chargeOpLog(chargeInfo); // 记入日志
		Integer result02 = chargeMapper.updateBindInfo(chargeInfo); // 绑定
		if (result02 <= 0) {
			return new ResultMessage(ReturnCode.FAILED, "绑定失败");
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("module_code", "Charge");
		map.put("status", 1);
		Integer result03 = userMapper.addModule(map); // 添加用户的认证标识
		awardnat(mt); // nats推送
		if (!StringUtils.isEmpty(user.getUserToken())) {
			ResultMessage ret = starService.award(user.getUserToken(), "HARDWAREBIND");// grpc 增加奖励积分
		}
		bindOrUnbindnat(request, mt, user.getPhone(), charge_info, 1); // nats 推送 运营平台
		return new ResultMessage(ReturnCode.OK, "绑定成功");
	}

	// 是否绑定nats推送
	private void bindOrUnbindnat(HttpServletRequest request, Short mt, String phone, ChargeInfo charge_info,
			Integer operationType) {
		JSONObject publish = new JSONObject();
		publish.put("mobile", StringUtils.isEmpty(phone) ? "" : phone);
		publish.put("deviceId", StringUtils.isEmpty(charge_info.getDevice_id()) ? "" : charge_info.getDevice_id());
		publish.put("deviceType",
				StringUtils.isEmpty(charge_info.getDevice_type()) ? "" : charge_info.getDevice_type());
		publish.put("deviceMac", StringUtils.isEmpty(charge_info.getDevice_mac()) ? "" : charge_info.getDevice_mac());
		publish.put("deviceModel",
				StringUtils.isEmpty(charge_info.getDevice_model()) ? "" : charge_info.getDevice_model());
		publish.put("deviceOperType",
				StringUtils.isEmpty(charge_info.getDevice_type()) ? "" : charge_info.getDevice_type());
		publish.put("operationTime", System.currentTimeMillis());
		publish.put("operationType", operationType);// 必填(0=解除绑定，1=绑定)

		publish.put("id", null);
		publish.put("createTime", System.currentTimeMillis());
		publish.put("platformFrom", "WKAPP");
		publish.put("clientPlatformType", (mt == 3) ? "IOS" : "ANDROID");
		try {
			publish.put("deviceIp", CommonUtils.getIpAddr(request));
		} finally {
			natsComponent.publish4oms("oms.subject.device.bind-unbind", publish.toJSONString());
		}
	}

	// nats推送
	private void awardnat(Short mt) {
		JSONObject publish = new JSONObject();
		publish.put("authType", "HARDWAREBIND");
		publish.put("authStatus", 1);// 必填(0=失败，1=成功)
		publish.put("authTime", System.currentTimeMillis());
		publish.put("id", null);
		publish.put("createTime", System.currentTimeMillis());
		publish.put("platformFrom", "WKAPP");
		publish.put("clientPlatformType", (mt == 3) ? "IOS" : "ANDROID");
		natsComponent.publish4oms("oms.subject.user.auth", publish.toJSONString());

	}

	// 获取到参数
	private static String GetParam(String url, String id) {
		String[] a = url.split("\\?");
		if (a == null) {
			return null;
		}
		String params = a[1];
		if (!StringUtils.isEmpty(params)) {
			String[] param = params.split("\\&");
			for (String str : param) {
				String[] arr = str.split("\\=");
				String key = arr[0];
				String value = arr[1];
				if (key.equals(id)) {
					return value;
				}
			}
		}
		return null;
	}

	@Override
	public ResultMessage unbindCharge(Integer userId, String mac) throws SQLException {
		List<ChargeInfo> chargeList = chargeMapper.getInfoByUser(userId);
		if (chargeList.isEmpty()) {
			return new ResultMessage(ReturnCode.FAILED, "您尚未绑定任何矿机");
		}
		StringBuilder macs = new StringBuilder();
		ChargeInfo chargeInfo = new ChargeInfo();

		if (StringUtils.isEmpty(mac)) {
			for (ChargeInfo chargeInfos : chargeList) {
				macs.append(chargeInfos.getDevice_id()).append(" ");
			}
		} else {
			macs.append(mac);
			chargeInfo.setDevice_mac(macs.toString());
		}

		chargeInfo.setBind_user_id(String.valueOf(userId));
		chargeInfo.setBind_datetime(String.valueOf(System.currentTimeMillis() / 1000));
		chargeInfo.setOp_type("unbind");
		chargeInfo.setState((short) 1);
		Integer result01 = chargeMapper.chargeOpLog(chargeInfo); // 记入日志
		chargeInfo.setDevice_id(mac);
		Integer result02 = chargeMapper.updateBindInfo(chargeInfo);
		if (result02 <= 0) {
			return new ResultMessage(ReturnCode.FAILED, "解绑失败");
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("userId", userId);
			map.put("module_code", "Charge");
			map.put("status", 0);
			Integer result03 = userMapper.updateModule(map); // 修改用户的认证标识
			return new ResultMessage(ReturnCode.OK, "解绑成功");
		}
	}

	@Override
	public ResultMessage chargeList(Integer userId) throws SQLException {
		List<ChargeInfo> chargeList = chargeMapper.getInfoByUser(userId);
		if (chargeList.isEmpty()) {
			return new ResultMessage(ReturnCode.FAILED, "您尚未绑定任何矿机");
		}
		ResultMessage resultMessage = new ResultMessage(ReturnCode.OK, "成功");
		resultMessage.put("info", chargeList);
		return resultMessage;
	}
}
