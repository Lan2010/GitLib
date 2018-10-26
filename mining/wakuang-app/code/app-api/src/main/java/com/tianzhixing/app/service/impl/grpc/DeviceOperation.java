package com.tianzhixing.app.service.impl.grpc;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.tianzhixing.app.common.Constant;
import com.tianzhixing.app.component.grpc.GrpcConnection;
import com.tianzhixing.app.exception.GrpcException;
import com.tianzhixing.kernel.grpc.proto.ResponseEntity;
import com.tianzhixing.kernel.grpc.proto.device.operation.DeviceInfo;
import com.tianzhixing.kernel.grpc.proto.device.operation.DeviceOperationServiceGrpc;
import com.tianzhixing.kernel.grpc.proto.device.operation.DeviceOperationServiceGrpc.DeviceOperationServiceBlockingStub;
import com.tianzhixing.kernel.grpc.proto.device.operation.Result;

import io.grpc.ManagedChannel;

@Component
public class DeviceOperation {
	@Resource
	private  GrpcConnection grpcConnection;

	public  Result bind2Grpc(String userToken, String device_id, String device_type, String device_mac,
			String device_model) throws GrpcException {
		Result result = new Result();
		try {	
			ManagedChannel channel = grpcConnection.getChannel(Constant.GRPC_OTHER_PORT);
			DeviceOperationServiceBlockingStub stub = DeviceOperationServiceGrpc.newBlockingStub(channel);// 根据通信channel初始化客户端存根节点
			DeviceInfo info = DeviceInfo.newBuilder().setDeviceId(device_id).setDeviceType(device_type)
					.setDeviceMAC(device_mac).setDeviceModel(device_model).setOperationTime(System.currentTimeMillis())
					.setAccountToken(userToken).setToken(Constant.ID_AUTH_TOKEN).build();
			 result = stub.bind(info);
			if (channel != null)
				channel.shutdown();
			if (result == null) {
				throw new GrpcException("grpc错误");
			}
			ResponseEntity responseEntity = result.getResponseEntity();
			if (responseEntity == null) {
				throw new GrpcException("grpc错误");
			}
			return result;
		} catch (Exception e) {
			throw new GrpcException("grpc错误");
		}
	}

}
