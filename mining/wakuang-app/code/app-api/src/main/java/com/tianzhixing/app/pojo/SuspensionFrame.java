package com.tianzhixing.app.pojo;

/**
 * 悬浮窗数据获取
 * 
 * @author dev-lan
 *
 */
public class SuspensionFrame {
	private Integer id;
	private String frame_icon; // 图片
	private String link; // 链接
	private String begin_datetime; // 弹出时间
	private String end_datetime; // 结束时间
	private String frame_word; // 弹出文案
	private String add_datetime; // 添加时间

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public String getFrame_icon() {
		return frame_icon;
	}

	public void setFrame_icon(String frame_icon) {
		this.frame_icon = frame_icon;
	}

	public String getFrame_word() {
		return frame_word;
	}

	public void setFrame_word(String frame_word) {
		this.frame_word = frame_word;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getBegin_datetime() {
		return begin_datetime;
	}

	public void setBegin_datetime(String begin_datetime) {
		this.begin_datetime = begin_datetime;
	}

	public String getEnd_datetime() {
		return end_datetime;
	}

	public void setEnd_datetime(String end_datetime) {
		this.end_datetime = end_datetime;
	}

	

	public String getAdd_datetime() {
		return add_datetime;
	}

	public void setAdd_datetime(String add_datetime) {
		this.add_datetime = add_datetime;
	}

}
