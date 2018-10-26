package core.pojo;

/**
 * 明信片
 * 
 * @author dev-lan
 * @date 2018年6月14日
 * 
 */
public class PostCard {
	private Integer pcId; // 自增主键
	private String pcHeadImg; // 明信片头像
	private String pcTitle; // 明信片标题
	private String pcText; // 明信片文本内容
	private String pcDate; // 明信片生成日期
	private Integer destId; // 景点ID
	private String destName;// 景点名称
	private String destImgUrl; // 景点原图地址
	private String destBtImgUrl; // 景点大缩略图地址
	private String thumbImageUrl;// 景点缩略图地址
	private String destMusic;// 景点背景音乐
	private Integer userId; // 用户ID
	private String nickName;// 用户微信昵称

	public PostCard() {
		super();
	}

	public PostCard(Integer pcId, String pcTitle) {
		super();
		this.pcId = pcId;
		this.pcTitle = pcTitle;
	}

	public PostCard(String pcHeadImg, String pcTitle, String pcText, String pcDate, Integer destId, Integer userId) {
		super();
		this.pcHeadImg = pcHeadImg;
		this.pcTitle = pcTitle;
		this.pcText = pcText;
		this.pcDate = pcDate;
		this.destId = destId;
		this.userId = userId;
	}

	public String getDestName() {
		return destName;
	}

	public void setDestName(String destName) {
		this.destName = destName;
	}

	public String getDestImgUrl() {
		return destImgUrl;
	}

	public void setDestImgUrl(String destImgUrl) {
		this.destImgUrl = destImgUrl;
	}

	public String getDestBtImgUrl() {
		return destBtImgUrl;
	}

	public void setDestBtImgUrl(String destBtImgUrl) {
		this.destBtImgUrl = destBtImgUrl;
	}

	public String getThumbImageUrl() {
		return thumbImageUrl;
	}

	public void setThumbImageUrl(String thumbImageUrl) {
		this.thumbImageUrl = thumbImageUrl;
	}

	public String getDestMusic() {
		return destMusic;
	}

	public void setDestMusic(String destMusic) {
		this.destMusic = destMusic;
	}

	public Integer getPcId() {
		return pcId;
	}

	public void setPcId(Integer pcId) {
		this.pcId = pcId;
	}

	public String getPcHeadImg() {
		return pcHeadImg;
	}

	public void setPcHeadImg(String pcHeadImg) {
		this.pcHeadImg = pcHeadImg;
	}

	public String getPcTitle() {
		return pcTitle;
	}

	public void setPcTitle(String pcTitle) {
		this.pcTitle = pcTitle;
	}

	public String getPcText() {
		return pcText;
	}

	public void setPcText(String pcText) {
		this.pcText = pcText;
	}

	public String getPcDate() {
		return pcDate;
	}

	public void setPcDate(String pcDate) {
		this.pcDate = pcDate;
	}

	

	public Integer getDestId() {
		return destId;
	}

	public void setDestId(Integer destId) {
		this.destId = destId;
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

}
