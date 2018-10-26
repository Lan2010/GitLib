package com.tianzhixing.app.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tianzhixing.app.common.AwardStarModule;
import com.tianzhixing.app.common.Constant;
import com.tianzhixing.app.common.ReturnCode;
import com.tianzhixing.app.component.grpc.GrpcConnection;
import com.tianzhixing.app.component.nats.NatsComponent;
import com.tianzhixing.app.dao.mapper.AwardStarTypeMapper;
import com.tianzhixing.app.dao.mapper.ChargeMapper;
import com.tianzhixing.app.dao.mapper.PhoneListMapper;
import com.tianzhixing.app.dao.mapper.StarModuleMapper;
import com.tianzhixing.app.dao.mapper.UserInviteMapper;
import com.tianzhixing.app.dao.mapper.UserMapper;
import com.tianzhixing.app.exception.GrpcException;
import com.tianzhixing.app.pojo.AwardStarType;
import com.tianzhixing.app.pojo.BaseRequest;
import com.tianzhixing.app.pojo.ChargeInfo;
import com.tianzhixing.app.pojo.FileInfo;
import com.tianzhixing.app.pojo.PhoneList;
import com.tianzhixing.app.pojo.RecordsWithDay;
import com.tianzhixing.app.pojo.ResultMessage;
import com.tianzhixing.app.pojo.StarModule;
import com.tianzhixing.app.pojo.StarRecord;
import com.tianzhixing.app.pojo.User;
import com.tianzhixing.app.service.StarService;
import com.tianzhixing.app.service.UserLogService;
import com.tianzhixing.app.service.UserService;
import com.tianzhixing.app.service.impl.grpc.AccountReg;
import com.tianzhixing.app.service.impl.grpc.AccountStarPoint;
import com.tianzhixing.app.service.impl.grpc.SendSms;
import com.tianzhixing.app.util.CommonUtils;
import com.tianzhixing.app.util.EncryptUtil;
import com.tianzhixing.app.util.FileUtil;
import com.tianzhixing.app.util.RedisUtil;
import com.tianzhixing.app.util.TokenUtil;
import com.tianzhixing.auth.grpc.proto.mobile.ValidationResult;
import com.tianzhixing.auth.grpc.proto.mobile.ValidationResult.Status;
import com.tianzhixing.kernel.grpc.proto.ResponseEntity;
import com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult;
import com.tianzhixing.kernel.grpc.proto.account.reg.Result;
import com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayInfo;
import com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayResult;

@Service
public class UserServiceImpl implements UserService {
	private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	@Resource
	private UserMapper userMapper;
	@Resource
	private StarModuleMapper starModuleMapper;
	@Resource
	private UserInviteMapper userInviteMapper;
	@Resource
	private PhoneListMapper phoneListMapper;
	@Resource
	private AwardStarTypeMapper awardStarTypeMapper;
	@Resource
	private ChargeMapper chargeMapper;
	@Resource
	private UserLogService userLogService;
	@Resource
	private StarService starService;
	@Resource
	private NatsComponent natsComponent;
	@Resource
	private GrpcConnection grpcConnection;
	@Resource
	private AccountStarPoint accountStarPoint;
	@Resource
	private SendSms sendSms;
	@Resource
	private AccountReg accountReg;

