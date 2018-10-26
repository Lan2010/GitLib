package core.entity;

/**
 * 预约信息
 * 
 * @author dev-lan
 * @date 2018年6月9日
 */
public class Appt {
	private Integer apptId;// 自增主键
	private Integer needStar; // 所需星星数
	private Integer appted_number; // 已预约数
	private String appt_startTime;// 预约拍摄开始时间
	private String appt_endTime;// 预约拍摄开始时间
	private Dest dest; // 景点Id

	public Integer getApptId() {
		return apptId;
	}

	public void setApptId(Integer apptId) {
		this.apptId = apptId;
	}

	public Integer getNeedStar() {
		return needStar;
	}

	public void setNeedStar(Integer needStar) {
		this.needStar = needStar;
	}

	public Integer getAppted_number() {
		return appted_number;
	}

	public void setAppted_number(Integer appted_number) {
		this.appted_number = appted_number;
	}

	public String getAppt_startTime() {
		return appt_startTime;
	}

	public void setAppt_startTime(String appt_startTime) {
		this.appt_startTime = appt_startTime;
	}

	public String getAppt_endTime() {
		return appt_endTime;
	}

	public void setAppt_endTime(String appt_endTime) {
		this.appt_endTime = appt_endTime;
	}

	public Dest getDest() {
		return dest;
	}

	public void setDest(Dest dest) {
		this.dest = dest;
	}

}
