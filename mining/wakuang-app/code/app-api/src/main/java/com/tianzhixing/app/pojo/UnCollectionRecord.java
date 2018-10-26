package com.tianzhixing.app.pojo;

public class UnCollectionRecord {
	private String operStarPoint;
	private String longitudeAndLatitude;
	private String recordsType;
	private Integer taskId;
	private Integer advertisementId;
	private String remark;
	private String createTime;
	private String recordToken;

	public UnCollectionRecord() {
		super();
	}

	public UnCollectionRecord(String operStarPoint, String longitudeAndLatitude, String recordsType, Integer taskId,
			Integer advertisementId, String remark, String createTime, String recordToken) {
		super();
		this.operStarPoint = operStarPoint;
		this.longitudeAndLatitude = longitudeAndLatitude;
		this.recordsType = recordsType;
		this.taskId = taskId;
		this.advertisementId = advertisementId;
		this.remark = remark;
		this.createTime = createTime;
		this.recordToken = recordToken;
	}

	public String getOperStarPoint() {
		return operStarPoint;
	}

	public void setOperStarPoint(String operStarPoint) {
		this.operStarPoint = operStarPoint;
	}

	public String getLongitudeAndLatitude() {
		return longitudeAndLatitude;
	}

	public void setLongitudeAndLatitude(String longitudeAndLatitude) {
		this.longitudeAndLatitude = longitudeAndLatitude;
	}

	public String getRecordsType() {
		return recordsType;
	}

	public void setRecordsType(String recordsType) {
		this.recordsType = recordsType;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

	public Integer getAdvertisementId() {
		return advertisementId;
	}

	public void setAdvertisementId(Integer advertisementId) {
		this.advertisementId = advertisementId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getRecordToken() {
		return recordToken;
	}

	public void setRecordToken(String recordToken) {
		this.recordToken = recordToken;
	}

}