	@Override
	public ResultMessage login(User user, BaseRequest baseRequest) throws SQLException {
		ResultMessage rm = new ResultMessage(ReturnCode.OK, "登录成功");// 默认返回code=0
		if (user.getPhone() == null || user.getPhone().isEmpty()) {
			rm = new ResultMessage(ReturnCode.FAILED, "参数错误");
			return rm;
		}
		String lock = "Login_Lock_" + user.getPhone();// 登录锁，超过5次登录失败，则锁定用户两个小时，不可以再登录
		Integer times = 0;
		if (user.getRegTerminal() > 5 || user.getRegTerminal() < 1) { // 接口传递的终端编号有问题？
			rm = new ResultMessage(ReturnCode.FAILED, "参数错误");
			return rm;
		}
		if (RedisUtil.getString(lock) != null) {
			times = Integer.valueOf(RedisUtil.getString(lock));
			if (times != null && times > 5) { // 判断是否为锁定
				rm = new ResultMessage(ReturnCode.FAILED, "登录错误次数过多还在锁定中！");
				return rm;
			}
		}
		user.setPassword(
				EncryptUtil.encodeByMD5(EncryptUtil.encodeByMD5(user.getPassword()) + Constant.LOGIN_SECRETKEY));
		User result = userMapper.userLogin(user);// 用户登录
		if (result != null && result.getUserId() != null) {
			if (times > 0) {
				RedisUtil.del(lock);// 删除redis中“锁定登录”的键值
			}
			// 添加登录日志
			userLogService.addLoginLog(result.getUserId(), user.getPhone(), user.getRegIp(), "用户登录", "登录成功",
					user.getRegTerminal());
			user.setUserId(result.getUserId());
			Map<String, Object> userInfo = setUserInfo(user, "PassWordLogin", baseRequest);
			rm.put("info", userInfo);
			return rm;
		} else {
			userLogService.addLoginLog(null, user.getPhone(), user.getRegIp(), "用户登录", "登录失败", user.getRegTerminal());
			if (times != null && times < 6) {
				RedisUtil.set(lock, times + 1 + "", 7200);
				rm = new ResultMessage(ReturnCode.FAILED, "抱歉您还有" + (5 - times) + "次登录机会,超出将锁定两个小时!");
				return rm;
			} else {
				RedisUtil.set(lock, "1", 7200);
				rm = new ResultMessage(ReturnCode.FAILED, "抱歉请检查您的登录名和密码是否正确！");
				return rm;
			}
		}
	}

	@Override
	public User getByPhone(String phone) throws SQLException {
		return userMapper.getByPhone(phone);
	}

	@Override
	public ResultMessage smsLogin(User user, BaseRequest baseRequest) throws SQLException, GrpcException {
		ResultMessage rm = smsValidate(user.getSmsCode(), user.getPhone(), "login");
		User temp = getByPhone(user.getPhone());
		if (temp != null && ReturnCode.OK.equals(rm.getCode())) {
			// 添加登录日志
			userLogService.addLoginLog(temp.getUserId(), user.getPhone(), user.getRegIp(), "用户登录", "登录成功",
					user.getRegTerminal());
			user.setUserId(temp.getUserId());
			Map<String, Object> userInfo = setUserInfo(user, "smsLogin", baseRequest);
			rm.put("info", userInfo);
		} else {
			userLogService.addLoginLog(null, user.getPhone(), user.getRegIp(), "用户登录", "登录失败", user.getRegTerminal());
			rm = new ResultMessage(ReturnCode.FAILED, "抱歉请检查您的登录名和验证码是否正确！");
		}

		return rm;
	}

	@Override
	public ResultMessage smsValidate(String smsCode, String phone, String useType) throws GrpcException {
		if (smsCode == null || smsCode.isEmpty()) {
			return new ResultMessage(1, "请填写验证码");
		}
		if (phone == null || phone.isEmpty() || !CommonUtils.validatePhone(phone)) {
			return new ResultMessage(1, "手机号不正确");
		}
		String key = phone + "_" + useType;
		String smsInfo = RedisUtil.getString(key);
		if (smsInfo == null || smsInfo.isEmpty()) {
			return new ResultMessage(1, "手机短信验证码已过期");
		}
		JSONObject json = JSON.parseObject(smsInfo);
		String codeToken = json.getString("codeToken");
		ValidationResult result = sendSms.sendValiSms2Grpc(phone, codeToken, smsCode);
		if (result == null) {
			return new ResultMessage(1, "验证码错误");
		}
		
		if (result.getCode() == 200 && (Status) result.getStatus() == Status.SUC) {
			return new ResultMessage(ReturnCode.OK, "");
		} else {
			return new ResultMessage(result.getCode(), result.getMessage());
		}
	}

