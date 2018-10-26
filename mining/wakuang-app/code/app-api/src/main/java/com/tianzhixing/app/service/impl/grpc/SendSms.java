package com.tianzhixing.app.service.impl.grpc;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.tianzhixing.app.common.Constant;
import com.tianzhixing.app.component.grpc.GrpcConnection;
import com.tianzhixing.app.exception.GrpcException;
import com.tianzhixing.auth.grpc.proto.mobile.MobileValidationSerivceGrpc;
import com.tianzhixing.auth.grpc.proto.mobile.MobileValidationSerivceGrpc.MobileValidationSerivceBlockingStub;
import com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo;
import com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo.SendCodeType;
import com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult;
import com.tianzhixing.auth.grpc.proto.mobile.ValidationCodeInfo;
import com.tianzhixing.auth.grpc.proto.mobile.ValidationResult;

import io.grpc.ManagedChannel;

@Component
public class SendSms {
	@Resource
	private GrpcConnection grpcConnection;

	public SendCodeResult sendSms2Grpc(String phone) throws GrpcException {
		try {
			ManagedChannel channel = grpcConnection.getChannel();
			MobileValidationSerivceBlockingStub stub = MobileValidationSerivceGrpc.newBlockingStub(channel);
			SendCodeInfo sendCodeInfo = SendCodeInfo.newBuilder().setMobile(phone).setToken(Constant.ID_AUTH_TOKEN)
					.setCodeLength(6).setSendCodeType(SendCodeType.SECURITY_CODE).build();
			SendCodeResult sendCodeResult = stub.send(sendCodeInfo);
			if (channel != null)
				channel.shutdown();
			return sendCodeResult;
		} catch (Exception e) {
			e.printStackTrace();
			throw new GrpcException("grpc错误");
		}
	}

	/**
	 * 短信登录时，向gprc服务器端校验短信验证码
	 * 
	 * @param phone
	 * @param codeToken
	 * @param inputCode
	 * @return
	 */
	public ValidationResult sendValiSms2Grpc(String phone, String codeToken, String inputCode) throws GrpcException {
		try {
			if (phone == null || codeToken == null || inputCode == null) {
				return null;
			}
			ManagedChannel channel = grpcConnection.getChannel();
			MobileValidationSerivceBlockingStub stub = MobileValidationSerivceGrpc.newBlockingStub(channel);
			ValidationCodeInfo sendCodeInfo = ValidationCodeInfo.newBuilder().setMobile(phone)
					.setToken(Constant.ID_AUTH_TOKEN).setCodeToken(codeToken).setInputCode(inputCode).build();
			ValidationResult validationResult = stub.validate(sendCodeInfo);
			if (channel != null)
				channel.shutdown();
			return validationResult;
		} catch (Exception e) {
			throw new GrpcException("grpc错误");
		}
	}
}
