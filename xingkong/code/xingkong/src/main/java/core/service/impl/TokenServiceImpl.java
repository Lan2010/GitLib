package core.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import core.service.TokenService;
import core.util.JWTUtil;
import core.util.RedisUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.impl.DefaultClaims;

/**
 * 自定义token业务管理层
 * 
 * @author dev-teng
 * @date 2018年6月11日
 */
@Service
public class TokenServiceImpl implements TokenService {
	private static Logger logger = LoggerFactory.getLogger(TokenServiceImpl.class);
	private String token;
	private Claims claims;
	private Integer userId;
	private String session_key;

	public TokenService createToken(){
		this.token = JWTUtil.createJWT(session_key, getClaims());// 创建jwt
		return this;
	}

	public TokenService setClaims(Integer userId,String session_key){
		claims = new DefaultClaims();
		claims.setId(String.valueOf(userId))
			.setIssuer("tianzhixing")
			.setSubject("mini program login")
			.setIssuedAt(new Date(System.currentTimeMillis()));
		this.session_key = session_key;
		this.userId = userId;
		return this;
	}
	
	public Claims getClaims() {
		return this.claims;
	}
	public String getToken() {
		return this.token;
	}

	public synchronized TokenService addTokenToRedis() {
		RedisUtil.set("token." + userId, getToken(), RedisUtil.EXPIRE);
		logger.info("[key=token.{},value={}]",userId,getToken());
		return this;
	}

	public synchronized TokenService addSessionKeyToRedis() {	
		RedisUtil.set(getToken(), this.session_key, RedisUtil.EXPIRE);
		return this;
	}

}
