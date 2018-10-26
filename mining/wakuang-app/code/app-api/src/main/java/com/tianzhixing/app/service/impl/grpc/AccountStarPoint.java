package com.tianzhixing.app.service.impl.grpc;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.tianzhixing.app.common.Constant;
import com.tianzhixing.app.component.grpc.GrpcConnection;
import com.tianzhixing.app.exception.GrpcException;
import com.tianzhixing.app.pojo.Page;
import com.tianzhixing.kernel.grpc.proto.ResponseEntity;
import com.tianzhixing.kernel.grpc.proto.account.starpoint.AccountStarPointServiceGrpc;
import com.tianzhixing.kernel.grpc.proto.account.starpoint.AccountStarPointServiceGrpc.AccountStarPointServiceBlockingStub;
import com.tianzhixing.kernel.grpc.proto.account.starpoint.PageMapper;
import com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingInfo;
import com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingResult;
import com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsCondition;
import com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult;
import com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition;
import com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayResult;
import com.tianzhixing.kernel.grpc.proto.account.starpoint.UnCollectionRecordsCondition;
import com.tianzhixing.kernel.grpc.proto.account.starpoint.UnCollectionRecordsResult;

import io.grpc.ManagedChannel;

@Component
public class AccountStarPoint {
	@Resource
	private  GrpcConnection grpcConnection;

	public  RankingResult ranking() throws GrpcException {
		RankingResult result = new RankingResult();
		try {
			 ManagedChannel channel = grpcConnection.getChannel(Constant.GRPC_OTHER_PORT);
			AccountStarPointServiceBlockingStub stub = AccountStarPointServiceGrpc.newBlockingStub(channel);// 根据通信channel初始化客户端存根节点
			PageMapper pageMapper = PageMapper.newBuilder().setFrom(0).setSize(20).build();
			RankingInfo rankingInfo = RankingInfo.newBuilder().setPageMapper(pageMapper)
					.setToken(Constant.ID_AUTH_TOKEN).build();
			result = stub.ranking(rankingInfo);
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
			e.printStackTrace();
			throw new GrpcException("grpc错误");
		}
	}

	public  RecordsResult records(String userToken, Page page) throws GrpcException {
		RecordsResult result = new RecordsResult();
		try {
			 ManagedChannel channel = grpcConnection.getChannel(Constant.GRPC_OTHER_PORT);
			AccountStarPointServiceBlockingStub stub = AccountStarPointServiceGrpc.newBlockingStub(channel);// 根据通信channel初始化客户端存根节点
			PageMapper pageMapper = PageMapper.newBuilder().setFrom(page.getStart()).setSize(page.getPageSize())
					.build();
			RecordsCondition recordsCondition = RecordsCondition.newBuilder().setAccountToken(userToken)
					.setPageMapper(pageMapper).setToken(Constant.ID_AUTH_TOKEN).setRecordsType("ALL").build();
			result = stub.records(recordsCondition);
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

	public  UnCollectionRecordsResult unCollectionRecords(String userToken) throws GrpcException {
		UnCollectionRecordsResult result = new UnCollectionRecordsResult();
		try {
			 ManagedChannel channel = grpcConnection.getChannel(Constant.GRPC_OTHER_PORT);
			AccountStarPointServiceBlockingStub stub = AccountStarPointServiceGrpc.newBlockingStub(channel);// 根据通信channel初始化客户端存根节点
			UnCollectionRecordsCondition condition = UnCollectionRecordsCondition.newBuilder()
					.setAccountToken(userToken).setToken(Constant.ID_AUTH_TOKEN).build();
			result = stub.unCollectionRecords(condition);
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

	public  RecordsWithDayResult recordsWithDay(String userToken, int days, String endTime) throws GrpcException {
		RecordsWithDayResult result = new RecordsWithDayResult();
		try {
			 ManagedChannel channel = grpcConnection.getChannel(Constant.GRPC_OTHER_PORT);
			AccountStarPointServiceBlockingStub stub = AccountStarPointServiceGrpc.newBlockingStub(channel);// 根据通信channel初始化客户端存根节点
			RecordsWithDayCondition info = RecordsWithDayCondition.newBuilder().setDays(days)
					.setEndTime(Long.parseLong(endTime)).setAccountToken(userToken).setToken(Constant.ID_AUTH_TOKEN)
					.build();
			 result = stub.recordsWithDay(info);
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
	 * 获取用户星星数基本信息
	 * @param userToken
	 * @return
	 * @throws GrpcException
	 */
	public com.tianzhixing.kernel.grpc.proto.account.starpoint.Result grpcGetUserInfo(String userToken)throws GrpcException {
		try {
			ManagedChannel channel = grpcConnection.getChannel(Constant.GRPC_OTHER_PORT);
			AccountStarPointServiceBlockingStub stub = AccountStarPointServiceGrpc.newBlockingStub(channel);
			com.tianzhixing.kernel.grpc.proto.account.starpoint.AccountInfo accountInfo = com.tianzhixing.kernel.grpc.proto.account.starpoint.AccountInfo
					.newBuilder().setToken(Constant.ID_AUTH_TOKEN).setAccountToken(userToken).build();
			com.tianzhixing.kernel.grpc.proto.account.starpoint.Result result = stub.starPoint(accountInfo);
			if (channel != null)
				channel.shutdown();
			return result;
		} catch (Exception e) {
			throw new GrpcException("grpc错误");
		}
	}

}
