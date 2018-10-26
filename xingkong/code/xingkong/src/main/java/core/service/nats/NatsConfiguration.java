package core.service.nats;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import core.common.Constant;
import core.util.CommonUtils;

@Component
public class NatsConfiguration {
	private String url;
	
	@PostConstruct
	public void init(){
		this.url = CommonUtils.getPropertiesValue(Constant.APPLICATION_CONFIG_PATH,"io.nats.client.url");
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
}
