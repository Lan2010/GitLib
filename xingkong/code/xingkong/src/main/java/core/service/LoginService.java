package core.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import core.pojo.User;
/**
 * 登录业务处理层
 * @author dev-teng
 * @date 2018年6月11日
 */
public interface LoginService {
	/**
	 * 携带小程序客户端传入的code码，向微信服务器发起http请求,获取session_key,openid,存入Map集合中
	 * @param code
	 * @return
	 */
	Map<String, Object> login(String code);

	/**
	 * 登录埋点数据，发布到nats
	 * @param request
	 * @param i
	 * @param user
	 */
	void publish4oms(HttpServletRequest request, Integer operationType, User user);

}
