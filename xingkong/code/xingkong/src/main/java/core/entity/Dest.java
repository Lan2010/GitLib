package core.entity;

import java.util.List;

/**
 * 景点信息
 * 
 * @author dev-lan
 * @date 2018年6月9日
 */
public class Dest {
	private Integer dest_id;// 自增主键
	private String dest_name;// 景点名称
	private String dest_logo; // 景点logo
	private String dest_desc; // 景点详情介绍
	private List<String> dest_img; // 详情图片
	private Integer category; // 景点类别，对地--0 对星空 --1
	private Integer status; // 景点状态

	public Integer getDest_id() {
		return dest_id;
	}

	public void setDest_id(Integer dest_id) {
		this.dest_id = dest_id;
	}

	public String getDest_name() {
		return dest_name;
	}

	public void setDest_name(String dest_name) {
		this.dest_name = dest_name;
	}

	public String getDest_logo() {
		return dest_logo;
	}

	public void setDest_logo(String dest_logo) {
		this.dest_logo = dest_logo;
	}

	public String getDest_desc() {
		return dest_desc;
	}

	public void setDest_desc(String dest_desc) {
		this.dest_desc = dest_desc;
	}

	public List<String> getDest_img() {
		return dest_img;
	}

	public void setDest_img(List<String> dest_img) {
		this.dest_img = dest_img;
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
