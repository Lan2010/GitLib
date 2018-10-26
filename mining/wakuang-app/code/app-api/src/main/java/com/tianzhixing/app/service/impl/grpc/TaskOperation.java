package com.tianzhixing.app.service.impl.grpc;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.tianzhixing.app.common.Constant;
import com.tianzhixing.app.component.grpc.GrpcConnection;
import com.tianzhixing.app.exception.GrpcException;
import com.tianzhixing.kernel.grpc.proto.ResponseEntity;
import com.tianzhixing.kernel.grpc.proto.task.operation.Result;
import com.tianzhixing.kernel.grpc.proto.task.operation.TaskInfo;
import com.tianzhixing.kernel.grpc.proto.task.operation.TaskOperationServiceGrpc;
import com.tianzhixing.kernel.grpc.proto.task.operation.TaskOperationServiceGrpc.TaskOperationServiceBlockingStub;

import io.grpc.ManagedChannel;

@Component
public class TaskOperation {
	@Resource
	private GrpcConnection grpcConnection;

	public Result accept(String orderNO, String userToken) throws GrpcException {
		Result result = new Result();
		try {
			ManagedChannel channel = grpcConnection.getChannel(Constant.GRPC_OTHER_PORT);
			TaskOperationServiceBlockingStub stub = TaskOperationServiceGrpc.newBlockingStub(channel);// 根据通信channel初始化客户端存根节点
			TaskInfo info = TaskInfo.newBuilder().setOperationTime(System.currentTimeMillis()).setTaskId(orderNO)
					.setAccountToken(userToken).setToken(Constant.ID_AUTH_TOKEN).build();
			result = stub.accept(info);
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
