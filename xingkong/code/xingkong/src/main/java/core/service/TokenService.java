package core.service;

/**
 * 自定义token业务处理层
 * @author dev-teng
 * @date 2018年6月11日
 */
public interface TokenService{
	
	TokenService createToken();
	
	TokenService setClaims(Integer userId,String session_key);

	TokenService addTokenToRedis();

	String getToken();

	TokenService addSessionKeyToRedis();
	
}
