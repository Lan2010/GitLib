package com.tianzhixing.oms.bussiness.backend.web.vo;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by routine.k on 2018/6/22.
 */
public class ProductVo implements Serializable {

	private Integer id;

	private String productName;

	private BigDecimal price;

	private BigDecimal promotionPrice;

	private Integer productStatus;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date sellDt;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date lastModifyDt;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date noSellDt;

	private Integer sampleTubeCnt;

	private Integer productType;

	private Integer productCreatePlatform;

	private String youzanItemId;

	private String productPic;

	private String productComment;

	public ProductVo() {
	}

	public ProductVo(Integer id, String productName, BigDecimal price, BigDecimal promotionPrice, Integer productStatus, Date sellDt, Date lastModifyDt, Date noSellDt, Integer sampleTubeCnt, Integer productType, Integer productCreatePlatform, String youzanItemId, String productPic, String productComment) {
		super();
		this.id = id;
		this.productName = productName;
		this.price = price;
		this.promotionPrice = promotionPrice;
		this.productStatus = productStatus;
		this.sellDt = sellDt;
		this.lastModifyDt = lastModifyDt;
		this.noSellDt = noSellDt;
		this.sampleTubeCnt = sampleTubeCnt;
		this.productType = productType;
		this.productCreatePlatform = productCreatePlatform;
		this.youzanItemId = youzanItemId;
		this.productPic = productPic;
		this.productComment = productComment;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getPromotionPrice() {
		return promotionPrice;
	}

	public void setPromotionPrice(BigDecimal promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

	public Integer getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(Integer productStatus) {
		this.productStatus = productStatus;
	}

	public Date getSellDt() {
		return sellDt;
	}

	public void setSellDt(Date sellDt) {
		this.sellDt = sellDt;
	}

	public Date getLastModifyDt() {
		return lastModifyDt;
	}

	public void setLastModifyDt(Date lastModifyDt) {
		this.lastModifyDt = lastModifyDt;
	}

	public Date getNoSellDt() {
		return noSellDt;
	}

	public void setNoSellDt(Date noSellDt) {
		this.noSellDt = noSellDt;
	}

	public Integer getSampleTubeCnt() {
		return sampleTubeCnt;
	}

	public void setSampleTubeCnt(Integer sampleTubeCnt) {
		this.sampleTubeCnt = sampleTubeCnt;
	}

	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	public Integer getProductCreatePlatform() {
		return productCreatePlatform;
	}

	public void setProductCreatePlatform(Integer productCreatePlatform) {
		this.productCreatePlatform = productCreatePlatform;
	}

	public String getYouzanItemId() {
		return youzanItemId;
	}

	public void setYouzanItemId(String youzanItemId) {
		this.youzanItemId = youzanItemId;
	}

	public String getProductPic() {
		return productPic;
	}

	public void setProductPic(String productPic) {
		this.productPic = productPic;
	}

	public String getProductComment() {
		return productComment;
	}

	public void setProductComment(String productComment) {
		this.productComment = productComment;
	}

}