	public static void main(String[] args) {
		System.out.println(Status.SUC==com.tianzhixing.auth.grpc.proto.mobile.ValidationResult.Status.valueOf(1));
	}
	/**
	 * 获取用户的登录信息
	 * 
	 * @param user
	 * @param baseRequest
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> getLoginInfo(User user, String loginType, BaseRequest baseRequest) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		long now = System.currentTimeMillis() / 1000;
		if (baseRequest.getToken() != null && !baseRequest.getToken().trim().isEmpty()) {
			map.put("token", baseRequest.getToken());
		} else {
			String token = TokenUtil.getToken();
			map.put("token", token);
		}
		User temp = getUserById(user.getUserId());
		map.put("phone", user.getPhone());
		map.put("device_id", baseRequest.getD());
		map.put("login_time", now);
		map.put("expires_time", now + Constant.SESSION_EXPIRES_TIME);// 1个月的过期时间(单位：毫秒)
		map.put("operate_ip", user.getRegIp());
		map.put("login_type", loginType);
		map.put("terminal", user.getRegTerminal());
		map.put("user_id", user.getUserId());
		map.put("user_key", temp != null ? temp.getUserKey() : "");
		return map;
	}

	@Override
	public Map<String, Object> setUserInfo(User user, String loginType, BaseRequest baseRequest) throws SQLException {
		Map<String, Object> loginInfo = getLoginInfo(user, loginType, baseRequest);
		userLogService.loginSucceedCallback(loginInfo);
		String token = (String) loginInfo.get("token");
		Set<String> keys = RedisUtil.keys("APP_Login_" + "*" + "_" + user.getUserId());
		RedisUtil.dels(keys);
		RedisUtil.set("APP_Login_" + token + "_" + user.getUserId(), JSON.toJSONString(loginInfo),
				Constant.SESSION_EXPIRES_TIME);
		removeAttr(loginInfo);
		return loginInfo;
	}

	private void removeAttr(Map<String, Object> map) {
		map.remove("user_id");
		map.remove("login_type");
		map.remove("operate_ip");
		map.remove("terminal");
		map.remove("equipment");
	}

	@Override
	public JSONObject getStarStatus(Integer userId) throws SQLException {
		List<AwardStarType> awards = awardStarTypeMapper.getAwardStarType(null);
		List<StarModule> list = starModuleMapper.getStarModules(userId);
		JSONObject all_status = new JSONObject();
		for (AwardStarType a : awards) {
			all_status.put(a.getType_code(), 0);
			for (StarModule starModule : list) {
				if (a.getType_code().equals(starModule.getModuleCode()) && starModule.getStatus() == 1) {
					all_status.put(a.getType_code(), 1);
				}
			}
		}
		ChargeInfo charge = chargeMapper.getByUserId(userId);
		String mac = null;
		if (charge == null) {
			mac = "";
		} else {
			mac = charge.getDevice_mac() != null ? charge.getDevice_mac() : "";
		}
		all_status.put("charge_mac", mac);// 用户绑定的充电宝的mac地址
		return all_status;
	}

	@Override
	public Integer getOneModule(AwardStarModule awardStarModule, Integer userId) throws SQLException {
		return starModuleMapper.getOneModule(awardStarModule.name(), userId);
	}

	@Override
	public void pushLogin2Nats(User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		String subject = "oms.subject.user.auth";
		map.put("id", CommonUtils.randomID(8));
		map.put("mobile", user.getPhone());
		map.put("operationTime", System.currentTimeMillis());
		map.put("operationType", 1);//
		map.put("wxID", "");//
		map.put("qqID", "");//
		map.put("sinaWeiBoID", "");//
		map.put("ip", user.getRegIp());//
		natsComponent.publish4oms(subject, JSON.toJSONString(map));
	}

	@Override
	public void pushRegister2Nats(String ip, Short terminal, String phone) {
		Map<String, Object> map = new HashMap<String, Object>();
		String subject = "oms.subject.user.basic.info";
		map.put("id", CommonUtils.randomID(8));
		map.put("mobile", phone);
		map.put("userFromType", "RANDOM");// 必填, 用户来源[非渠道数据填写：RANDOM， 其他渠道，如推广，则填写推广渠道设置的名称-英文]
		map.put("userOperType", "reg");//
		map.put("wxID", "");//
		map.put("qqID", "");//
		map.put("sinaWeiBoID", "");//
		map.put("platformFrom", "WKAPP");//
		map.put("clientPlatformType", terminal == 3 ? "IOS" : "ANDROID");//
		map.put("createTime", System.currentTimeMillis());//
		map.put("regTime", System.currentTimeMillis());// 注册时间
		map.put("ip", ip);//
		natsComponent.publish4oms(subject, JSON.toJSONString(map));
	}

	@Override
	public Long logout(HttpServletRequest request, BaseRequest baseRequest) {
		String key = "APP_Login_" + baseRequest.getToken() + "_" + request.getAttribute("user_id");
		Long result = RedisUtil.del(key);
		if (result > 0) {
			log.info("关键字索引：loginOut,内容：删除key->" + key + "成功");
		} else {
			log.warn("关键字索引：loginOut,内容：删除key->" + key + "失败");
		}
		return result;
	}

	@Override
	public void pushLogout2Nats(HttpServletRequest request) {
		Integer user_id = (Integer) request.getAttribute("user_id");
		String subject = "oms.subject.user.auth";
		User user;
		try {
			user = getUserById(user_id);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", CommonUtils.randomID(8));
			map.put("mobile", user.getPhone() != null ? user.getPhone() : "");
			map.put("operationType", 0);
			map.put("operationTime", System.currentTimeMillis());
			map.put("ip", CommonUtils.getIpAddr(request));
			natsComponent.publish4oms(subject, JSON.toJSONString(map));
		} catch (SQLException e) {
			log.error(" pushLogout2Nats SQLException.", e);
			e.printStackTrace();

		}
	}

	@Override
	public void pushUserAuth2Nats(Short terminal, String authType, short isSuccess) {
		String subject = "oms.subject.user.auth";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", CommonUtils.randomID(8));
		map.put("createTime", System.currentTimeMillis());
		map.put("platformFrom", "WKAPP");
		map.put("clientPlatformType", terminal == 3 ? "IOS" : "ANDROID");
		map.put("authType", authType == null ? "" : authType);// 必填,
																// [取值范围：AUTHIDCARD-实名认证，GENE-基因数据，ADDRESS-添加地址，ATTENTIONWEBCHAT-关注公众号，VOICEDISCERN-声音识别，FACEDEISCEERN-人脸识别，BINDBANK-绑定银行卡，HARDWAREBIND-绑定矿机,
																// BINDADDRESSLIST=绑定通讯录]
		map.put("authStatus", isSuccess);// 认证成功/失败
		map.put("authTime", System.currentTimeMillis());// 认证成功
		natsComponent.publish4oms(subject, JSON.toJSONString(map));
	}

	@Override
	public User getUserByToken(String accountToken) throws SQLException {
		return userMapper.getUserByToken(accountToken);
	}

	@Override
	public User getUserById(Integer userId) throws SQLException {
		User user = userMapper.getUserById(userId);
		if (user != null) {
			user.setPassword("");
		}
		return user;
	}

	@Override
	public ResultMessage register(User user, BaseRequest baseRequest) throws SQLException, GrpcException {
		User temp = userMapper.getByPhone(user.getPhone());
		ResultMessage rm = new ResultMessage(ReturnCode.OK, "恭喜您，注册成功");
		if (temp != null) {
			rm = new ResultMessage(1, "该手机号码已经注册");
			return rm;
		}
		String msg = CommonUtils.validPass(user.getPassword());
		if (msg != null) {
			rm = new ResultMessage(1, msg);
			return rm;
		}
		// int rand = (int)(0+Math.random()*32768);
		String userKey = EncryptUtil.encodeByMD5(UUID.randomUUID().toString());
		user.setPayPassword(EncryptUtil.encodeByMD5(user.getPassword()));
		user.setPassword(
				EncryptUtil.encodeByMD5(EncryptUtil.encodeByMD5(user.getPassword()) + Constant.LOGIN_SECRETKEY));// 设置登录密码
		user.setUserKey(userKey);// 设置用户编码，随机字符的MD5值
		user.setPhoneStatus((short) 1);
		user.setRegDatetime(System.currentTimeMillis() / 1000);
		int result = userMapper.addUser(user);// 新增用户

		if (result > 0) {
			temp = userMapper.getByPhone(user.getPhone());
			if (temp == null) {
				rm = new ResultMessage(1, "发生未知错误");
				return rm;
			}
			Integer userId= temp.getUserId();
			String userToken = "";
			User inviter = null;
			if (user.getInvitationCode() != null && !user.getInvitationCode().isEmpty()) {
				inviter = getUserByInviter(user.getInvitationCode());
				if (inviter != null) {
					userToken = inviter.getUserToken();
					// 处理邀请
					handlInvite(userId, inviter.getUserId(), user.getInvitationCode());
				}
			}
			// 发送注册信息到grpc服务器
			Result res = accountReg.sendReg2Grpc(userId, user.getPhone(), userToken);
			ResponseEntity resp = res.getResponseEntity();
			if (resp != null) {
				String accountToken = res.getAccountToken();
				int code = resp.getCode();
				if (code == 501) {/// 表示重新注册, 查询一遍，重新在本地注册
					// 向grpc服务器校验手机号
					CheckMobileResult checkMobileResult = accountReg.sendCheckMobile2Grpc(user.getPhone());
					accountToken = checkMobileResult.getAccountToken();// accountToken重新赋值
				}
				if (accountToken == null) {// 向grpc服务端注册用户失败则删除刚创建的用户
					userMapper.delUser(userId);
					rm = new ResultMessage(1, "注册失败");
					return rm;
				}
				User temp2 = new User();
				temp2.setUserToken(accountToken);
				temp2.setUserId(userId);
				userMapper.updateUser(temp2);
			} else {
				rm = new ResultMessage(1, "注册失败");
				return rm;
			}
			user.setUserId(userId);
			Map<String, Object> userInfo = setUserInfo(user, "RegisterLogin", baseRequest);
			rm.put("info", userInfo);
			return rm;
		} else {
			rm = new ResultMessage(1, "注册失败");
			return rm;
		}
	}
	
	@Override
	public Integer addModule(Integer userId, AwardStarModule awardStarModule) throws SQLException {
		Integer status = starModuleMapper.getOneModule(awardStarModule.name(), userId);
		if (status != null) {// 已经绑定过通讯录
			// 已经存在该条记录
			return 0;
		}
		return starModuleMapper.addModule(userId, awardStarModule.name());
	}

	// public static void main(String[] args) {
	// System.out.println(EncryptUtil.encodeByMD5(EncryptUtil.encodeByMD5("123456")
	// + Constant.LOGIN_SECRETKEY));
	// System.out.println(EncryptUtil.encodeByMD5("123456"));
	// }

	@Override
	public User getUserByInviter(String inviteCode) {
		User user = userMapper.getUserByInviter(inviteCode.toUpperCase());
		return user;
	}

	/**
	 * 处理邀请信息
	 * 
	 * @param userId        被邀请人ID
	 * @param inviterUserId 邀请人ID
	 * @param inviteCode    邀请码
	 */
	public void handlInvite(Integer userId, Integer inviterUserId, String inviteCode) {
		if (userId == null || inviteCode == null)
			return;
		if (inviterUserId != null) {
			userMapper.addInviter(inviterUserId, userId, System.currentTimeMillis()/1000);
		}
	}

