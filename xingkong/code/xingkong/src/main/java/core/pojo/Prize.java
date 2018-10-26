package core.pojo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 中奖名单
 * @author dev-teng
 * @date 2018年6月25日
 */
public class Prize {
	private Integer id;
	private Integer userId;
	@JSONField(format="yyyy-MM-dd")
	private Date prizeTime;
	private String desc;
	private GreenCard greenCard;
	
	public GreenCard getGreenCard() {
		return greenCard;
	}
	public void setGreenCard(GreenCard greenCard) {
		this.greenCard = greenCard;
	}
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
	public Date getPrizeTime() {
		return prizeTime;
	}
	public void setPrizeTime(Date prizeTime) {
		this.prizeTime = prizeTime;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
