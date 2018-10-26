package core.pojo;

/**
 * 许愿表
 * 
 * @author dev-lan
 * @date 2018年6月12日
 */
public class Wish {
	private Integer wishId; //自增主键
	private Integer userId; //用户ID
	private String createTime; //许愿时间
	private Integer audioTimeLen; //许愿语音时长
	private String audioUrl; //许愿语音地址
	
	public Wish() {}
	
	public Wish(Integer userId, String createTime, Integer audioTimeLen, String audioUrl) {
		super();
		this.userId = userId;
		this.createTime = createTime;
		this.audioTimeLen = audioTimeLen;
		this.audioUrl = audioUrl;
	}
	public Integer getWishId() {
		return wishId;
	}
	public void setWishId(Integer wishId) {
		this.wishId = wishId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Integer getAudioTimeLen() {
		return audioTimeLen;
	}
	public void setAudioTimeLen(Integer audioTimeLen) {
		this.audioTimeLen = audioTimeLen;
	}
	public String getAudioUrl() {
		return audioUrl;
	}
	public void setAudioUrl(String audioUrl) {
		this.audioUrl = audioUrl;
	}

}