	@Override
	public List<PhoneList> getPhoneLists(Integer userId) throws SQLException {
		return getPhoneLists(null, userId);
	}

	@Override
	public List<PhoneList> getPhoneLists(Integer registerType, Integer userId) throws SQLException {
		return phoneListMapper.getPhoneLists(registerType, userId);
	}

	@Override
	public int addPhoneLists(List<PhoneList> phonesAdd) throws SQLException {
		return phoneListMapper.addPhoneLists(phonesAdd);
	}

	@Override
	public int delPhoneLists(Integer userId) throws SQLException {
		if (userId == null) {
			return 0;
		}
		return phoneListMapper.delPhoneLists(userId);
	}

	@Override
	public Integer getInviteCount(Integer userId) throws SQLException {
		if (userId == null) {
			return 0;
		}
		return userInviteMapper.getInviteCount(userId, null, null);
	}

	@Override
	public void updateInviteCode(Integer userId, String inviteCode) throws SQLException {
		User user = new User();
		user.setUserId(userId);
		user.setInvitationCode(inviteCode);
		updateUser(user);
	}

	@Override
	public Integer updateUser(User user) throws SQLException {
		user.setEditDatetime(System.currentTimeMillis() / 1000);
		user.setUserStatus(1);
		return userMapper.updateUser(user);
	}

