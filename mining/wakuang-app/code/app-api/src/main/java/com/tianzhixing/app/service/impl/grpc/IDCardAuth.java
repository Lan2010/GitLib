package com.tianzhixing.app.service.impl.grpc;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.tianzhixing.app.common.Constant;
import com.tianzhixing.app.component.grpc.GrpcConnection;
import com.tianzhixing.app.exception.GrpcException;
import com.tianzhixing.auth.grpc.proto.idcard.AuthResult;
import com.tianzhixing.auth.grpc.proto.idcard.IDCardAuthSerivceGrpc;
import com.tianzhixing.auth.grpc.proto.idcard.IDCardInfo;
import com.tianzhixing.auth.grpc.proto.idcard.IDCardAuthSerivceGrpc.IDCardAuthSerivceBlockingStub;

import io.grpc.ManagedChannel;

@Component
public class IDCardAuth {
	@Resource
	private GrpcConnection grpcConnection;

	public AuthResult authIDCard(String name, String idcard) throws GrpcException {
		if (name == null || idcard == null) {
			return null;
		}
		try {
			ManagedChannel channel = grpcConnection.getChannel();
			IDCardAuthSerivceBlockingStub stub = IDCardAuthSerivceGrpc.newBlockingStub(channel);// 根据通信channel初始化客户端存根节点
			IDCardInfo iDCardInfo = IDCardInfo.newBuilder().setName(name).setIdcard(idcard)
					.setToken(Constant.ID_AUTH_TOKEN).build();
			AuthResult authResult = stub.authIDCard(iDCardInfo);
			if (channel != null)
				channel.shutdown();
			return authResult;
		} catch (Exception e) {
			throw new GrpcException("grpc错误");
		}
	}
}
