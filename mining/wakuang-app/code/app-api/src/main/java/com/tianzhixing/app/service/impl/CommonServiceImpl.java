package com.tianzhixing.app.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.tianzhixing.app.common.ReturnCode;
import com.tianzhixing.app.component.nats.NatsComponent;
import com.tianzhixing.app.dao.mapper.AppInfoMapper;
import com.tianzhixing.app.dao.mapper.AppVersionsMapper;
import com.tianzhixing.app.dao.mapper.CommonMapper;
import com.tianzhixing.app.dao.mapper.FeedBackMapper;
import com.tianzhixing.app.pojo.AppInfo;
import com.tianzhixing.app.pojo.AppVersions;
import com.tianzhixing.app.pojo.FeedBack;
import com.tianzhixing.app.pojo.ResultMessage;
import com.tianzhixing.app.pojo.SuspensionFrame;
import com.tianzhixing.app.service.CommonService;
import com.tianzhixing.app.util.CommonUtils;

@Service
public class CommonServiceImpl implements CommonService {
	@Resource
	private CommonMapper commonMapper;
	@Resource
	private FeedBackMapper feedBackMapper;
	@Resource
	private AppInfoMapper appInfoMapper;
	@Resource
	private NatsComponent natsComponent;
	@Resource
	private AppVersionsMapper appVersionsMapper;

	@Override
	public ResultMessage getSuspensionFrame() throws SQLException {
		ResultMessage result = new ResultMessage();
		SuspensionFrame frame = commonMapper.getSuspensionFrame();
		if (frame == null) {
			result = new ResultMessage(ReturnCode.FAILED, "暂无数据！");
			result.put("info", null);
			return result;
		}
		result = new ResultMessage(ReturnCode.OK, "识别到内容");
		result.put("info", frame);
		return result;
	}

	@Override
	public void pushAppOnOff2Nats(String ip, Short terminal, String phone, String operationType) {
		Map<String, Object> map = new HashMap<String, Object>();
		String subject = "oms.subject.application.operation";
		map.put("operationType", operationType);
		map.put("phone", phone);
		map.put("ip", ip);
		map.put("wxID", ip);
		map.put("appOperationTime", System.currentTimeMillis());
		map.put("id", CommonUtils.randomID(8));
		map.put("createTime", System.currentTimeMillis());
		map.put("platformFrom", "WKAPP");
		map.put("clientPlatformType", terminal == 3 ? "IOS" : "ANDROID");
		natsComponent.publish4oms(subject, JSON.toJSONString(map));
	}

	@Override
	public AppVersions getAppVersions(Short terminal) {
		List<AppVersions> list = new ArrayList<AppVersions>();
		if (terminal <= 0) {
			return null;
		}
		list = appVersionsMapper.getAppVersions(terminal);
		if (list.size() > 0)
			return list.get(0);
		else
			return null;
	}

	@Override
	public String getCheckCodes() {
		List<String> list = appVersionsMapper.getCheckCodes();
		return list.get(0);
	}

	@Override
	public Integer getFebackTimes(String deviceID, long startDay, long endday) throws SQLException {
		Integer times = feedBackMapper.getOneDayTimes(deviceID, startDay, endday);
		return times;
	}

	@Override
	public Integer addFeedBack(FeedBack feedBack) throws SQLException {
		return feedBackMapper.addFeedBack(feedBack);
	}

	@Override
	public Integer addAppInfo(AppInfo appInfo) throws SQLException {
		return appInfoMapper.addAppInfo(appInfo);
	}

}
