package com.tianzhixing.oms.bussiness.backend.web.vo;

import java.io.Serializable;

/**
 * Created by routine.k on 2018/6/22.
 */
public class CourseDetailVo implements Serializable {

	private Long id;

	private String videoLink;

	private Integer productId;

	private Integer courseType;

	private Integer videoNum;

	private String coursePic;

	private String courseComment;
	
	private Integer enable;

	private String videoName;

	private String productName;

	public CourseDetailVo() {
		super();
	}

	public CourseDetailVo(Long id, String videoLink, Integer productId, Integer courseType, Integer videoNum, String coursePic, String courseComment, Integer enable, String videoName, String productName) {
		super();
		this.id = id;
		this.videoLink = videoLink;
		this.productId = productId;
		this.courseType = courseType;
		this.videoNum = videoNum;
		this.coursePic = coursePic;
		this.courseComment = courseComment;
		this.enable = enable;
		this.videoName = videoName;
		this.productName = productName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getVideoLink() {
		return videoLink;
	}

	public void setVideoLink(String videoLink) {
		this.videoLink = videoLink;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getCourseType() {
		return courseType;
	}

	public void setCourseType(Integer courseType) {
		this.courseType = courseType;
	}

	public Integer getVideoNum() {
		return videoNum;
	}

	public void setVideoNum(Integer videoNum) {
		this.videoNum = videoNum;
	}

	public String getCoursePic() {
		return coursePic;
	}

	public void setCoursePic(String coursePic) {
		this.coursePic = coursePic;
	}

	public String getCourseComment() {
		return courseComment;
	}

	public void setCourseComment(String courseComment) {
		this.courseComment = courseComment;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

}
