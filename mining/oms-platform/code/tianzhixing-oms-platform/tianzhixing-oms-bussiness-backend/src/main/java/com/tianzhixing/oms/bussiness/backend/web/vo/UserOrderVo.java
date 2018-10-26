package com.tianzhixing.oms.bussiness.backend.web.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Created by routine.k on 2018/6/22.
 */
public class UserOrderVo implements Serializable {

	private Integer id;

	private Integer userId;

	private Integer productId;

	private String productName;

	private BigDecimal productPrice;

	private Integer productCnt;

	private String recipients;

	private String phoneNum;

	private String addrCountry;

	private String addrProvince;

	private String addrCity;

	private String addrDistrict;

	private String addrStreet;

	private String addrNum;

	private String addr;

	private String delivery;

	private String promotionCodeIds;

	private BigDecimal promotionPrice;

	private Integer isInvoice;

	private Integer invoiceType;

	private String invoiceIndividual;

	private String invoiceOrgName;

	private String invoiceContent;

	private String taxPayerCode;

	private String taxPayerRegAddr;

	private String taxPayerRegPhoneNum;

	private String taxPayerDepositBank;

	private String taxPayerBankAccount;

	private BigDecimal actualPay;

	private String payChannel;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date payDt;

	private Integer payStatus;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date paySuccessDt;

	private String orderMsg;

	private String orderNumber;

	private String invoiceTakerName;

	private String invoiceTakerPhone;

	private String invoiceTakerAddr;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date createDt;

	private Integer sampleTubeCnt;

	private Integer invoiceProperty;

	private Integer invoiceMakeType;

	private BigDecimal productPromotionPrice;

	public UserOrderVo() {
		super();
	}

