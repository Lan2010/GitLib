package core.pojo;

/**
 * 用户表
 * @author dev-teng
 * @date 2018年6月12日
 */
public class User {
	private Integer userId;// 自增主键
	private String nickName;// 用户微信昵称
	private String cellPhone;// 手机号码
	private String openid;// 小程序唯一身份标识
	private String avatarUrl;// 用户微信头像
	private String avatarLocal;//
	private Short isAuth;// 是否认证  0--未认证  1--已认证
	private Short isGetGC;// 是否领取绿卡 0--未领取  1--已领取
	
	public User() {}
	
	public User(Integer userId, String nickName, String cellPhone, String openid, String avatarUrl, Short isAuth, String avatarLocal) {
		super();
		this.userId = userId;
		this.nickName = nickName;
		this.cellPhone = cellPhone;
		this.openid = openid;
		this.avatarUrl = avatarUrl;
		this.isAuth = isAuth;
		this.avatarLocal = avatarLocal;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getCellPhone() {
		return cellPhone;
	}
	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	public Short getIsAuth() {
		return isAuth;
	}
	public void setIsAuth(Short isAuth) {
		this.isAuth = isAuth;
	}

	public Short getIsGetGC() {
		return isGetGC;
	}

	public void setIsGetGC(Short isGetGC) {
		this.isGetGC = isGetGC;
	}

	public String getAvatarLocal() {
		return avatarLocal;
	}

	public void setAvatarLocal(String avatarLocal) {
		this.avatarLocal = avatarLocal;
	}
	
}
