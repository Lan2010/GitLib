package com.tianzhixing.app.component.nats;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.tianzhixing.app.common.Constant;
import com.tianzhixing.app.util.CommonUtils;

import io.nats.client.Connection;
import io.nats.client.ConnectionFactory;

@Component
public class NatsComponentImpl implements NatsComponent {

	private static Logger log = LoggerFactory.getLogger(NatsComponent.class);

	private static ConnectionFactory cf = new ConnectionFactory(
			CommonUtils.getPropertiesValue(Constant.APPLICATION_CONFIG_PATH, "io.nats.client.url"));

	/**
	 * sub消息处理
	 */
	public void handleMessage() {

	}

	/**
	 * 发布消息，提供给运营平台的数据
	 * 
	 * @param msg
	 * @param subject
	 */
	public void publish4oms(String subject, String msg) {
		Connection nc = null;
		try {
			nc = cf.createConnection();
			byte[] data = msg.getBytes();// message payload,内容为json格式，转换成字节数组
			nc.publish(subject, data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("Exception.", e);
		} finally {
			if (nc != null)
				nc.close();
		}
	}
}