	public UserOrderVo(Integer id, Integer userId, Integer productId, String productName, BigDecimal productPrice, Integer productCnt, String recipients, String phoneNum, String addrCountry, String addrProvince, String addrCity, String addrDistrict, String addrStreet, String addrNum, String addr, String delivery, String promotionCodeIds, BigDecimal promotionPrice, Integer isInvoice, Integer invoiceType, String invoiceIndividual, String invoiceOrgName, String invoiceContent, String taxPayerCode, String taxPayerRegAddr, String taxPayerRegPhoneNum, String taxPayerDepositBank, String taxPayerBankAccount, BigDecimal actualPay, String payChannel, Date payDt, Integer payStatus, Date paySuccessDt, String orderMsg, String orderNumber, String invoiceTakerName, String invoiceTakerPhone, String invoiceTakerAddr, Date createDt, Integer sampleTubeCnt, Integer invoiceProperty, Integer invoiceMakeType, BigDecimal productPromotionPrice) {
		super();
		this.id = id;
		this.userId = userId;
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productCnt = productCnt;
		this.recipients = recipients;
		this.phoneNum = phoneNum;
		this.addrCountry = addrCountry;
		this.addrProvince = addrProvince;
		this.addrCity = addrCity;
		this.addrDistrict = addrDistrict;
		this.addrStreet = addrStreet;
		this.addrNum = addrNum;
		this.addr = addr;
		this.delivery = delivery;
		this.promotionCodeIds = promotionCodeIds;
		this.promotionPrice = promotionPrice;
		this.isInvoice = isInvoice;
		this.invoiceType = invoiceType;
		this.invoiceIndividual = invoiceIndividual;
		this.invoiceOrgName = invoiceOrgName;
		this.invoiceContent = invoiceContent;
		this.taxPayerCode = taxPayerCode;
		this.taxPayerRegAddr = taxPayerRegAddr;
		this.taxPayerRegPhoneNum = taxPayerRegPhoneNum;
		this.taxPayerDepositBank = taxPayerDepositBank;
		this.taxPayerBankAccount = taxPayerBankAccount;
		this.actualPay = actualPay;
		this.payChannel = payChannel;
		this.payDt = payDt;
		this.payStatus = payStatus;
		this.paySuccessDt = paySuccessDt;
		this.orderMsg = orderMsg;
		this.orderNumber = orderNumber;
		this.invoiceTakerName = invoiceTakerName;
		this.invoiceTakerPhone = invoiceTakerPhone;
		this.invoiceTakerAddr = invoiceTakerAddr;
		this.createDt = createDt;
		this.sampleTubeCnt = sampleTubeCnt;
		this.invoiceProperty = invoiceProperty;
		this.invoiceMakeType = invoiceMakeType;
		this.productPromotionPrice = productPromotionPrice;
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

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public Integer getProductCnt() {
		return productCnt;
	}

	public void setProductCnt(Integer productCnt) {
		this.productCnt = productCnt;
	}

	public String getRecipients() {
		return recipients;
	}

	public void setRecipients(String recipients) {
		this.recipients = recipients;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getAddrCountry() {
		return addrCountry;
	}

	public void setAddrCountry(String addrCountry) {
		this.addrCountry = addrCountry;
	}

	public String getAddrProvince() {
		return addrProvince;
	}

	public void setAddrProvince(String addrProvince) {
		this.addrProvince = addrProvince;
	}

	public String getAddrCity() {
		return addrCity;
	}

	public void setAddrCity(String addrCity) {
		this.addrCity = addrCity;
	}

	public String getAddrDistrict() {
		return addrDistrict;
	}

	public void setAddrDistrict(String addrDistrict) {
		this.addrDistrict = addrDistrict;
	}

	public String getAddrStreet() {
		return addrStreet;
	}

	public void setAddrStreet(String addrStreet) {
		this.addrStreet = addrStreet;
	}

	public String getAddrNum() {
		return addrNum;
	}

	public void setAddrNum(String addrNum) {
		this.addrNum = addrNum;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getDelivery() {
		return delivery;
	}

	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}

	public String getPromotionCodeIds() {
		return promotionCodeIds;
	}

	public void setPromotionCodeIds(String promotionCodeIds) {
		this.promotionCodeIds = promotionCodeIds;
	}

	public BigDecimal getPromotionPrice() {
		return promotionPrice;
	}

	public void setPromotionPrice(BigDecimal promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

	public Integer getIsInvoice() {
		return isInvoice;
	}

	public void setIsInvoice(Integer isInvoice) {
		this.isInvoice = isInvoice;
	}

	public Integer getInvoiceType() {
		return invoiceType;
	}

	public void setInvoiceType(Integer invoiceType) {
		this.invoiceType = invoiceType;
	}

	public String getInvoiceIndividual() {
		return invoiceIndividual;
	}

	public void setInvoiceIndividual(String invoiceIndividual) {
		this.invoiceIndividual = invoiceIndividual;
	}

	public String getInvoiceOrgName() {
		return invoiceOrgName;
	}

	public void setInvoiceOrgName(String invoiceOrgName) {
		this.invoiceOrgName = invoiceOrgName;
	}

	public String getInvoiceContent() {
		return invoiceContent;
	}

	public void setInvoiceContent(String invoiceContent) {
		this.invoiceContent = invoiceContent;
	}

	public String getTaxPayerCode() {
		return taxPayerCode;
	}

	public void setTaxPayerCode(String taxPayerCode) {
		this.taxPayerCode = taxPayerCode;
	}

	public String getTaxPayerRegAddr() {
		return taxPayerRegAddr;
	}

	public void setTaxPayerRegAddr(String taxPayerRegAddr) {
		this.taxPayerRegAddr = taxPayerRegAddr;
	}

	public String getTaxPayerRegPhoneNum() {
		return taxPayerRegPhoneNum;
	}

	public void setTaxPayerRegPhoneNum(String taxPayerRegPhoneNum) {
		this.taxPayerRegPhoneNum = taxPayerRegPhoneNum;
	}

	public String getTaxPayerDepositBank() {
		return taxPayerDepositBank;
	}

	public void setTaxPayerDepositBank(String taxPayerDepositBank) {
		this.taxPayerDepositBank = taxPayerDepositBank;
	}

	public String getTaxPayerBankAccount() {
		return taxPayerBankAccount;
	}

	public void setTaxPayerBankAccount(String taxPayerBankAccount) {
		this.taxPayerBankAccount = taxPayerBankAccount;
	}

	public BigDecimal getActualPay() {
		return actualPay;
	}

	public void setActualPay(BigDecimal actualPay) {
		this.actualPay = actualPay;
	}

	public String getPayChannel() {
		return payChannel;
	}

	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}

	public Date getPayDt() {
		return payDt;
	}

	public void setPayDt(Date payDt) {
		this.payDt = payDt;
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		this.payStatus = payStatus;
	}

	public Date getPaySuccessDt() {
		return paySuccessDt;
	}

	public void setPaySuccessDt(Date paySuccessDt) {
		this.paySuccessDt = paySuccessDt;
	}

	public String getOrderMsg() {
		return orderMsg;
	}

	public void setOrderMsg(String orderMsg) {
		this.orderMsg = orderMsg;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getInvoiceTakerName() {
		return invoiceTakerName;
	}

	public void setInvoiceTakerName(String invoiceTakerName) {
		this.invoiceTakerName = invoiceTakerName;
	}

	public String getInvoiceTakerPhone() {
		return invoiceTakerPhone;
	}

	public void setInvoiceTakerPhone(String invoiceTakerPhone) {
		this.invoiceTakerPhone = invoiceTakerPhone;
	}

	public String getInvoiceTakerAddr() {
		return invoiceTakerAddr;
	}

	public void setInvoiceTakerAddr(String invoiceTakerAddr) {
		this.invoiceTakerAddr = invoiceTakerAddr;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public Integer getSampleTubeCnt() {
		return sampleTubeCnt;
	}

	public void setSampleTubeCnt(Integer sampleTubeCnt) {
		this.sampleTubeCnt = sampleTubeCnt;
	}

	public Integer getInvoiceProperty() {
		return invoiceProperty;
	}

	public void setInvoiceProperty(Integer invoiceProperty) {
		this.invoiceProperty = invoiceProperty;
	}

	public Integer getInvoiceMakeType() {
		return invoiceMakeType;
	}

	public void setInvoiceMakeType(Integer invoiceMakeType) {
		this.invoiceMakeType = invoiceMakeType;
	}

	public BigDecimal getProductPromotionPrice() {
		return productPromotionPrice;
	}

	public void setProductPromotionPrice(BigDecimal productPromotionPrice) {
		this.productPromotionPrice = productPromotionPrice;
	}

}
