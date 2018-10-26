package core.entity;

/**
 * 中奖信息
 * 
 * @author dev-lan
 * @date 2018年6月9日
 */
public class Win {
	private Integer id;// 自增主键
	private Integer userId; // 用户ID
	private Integer apptId; // 预约ID
	private String pubTime;// 中签发布时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getApptId() {
		return apptId;
	}

	public void setApptId(Integer apptId) {
		this.apptId = apptId;
	}

	public String getPubTime() {
		return pubTime;
	}

	public void setPubTime(String pubTime) {
		this.pubTime = pubTime;
	}

}
