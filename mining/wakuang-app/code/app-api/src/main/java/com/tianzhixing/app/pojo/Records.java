package com.tianzhixing.app.pojo;

public class Records {

	private String operStarPoint;
	private String createTime;
	private String operationType;
	private String recordsType;
	private String remark;

	public Records() {
		super();
	}

	public Records(String operStarPoint, String createTime, String operationType, String recordsType, String remark) {
		super();
		this.operStarPoint = operStarPoint;
		this.createTime = createTime;
		this.operationType = operationType;
		this.recordsType = recordsType;
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "Records [operStarPoint=" + operStarPoint + ", createTime=" + createTime + ", operationType="
				+ operationType + ", recordsType=" + recordsType + ", remark=" + remark + "]";
	}

	public String getOperStarPoint() {
		return operStarPoint;
	}

	public void setOperStarPoint(String operStarPoint) {
		this.operStarPoint = operStarPoint;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getRecordsType() {
		return recordsType;
	}

	public void setRecordsType(String recordsType) {
		this.recordsType = recordsType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
