package com.tianzhixing.app.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tianzhixing.app.common.ReturnCode;
import com.tianzhixing.app.exception.GrpcException;
import com.tianzhixing.app.pojo.ResultMessage;
import com.tianzhixing.app.service.SmsService;
import com.tianzhixing.app.service.impl.grpc.SendSms;
import com.tianzhixing.app.util.RedisUtil;
import com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult;

@Service
public class SmsServiceImpl implements SmsService {
	@Resource
	private SendSms sendSms;

	public ResultMessage sendSms(String phone, String useType) throws GrpcException {
		if (useType == null || useType.isEmpty()) {
			return new ResultMessage(1, "发生错误");
		}
		if (phone == null) {
			return new ResultMessage(1, "手机号不正确");
		}
		Map<String, Object> msg = new HashMap<String, Object>();
		String key = phone + "_" + useType;
		String sms = RedisUtil.getString(key);
		JSONObject json = JSON.parseObject(sms);
		if (sms != null && json != null && System.currentTimeMillis() / 1000 - json.getLong("sendtime") < 110) {
			return new ResultMessage(1, "你已经发送了一条短信了，请稍后再尝试");
		}
		SendCodeResult result = sendSms.sendSms2Grpc(phone);
		if (result == null) {
			return new ResultMessage(1, "grpc错误");
		}
		if (result.getCode() != 200) {
			return new ResultMessage(1, result.getMessage() != null ? result.getMessage() : "");
		}
		msg.put("code", result.getCode());
		msg.put("msg", result.getMessage());
		msg.put("mobile", result.getMobile());
		msg.put("codeToken", result.getCodeToken());
		msg.put("sendPlatform", result.getSendPlatform());
		msg.put("sendtime", System.currentTimeMillis());
		RedisUtil.set(key, JSON.toJSONString(msg), 120);
		return new ResultMessage(ReturnCode.OK, "发送成功");
	}

}