	@Override
	public boolean verifyLoginPassword(Integer userId, String oldPwd) throws SQLException {
		User user = userMapper.getUserById(userId);
		String encryptPwd = EncryptUtil.encodeByMD5(EncryptUtil.encodeByMD5(oldPwd) + Constant.LOGIN_SECRETKEY);
		if (encryptPwd.equals(user.getPassword())) {
			return true;
		}
		return false;
	}

	@Override
	public String editLoginPassword(Integer userId, String oldPwd, String newPwd) throws SQLException {
		String msg = CommonUtils.validPass(newPwd);// 检验密码复杂度
		if (msg != null) {
			return msg;
		}
		User user = new User();
		user.setUserId(userId);
		user.setPassword(EncryptUtil.encodeByMD5(EncryptUtil.encodeByMD5(newPwd) + Constant.LOGIN_SECRETKEY));
		int result = updateUser(user);
		if (result > 0) {
			return null;
		} else {
			return "修改失败";
		}
	}

	@Override
	public ResultMessage getWithDayList(Integer userId, int days, String endTime)
			throws ParseException, SQLException, GrpcException {
		User user = getUserById(userId);
		if (user == null) {
			return new ResultMessage(ReturnCode.FAILED, "用户信息异常！");
		}
		// 获取到当前日期的星星流水
		RecordsWithDayResult result = accountStarPoint.recordsWithDay(user.getUserToken(), days, endTime);
		ResponseEntity responseEntity = result.getResponseEntity();
		Integer code = responseEntity.getCode();
		String message = responseEntity.getMessage();
		if (code != 200) {
			ResultMessage resultMessage = new ResultMessage(ReturnCode.FAILED, message);
			return resultMessage;
		}
		List<RecordsWithDayInfo> list = result.getRecordsList();
		List<RecordsWithDay> newlist = new ArrayList<RecordsWithDay>();
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		int month = now.get(Calendar.MONTH) + 1;
		int day = now.get(Calendar.DATE);
		if (list.isEmpty()) {
			ResultMessage ret = new ResultMessage();
			ret.setCode(ReturnCode.OK);
			ret.setMsg("");
			Map<String, Object> emptyinfo = new HashMap<String, Object>();
			emptyinfo.put("starPoint", 0);
			emptyinfo.put("day", month + "-" + day);
			ret.put("info", emptyinfo);
			return ret;
		}
		String date = list.get(0).getDate(); // 日期(yyyyMMdd)
		String starttime = year + "-" + date.substring(4, 6) + "-" + date.substring(6, 8);
		String endtime = year + "-" + month + "-" + day;
		int key = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat MD = new SimpleDateFormat("MM-dd");
		Date startdate = sdf.parse(starttime);
		Date enddate = sdf.parse(endtime);
		while (startdate.before(enddate)) {
			if (key >= list.size()) {
				newlist.add(key, new RecordsWithDay(newlist.get(key - 1).getStarPoint(), MD.format(startdate)));
			} else {
				String newDate = list.get(key).getDate().substring(4, 6) + "-"
						+ list.get(key).getDate().substring(6, 8);
				newlist.add(key, new RecordsWithDay(list.get(key).getStarPoint(), newDate));
			}

			Calendar c = Calendar.getInstance();
			c.setTime(startdate);
			c.add(Calendar.DAY_OF_MONTH, 1);// 每新增一天
			startdate = c.getTime();
			key++;
			if (startdate.equals(enddate)) {
				if (key <= list.size() - 1) {
					String newDate = list.get(key).getDate().substring(4, 6) + "-"
							+ list.get(key).getDate().substring(6, 8);
					newlist.add(key, new RecordsWithDay(list.get(key).getStarPoint(), newDate));
				} else {
					newlist.add(key, new RecordsWithDay(newlist.get(key - 1).getStarPoint(), MD.format(startdate)));
				}
			}
		}
		ResultMessage resultMessage = new ResultMessage(ReturnCode.OK, message);
		resultMessage.put("info", newlist);
		return resultMessage;
	}

