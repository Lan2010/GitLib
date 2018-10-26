package core.pojo;

import java.util.Date;

/**
 * 用户预约信息
 * @author dev-teng
 * @date 2018年6月16日
 */
public class Sub {
	private Integer id;//自增编号
	private Short subSuccess;//预约是否成功状态值，0失败，1成功
	private Date subTime;//预约时间
	private Integer userId;//用户编号ID
	
	public Sub() {}
	
	public Sub(Short subSuccess, Date subTime, Integer userId) {
		super();
		this.subSuccess = subSuccess;
		this.subTime = subTime;
		this.userId = userId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Short getSubSuccess() {
		return subSuccess;
	}

	public void setSubSuccess(Short subSuccess) {
		this.subSuccess = subSuccess;
	}

	public Date getSubTime() {
		return subTime;
	}

	public void setSubTime(Date subTime) {
		this.subTime = subTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}
