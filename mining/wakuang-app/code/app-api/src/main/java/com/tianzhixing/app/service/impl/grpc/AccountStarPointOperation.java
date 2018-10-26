package com.tianzhixing.app.service.impl.grpc;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.tianzhixing.app.common.Constant;
import com.tianzhixing.app.component.grpc.GrpcConnection;
import com.tianzhixing.app.exception.GrpcException;
import com.tianzhixing.kernel.grpc.proto.ResponseEntity;
import com.tianzhixing.kernel.grpc.proto.account.starpoint.PageMapper;
import com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AccountStarPointOperationServiceGrpc;
import com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AccountStarPointOperationServiceGrpc.AccountStarPointOperationServiceBlockingStub;
import com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardInfo;
import com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardResult;
import com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.BindAddressAwardInfo;
import com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult;
import com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionStarPointInfo;
import com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.StarPointRecordsInfo;
import com.tianzhixing.kernel.grpc.proto.advertisement.operation.AdvertisementInfo;
import com.tianzhixing.kernel.grpc.proto.advertisement.operation.AdvertisementOperationServiceGrpc;
import com.tianzhixing.kernel.grpc.proto.advertisement.operation.ClickResult;
import com.tianzhixing.kernel.grpc.proto.advertisement.operation.AdvertisementOperationServiceGrpc.AdvertisementOperationServiceBlockingStub;

import io.grpc.ManagedChannel;

@Component
public class AccountStarPointOperation {
	@Resource
	private GrpcConnection grpcConnection;

	/**
	 * 
	 * @param userToken
	 * @param type
	 * 			[取值范围：AUTHIDCARD-实名认证奖励，GENE-基因数据奖励，ADDRESS-添加地址奖励，ATTENTIONWEBCHAT-关注公众号奖励，VOICEDISCERN-声音识别奖励，FACEDEISCEERN-人脸识别奖励，BINDBANK-绑定银行卡奖励，HARDWAREBIND-绑定矿机奖励]
	 * @return
	 * @throws GrpcException
	 */
	public AwardResult award(String userToken, String type) throws GrpcException {
		try {
			ManagedChannel channel = grpcConnection.getChannel(Constant.GRPC_OTHER_PORT);
			AccountStarPointOperationServiceBlockingStub stub = AccountStarPointOperationServiceGrpc
					.newBlockingStub(channel);// 根据通信channel初始化客户端存根节点
			AwardInfo info = AwardInfo.newBuilder().setAdwardTime(System.currentTimeMillis()).setAdwardType(type)
					.setAccountToken(userToken).setToken(Constant.ID_AUTH_TOKEN).build();
			AwardResult result = stub.award(info);
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

	public CollectionResult collection(String userToken, List<StarPointRecordsInfo> records, String advertId)
			throws GrpcException {
		CollectionResult result = new CollectionResult();
		try {
			ManagedChannel channel = grpcConnection.getChannel(Constant.GRPC_OTHER_PORT);
			AccountStarPointOperationServiceBlockingStub stub = AccountStarPointOperationServiceGrpc
					.newBlockingStub(channel);// 根据通信channel初始化客户端存根节点
			CollectionStarPointInfo condition = CollectionStarPointInfo.newBuilder().setAccountToken(userToken)
					.setCollectionTime(System.currentTimeMillis()).setRecords(records).setAdvertIdOperationType(0)
					.setAdvertId(advertId).setToken(Constant.ID_AUTH_TOKEN).build();
			result = stub.collection(condition);
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

	public ClickResult clickOrAccess(String advertId, String starPoint, Integer adStarPointType, String userToken)
			throws GrpcException {
		ClickResult result = new ClickResult();
		try {
			ManagedChannel channel = grpcConnection.getChannel(Constant.GRPC_OTHER_PORT);
			AdvertisementOperationServiceBlockingStub stub = AdvertisementOperationServiceGrpc.newBlockingStub(channel);// 根据通信channel初始化客户端存根节点
			PageMapper pageMapper = new PageMapper().newBuilder().setFrom(0).setSize(20).build();
			AdvertisementInfo info = AdvertisementInfo.newBuilder().setAdvertId(advertId).setStarPoint(starPoint)
					.setClickTime(System.currentTimeMillis()).setOperationType(adStarPointType)
					.setAccountToken(userToken).setToken(Constant.ID_AUTH_TOKEN).build();
			result = stub.clickOrAccess(info);

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

	/**
	 * 绑定通讯录，获得星星奖励
	 * 
	 * @param userToken
	 * @param contactCount
	 * @return
	 * @throws GrpcException
	 */
	public AwardResult grpcBindAddr(String userToken, Integer contactCount) throws GrpcException {
		try {
			ManagedChannel channel = grpcConnection.getChannel(Constant.GRPC_OTHER_PORT);
			AccountStarPointOperationServiceBlockingStub stub = AccountStarPointOperationServiceGrpc
					.newBlockingStub(channel);
			BindAddressAwardInfo bindAddr = BindAddressAwardInfo.newBuilder().setToken(Constant.ID_AUTH_TOKEN)
					.setAccountToken(userToken).setAdwardTime(System.currentTimeMillis()).setContactCount(contactCount)
					.build();
			AwardResult result = stub.bindAddressList(bindAddr);
			if (channel != null)
				channel.shutdown();
			return result;
		} catch (Exception e) {
			throw new GrpcException("grpc错误");
		}
	}
}
