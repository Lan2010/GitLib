package com.tianzhixing.app.interceptor;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tianzhixing.app.pojo.BaseResponse;
import com.tianzhixing.app.util.RedisUtil;

/**
 * 登录拦截器，拦截所有请求，控制访问权限，只有在登录的情况下才能访问
 * 
 * @author dev-teng
 * @date 2018年7月30日
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
	private static Logger log = LoggerFactory.getLogger(LoginInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		log.info("{keyword：LoginInterceptor preHandle,content:request url->" + request.getServletPath() + "?"
				+ request.getQueryString() + "}");
		// return true;
		String path = request.getServletPath();
		if(("/MobileApi/index/setTask").equals(path)
			||	("/MobileApi/index/setStartType").equals(path)
			||	("/MobileApi/index/setAdvertisement").equals(path)
			||	("/MobileApi/index/setFrame").equals(path)) {
			return true;
		}
		try {
			request.setCharacterEncoding("utf-8");
			String token = request.getParameter("token");
			String service = request.getParameter("service");
			if(service!=null && !service.isEmpty()) {
				service = service.toLowerCase();
			}else {
				service="";
			}		
			
			if (("user.login").equals(service) 
					|| ("user.smslogin").equals(service)
					|| ("user.register").equals(service)
					|| ("sms.loginphonecode").equals(service) 
					|| ("sms.regcode").equals(service)
					|| ("banner.getstartlist").equals(service) 
					|| ("common.appstopstar").equals(service)
					|| ("common.suspensionframe").equals(service) 
					|| ("common.versioninfo").equals(service)
					|| ("common.commonurl").equals(service)
					|| ("common.timestamp").equals(service)
					|| ("coupon.getauditstatus").equals(service) 
					|| ("ad.getlocationstar").equals(service)) {
				return true;//放行一些不需要登录的接口
			}

			if (token != null && !"".equals(token.trim())) {// 判断token是否存在，且在缓存中能够找到并匹配正确，返回true
				String keyPattern = "APP_Login_" + token + "_*";
				Set<String> keys = RedisUtil.keys(keyPattern);
				if (keys != null && keys.size() > 0) {
					String key = "";
					for (String v : keys) {
						if (v != null) {
							key = v;
							break;
						}
					}
					if (key.isEmpty()) {
						BaseResponse br = new BaseResponse(4008, null, "未登录", "", null);
						br.output(response);
					}
					String userInfo = RedisUtil.getString(key);
					JSONObject json = JSON.parseObject(userInfo);
					Integer user_id = (Integer) json.get("user_id");// 用户ID
					String phone = (String) json.get("phone");// 用户ID
					Integer expires_time = json.getInteger("expires_time");// 登录过期时间
					if (expires_time == null || expires_time <= System.currentTimeMillis()/1000) {// 当前时间超过过期时间
						BaseResponse br = new BaseResponse(4007, null, "登录已过期，需要重新登录", "", null);
						br.output(response);
						return false;
					}
					if ((Integer) json.get("user_id") == null) {
						BaseResponse br = new BaseResponse(4008, null, "未登录", "", null);
						br.output(response);
						return false;
					} else {
						request.setAttribute("user_id", user_id);// 必须设置，多个接口调用时，需要获取该参数
						request.setAttribute("defaultPhone", phone);// 手机号
						return super.preHandle(request, response, handler);
					}

				} else {
					BaseResponse br = new BaseResponse(4008, null, "未登录", "", null);
					br.output(response);
					return false;
				}

			} else {// token不存在，或者不正确，返回false
				BaseResponse br = new BaseResponse(4008, null, "未登录", "", null);
				br.output(response);
				return false;
			}

		} catch (UnsupportedEncodingException e) {
			log.error("keyword：LoginInterceptor preHandle,content:{}", e);
			return false;
		} catch (IOException e) {
			log.error("keyword：LoginInterceptor preHandle,content:{}", e);
			return false;
		} catch (Exception e) {
			log.error("keyword：LoginInterceptor preHandle,content:{}", e);
			return false;
		} finally {
			// TODO
		}
	}

	public static void main(String[] args) {
		String key = "Mbox_APP_Login_" + "496FBD6ECB02D96B544C53B7DE57F07A7D1BC287FA09D33BF54F4FC6E230545D" + "_*";
		System.out.println(RedisUtil.keys(key));
	}
}
