package com.tianzhixing.app.service;

import com.tianzhixing.app.exception.GrpcException;
import com.tianzhixing.app.pojo.ResultMessage;

public interface SmsService {
	/**
	 * 发送短信，获取登录验证码
	 * @param phone
	 * @return
	 * @throws GrpcException 
	 * @throws Exception 
	 */
	ResultMessage sendSms(String phone,String useType) throws GrpcException;
}
