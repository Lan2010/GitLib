package com.tianzhixing.app.component.grpc;

import org.springframework.stereotype.Component;

import com.tianzhixing.app.common.Constant;

import io.grpc.ManagedChannel;
import io.grpc.netty.NegotiationType;
import io.grpc.netty.NettyChannelBuilder;

@Component
public class GrpcConnection {
	private ManagedChannel channel;// 客户端与服务器的通信channel

	private String port;

	private String host;

	public ManagedChannel getChannel(String host, String port) {
		if (host != null) {
			this.host = host;
		}else {
			this.host = Constant.GRPC_HOST;
		}
		if (port != null) {
			this.port = port;
		}else {
			this.port = Constant.GRPC_PORT;
		}
		channel = NettyChannelBuilder.forAddress(getHost(), Integer.valueOf(getPort()))
				.negotiationType(NegotiationType.PLAINTEXT).build();
		return channel;
	}

	public ManagedChannel getChannel(String port) {
		return getChannel(null, port);
	}

	public ManagedChannel getChannel() {
		return getChannel(null, null);
	}

	public void setChannel(ManagedChannel channel) {
		this.channel = channel;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

}