	@Override
	public ResultMessage getUserCenterStar(Integer userId) throws SQLException {
		List<AwardStarType> asList = getStarModules(userId);
		AwardStarType module = awardStarTypeMapper.getOneModuleByCode("Invitation");
		StarRecord s1 = new StarRecord("AuthAll", "实名认证", "10", -1);
		StarRecord s2 = new StarRecord("Gene", "数字生命", "10", 1);
		StarRecord s3 = new StarRecord("Invitation", "邀请好友", Integer.toString(module.getType_value().intValue()), -1);
		List<StarRecord> list = new ArrayList<StarRecord>();
		list.add(s1);
		list.add(s2);
		list.add(s3);
		for (StarRecord starRecord : list) {
			for (AwardStarType v : asList) {
				if (starRecord.getType().equals(v.getType_code())) {
					starRecord.setValue(Integer.toString(v.getType_value().intValue()));
					starRecord.setStatus(v.getStatus());
				}
				if (starRecord.getType().equals("AuthAll")) {
					Integer value = getAllAuthStar(userId);
					starRecord.setValue(String.valueOf(value));
				}
				if (starRecord.getType().equals("Gene")) {
					starRecord.setStatus(1);
				}
			}
		}
		ResultMessage resultMessage = new ResultMessage(ReturnCode.OK, "成功");
		resultMessage.put("info", list);
		return resultMessage;
	}

