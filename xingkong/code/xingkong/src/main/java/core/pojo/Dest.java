package core.pojo;

/**
 * 景点信息
 * 
 * @author dev-lan
 * @date 2018年6月9日
 */
public class Dest {
	private Integer destId;// 自增主键
	private String destName;// 景点名称
	private String destLogo; // 景点logo
	private String destDesc; // 景点详情介绍
	private String destImgUrl; // 景点原图地址
	private String destBtImgUrl; // 景点大缩略图地址
	private String thumbImageUrl;// 景点缩略图地址
	private String destMusic;//景点背景音乐
	private Short category; // 景点类别，对地--0 对星空 --1
	private Short status; // 景点状态

	public String getDestBtImgUrl() {
		return destBtImgUrl;
	}

	public void setDestBtImgUrl(String destBtImgUrl) {
		this.destBtImgUrl = destBtImgUrl;
	}

	public Integer getDestId() {
		return destId;
	}

	public void setDestId(Integer destId) {
		this.destId = destId;
	}

	public String getDestName() {
		return destName;
	}

	public void setDestName(String destName) {
		this.destName = destName;
	}

	public String getDestLogo() {
		return destLogo;
	}

	public void setDestLogo(String destLogo) {
		this.destLogo = destLogo;
	}

	public String getDestDesc() {
		return destDesc;
	}

	public void setDestDesc(String destDesc) {
		this.destDesc = destDesc;
	}

	public String getDestImgUrl() {
		return destImgUrl;
	}

	public void setDestImgUrl(String destImgUrl) {
		this.destImgUrl = destImgUrl;
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

	public Short getCategory() {
		return category;
	}

	public void setCategory(Short category) {
		this.category = category;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}
}
