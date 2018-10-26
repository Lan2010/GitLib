package core.pojo;

/**
 * 用户登录日志
 * @author dev-teng
 * @date 2018年6月12日
 */
public class LoginLog {
	private Integer id;//自增编号
	private String openid;//小程序openid
	private String loginTime;//登录时间
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	
}