	/**
	 * 获取实名认证尚未得到积分的积分数
	 * 
	 * @param userId
	 * @return
	 */
	private Integer getAllAuthStar(Integer userId) {
		try {
			Integer all = awardStarTypeMapper.querySum();
			Integer user = awardStarTypeMapper.queryUserSum(userId);
			if (all == null) {
				return 0;
			}
			if (user == null) {
				user = 0;
			}
			Integer ret = all - user;
			if (ret < 0) {
				return 0;
			}
			return ret;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

	private List<AwardStarType> getStarModules(Integer userId) throws SQLException {
		List<AwardStarType> awards = awardStarTypeMapper.getAwardStarType(1);
		if (null == awards || awards.isEmpty()) {
			return null;
		}
		List<StarModule> list = starModuleMapper.getStarModules(userId);
		if (null == list || list.isEmpty()) {
			return awards;
		}

		for (AwardStarType a : awards) {
			a.setStatus(0);
			for (StarModule starModule : list) {
				if (a.getType_code().equals(starModule.getModuleCode()) && starModule.getStatus() == 1) {
					a.setStatus(1);
				}
			}
		}
		return awards;
	}

	@Override
	public ResultMessage getStarModuleList(Integer userId) throws SQLException {
		List<AwardStarType> asList = getStarModules(userId);
		if (null == asList || asList.size() == 0) {
			return new ResultMessage(ReturnCode.FAILED, "数据为空！");
		}
		for (AwardStarType awardStarType : asList) {
			int a = awardStarType.getType_value().intValue();
			awardStarType.setType_value(new BigDecimal(a));
		}
		ResultMessage resultMessage = new ResultMessage(ReturnCode.OK, "获取成功");
		resultMessage.put("info", asList);
		return resultMessage;
	}

	@Override
	public String restLoginPassword(Integer userId, String newPwd, String phone) throws SQLException {
		// 密码复杂度验证
		String msg = CommonUtils.validPass(newPwd);// 检验密码复杂度
		if (msg != null) {
			return msg;
		}
		User user = new User();
		user.setUserId(userId);
		user.setPassword(EncryptUtil.encodeByMD5(EncryptUtil.encodeByMD5(newPwd) + Constant.LOGIN_SECRETKEY));
		int result = updateUser(user);
		if (result > 0 && phone != null && !phone.isEmpty()) {
			RedisUtil.del("Login_Lock_" + phone);// 删除redis中密码输入错误锁
			return null;
		} else {
			return "重置失败";
		}
	}

	@Override
	public ResultMessage award(Integer userId, String authType, Short mt) throws SQLException, GrpcException {
		User user = getUserById(userId);
		String userToken = user.getUserToken();
		if (mt == null) {
			pushAuth2Nats(null, authType);
		}
		pushAuth2Nats(Integer.valueOf(mt), authType);
		return starService.award(userToken, authType);
	}

	@Override
	public void pushAuth2Nats(Integer terminal, String authType) {
		Map<String, Object> map = new HashMap<String, Object>();
		String subject = "oms.subject.user.auth";
		map.put("id", CommonUtils.randomID(8));
		map.put("authTime", System.currentTimeMillis());
		map.put("createTime", System.currentTimeMillis());
		map.put("platformFrom", "WKAPP");
		map.put("clientPlatformType", terminal == 3 ? "IOS" : "ANDROID");
		map.put("authStatus", 1);//
		map.put("authType", authType);// 必填,
										// [取值范围：AUTHIDCARD-实名认证，GENE-基因数据，ADDRESS-添加地址，ATTENTIONWEBCHAT-关注公众号，VOICEDISCERN-声音识别，FACEDEISCEERN-人脸识别，BINDBANK-绑定银行卡，HARDWAREBIND-绑定矿机,
										// BINDADDRESSLIST=绑定通讯录]
		map.put("wxID", "");//
		map.put("qqID", "");//
		map.put("sinaWeiBoID", "");//
		natsComponent.publish4oms(subject, JSON.toJSONString(map));
	}

	@Override
	public ResultMessage upload(HttpServletRequest request, Integer userId, String type, CommonsMultipartFile file,
			Short mt) throws SQLException, IOException {
		SimpleDateFormat s = new SimpleDateFormat("yyyyMM");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userId", userId);
		map.put("type", type);
		Integer count = userMapper.checkUserUpload(map);
		if (count >= 1) {
			return new ResultMessage(ReturnCode.FAILED, "已经识别过了！");
		}
		System.out.println("size:" + file.getSize());
		if (file.getSize() == 0) {
			return new ResultMessage(ReturnCode.FAILED, "没有上传的文件！");
		}
		String basePath = request.getServletContext().getRealPath("/"); // linux路径
		// 获取文件需要上传到的路径
		String ipkPath = "upload/" + type + "/" + s.format(new Date()) + "/";
		String path = basePath + ipkPath;
		System.out.println("path:" + path);
		String fileName = FileUtil.uploadFile(file, path);
		FileInfo fileInfo = new FileInfo(userId, "/" + ipkPath + fileName, type, fileName);
		try {
			Integer result = userMapper.saveFile(fileInfo);
			if (result <= 0) {
				return new ResultMessage(ReturnCode.SQL_EXCEPTION, "系统繁忙，请稍候再试！");
			}
			Map<String, Object> module = new HashMap<String, Object>();
			module.put("userId", userId);
			module.put("module_code", type);
			module.put("status", 1);
			Integer result01 = userMapper.addModule(module); // 添加用户的认证标识
			String type_string;
			if (type.equals("Voice")) {
				type_string = "VOICEDISCERN";
			} else {
				type_string = "FACEDEISCEERN";
			}
			ResultMessage result02 = award(userId, type_string, mt);
			if (result02.getCode() == 1) {
				log.error("GRPC异常!");
				throw new RuntimeException();
			}
		} catch (Exception e) {
			throw new RuntimeException();
		}
		return new ResultMessage(ReturnCode.OK, "识别成功");
	}
}
