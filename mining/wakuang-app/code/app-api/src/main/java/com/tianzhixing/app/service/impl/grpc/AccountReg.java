package com.tianzhixing.app.service.impl.grpc;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.tianzhixing.app.common.Constant;
import com.tianzhixing.app.component.grpc.GrpcConnection;
import com.tianzhixing.app.exception.GrpcException;
import com.tianzhixing.kernel.grpc.proto.account.reg.AccountInfo;
import com.tianzhixing.kernel.grpc.proto.account.reg.AccountInfo.Builder;
import com.tianzhixing.kernel.grpc.proto.account.reg.AccountRegServiceGrpc;
import com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileInfo;
import com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult;
import com.tianzhixing.kernel.grpc.proto.account.reg.Result;
import com.tianzhixing.kernel.grpc.proto.account.reg.AccountRegServiceGrpc.AccountRegServiceBlockingStub;

import io.grpc.ManagedChannel;

@Component
public class AccountReg {
	@Resource
	private GrpcConnection grpcConnection;

	/**
	 * 发送注册信息到grpc
	 * 
	 * @param userId
	 * @param phone
	 * @param userToken
	 *            推荐人token-选填
	 * @return
	 */
	public Result sendReg2Grpc(Integer userId, String phone, String userToken) throws GrpcException {
		try {
			if (userId == null || phone == null) {
				return null;
			}
			ManagedChannel channel = grpcConnection.getChannel(Constant.GRPC_OTHER_PORT);
			AccountRegServiceBlockingStub stub = AccountRegServiceGrpc.newBlockingStub(channel);
			Builder builder = AccountInfo.newBuilder().setToken(Constant.ID_AUTH_TOKEN).setMobile(phone)
					.setThirdToken(userId == null ? "" : userId.toString()).setRegTime(System.currentTimeMillis());
			if (userToken != null) {
				builder.setReferrerToken(userToken);
			}
			AccountInfo accountInfo = builder.build();
			Result result = stub.reg(accountInfo);
			if (channel != null)
				channel.shutdown();
			return result;
		} catch (Exception e) {
			throw new GrpcException("grpc错误");
		}
	}

	/**
	 * 发送校验手机号请求到grpc
	 * 
	 * @param phone
	 * @return
	 */
	public CheckMobileResult sendCheckMobile2Grpc(String phone) throws GrpcException {
		try {
			if (phone == null) {
				return null;
			}
			ManagedChannel channel = grpcConnection.getChannel(Constant.GRPC_OTHER_PORT);
			AccountRegServiceBlockingStub stub = AccountRegServiceGrpc.newBlockingStub(channel);
			CheckMobileInfo checkMobileInfo = CheckMobileInfo.newBuilder().setToken(Constant.ID_AUTH_TOKEN)
					.setMobile(phone).setOrg("WAAPP").build();
			CheckMobileResult result = stub.check(checkMobileInfo);
			if (channel != null)
				channel.shutdown();
			return result;
		} catch (Exception e) {
			throw new GrpcException("grpc错误");
		}
	}
}
