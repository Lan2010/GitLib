package com.tianzhixing.oms.bussiness.backend.web.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Created by routine.k on 2018/6/22.
 */
public class PromotionCodeVo implements Serializable {

	private Integer id;

	private String promotionCode;

	private String promotionRules;

	private Byte isExpired;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date expiryDt;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date createDt;

	private Integer forProductionId;

	private BigDecimal voucherAmount;

	private BigDecimal discountRate;

	private Integer promotionCodeType;

	private Integer allowComposition;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date beginDt;
	
	private String productName;

	public PromotionCodeVo() {
		super();
	}

	public PromotionCodeVo(Integer id, String promotionCode, String promotionRules, Byte isExpired, Date expiryDt, Date createDt, Integer forProductionId, BigDecimal voucherAmount, BigDecimal discountRate, Integer promotionCodeType, Integer allowComposition, Date beginDt, String productName) {
		super();
		this.id = id;
		this.promotionCode = promotionCode;
		this.promotionRules = promotionRules;
		this.isExpired = isExpired;
		this.expiryDt = expiryDt;
		this.createDt = createDt;
		this.forProductionId = forProductionId;
		this.voucherAmount = voucherAmount;
		this.discountRate = discountRate;
		this.promotionCodeType = promotionCodeType;
		this.allowComposition = allowComposition;
		this.beginDt = beginDt;
		this.productName = productName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPromotionCode() {
		return promotionCode;
	}

	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}

	public String getPromotionRules() {
		return promotionRules;
	}

	public void setPromotionRules(String promotionRules) {
		this.promotionRules = promotionRules;
	}

	public Byte getIsExpired() {
		return isExpired;
	}

	public void setIsExpired(Byte isExpired) {
		this.isExpired = isExpired;
	}

	public Date getExpiryDt() {
		return expiryDt;
	}

	public void setExpiryDt(Date expiryDt) {
		this.expiryDt = expiryDt;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public Integer getForProductionId() {
		return forProductionId;
	}

	public void setForProductionId(Integer forProductionId) {
		this.forProductionId = forProductionId;
	}

	public BigDecimal getVoucherAmount() {
		return voucherAmount;
	}

	public void setVoucherAmount(BigDecimal voucherAmount) {
		this.voucherAmount = voucherAmount;
	}

	public BigDecimal getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(BigDecimal discountRate) {
		this.discountRate = discountRate;
	}

	public Integer getPromotionCodeType() {
		return promotionCodeType;
	}

	public void setPromotionCodeType(Integer promotionCodeType) {
		this.promotionCodeType = promotionCodeType;
	}

	public Integer getAllowComposition() {
		return allowComposition;
	}

	public void setAllowComposition(Integer allowComposition) {
		this.allowComposition = allowComposition;
	}

	public Date getBeginDt() {
		return beginDt;
	}

	public void setBeginDt(Date beginDt) {
		this.beginDt = beginDt;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

}
