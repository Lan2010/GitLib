package core.pojo;

/**
 * 绿卡
 * 
 * @author dev-lan
 * @date 2018年6月14日
 * 
 */
public class GreenCard {

	private Integer gcId;// 自增主键
	private String gcNumber;// 绿卡号码
	private String signDate;// 签发日期
	private String gcName;// 用户绿卡昵称
	private String gcHeadImg;// 用户绿卡头像
	private Integer userId; // 用户ID

	
	public GreenCard() {
		super();
	}

	public GreenCard(String gcNumber, String signDate, String gcName, String gcHeadImg, Integer userId) {
		super();
		this.gcNumber = gcNumber;
		this.signDate = signDate;
		this.gcName = gcName;
		this.gcHeadImg = gcHeadImg;
		this.userId = userId;
	}

	public Integer getGcId() {
		return gcId;
	}

	public void setGcId(Integer gcId) {
		this.gcId = gcId;
	}

	public String getGcNumber() {
		return gcNumber;
	}

	public void setGcNumber(String gcNumber) {
		this.gcNumber = gcNumber;
	}

	public String getSignDate() {
		return signDate;
	}

	public void setSignDate(String signDate) {
		this.signDate = signDate;
	}

	public String getGcName() {
		return gcName;
	}

	public void setGcName(String gcName) {
		this.gcName = gcName;
	}

	public String getGcHeadImg() {
		return gcHeadImg;
	}

	public void setGcHeadImg(String gcHeadImg) {
		this.gcHeadImg = gcHeadImg;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "GreenCard [gcId=" + gcId + ", gcNumber=" + gcNumber + ", signDate=" + signDate + ", gcName=" + gcName
				+ ", gcHeadImg=" + gcHeadImg + ", userId=" + userId + "]";
	}

	
}
