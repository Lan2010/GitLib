package core.util;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTUtil {
	private static Logger logger = LoggerFactory.getLogger(JWTUtil.class);
	/**
	 * 签名
	 * @param secret
	 * @return
	 */
	private static SecretKey generalKey(String secret) {
		if(secret==null || "".equals(secret))
			return null;
		byte[] encodedKey = Base64.decodeBase64(secret);
		SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");
		return key;
	}


	/**
	 * 生成JWT
	 * @param session_key 加密的密钥
	 * @param claim 
	 * @return
	 */
	public static String createJWT(String session_key,Claims claims){
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		JwtBuilder builder = Jwts.builder().setClaims(claims).signWith(signatureAlgorithm,generalKey(session_key));//
		return builder.compact();
	}
	
	/**
	 * 从jwt消息中获取指定key的value值，如用户信息等
	 * @param token
	 * @param session_type
	 * @param session_key
	 * @return
	 */
	public static String getJti(String token,String session_key) {
		Claims claims= getClaims(token,session_key);
		if(claims!=null) {//校验token是否在redis中存在
			return claims.getId();
		}
		return null;
	}
	
	/**
	 * 获取Claims
	 * @param token
	 * @param session_type
	 * @param session_key
	 * @return
	 */
	public static Claims getClaims(String token,String session_key) {
		if(token==null) {
			return null;
		}
		return parseJWT(token,session_key);
	}
	
	/**
	 * 
	 * 解析JWT字符串
	 * @param token
	 * @param sessionType
	 * @param session_key
	 * @return
	 * @throws Exception
	 */
	private static Claims parseJWT(String token,String session_key){
		if(token==null) {
			return null;
		}
		JwtParser jwtParser  =Jwts.parser().setSigningKey(generalKey(session_key));
		try {
			Jws<Claims> jws = jwtParser.parseClaimsJws(token);
			if(jws!=null)
				return jws.getBody();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
}
