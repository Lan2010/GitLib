package com.tianzhixing.oms.bussiness.backend.web.vo;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by routine.k on 2018/6/22.
 */
public class UserGeneInterprVo implements Serializable {

	private Integer id;

	private String sampleTubeCode;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date geneChipFileDt;

	private String geneInterprFile;

	private String geneInterprJson;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date geneInterprCreateDt;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date geneInterprUpdateDt;

	private Boolean isValidSample;

	private Integer isProcessing;

	private String resultHtml;

	private String resultPdf;

	private String productType;

	public UserGeneInterprVo() {
	}

	public UserGeneInterprVo(Integer id, String sampleTubeCode, Date geneChipFileDt, String geneInterprFile, String geneInterprJson, Date geneInterprCreateDt, Date geneInterprUpdateDt, Boolean isValidSample, Integer isProcessing, String resultHtml, String resultPdf, String productType) {
		super();
		this.id = id;
		this.sampleTubeCode = sampleTubeCode;
		this.geneChipFileDt = geneChipFileDt;
		this.geneInterprFile = geneInterprFile;
		this.geneInterprJson = geneInterprJson;
		this.geneInterprCreateDt = geneInterprCreateDt;
		this.geneInterprUpdateDt = geneInterprUpdateDt;
		this.isValidSample = isValidSample;
		this.isProcessing = isProcessing;
		this.resultHtml = resultHtml;
		this.resultPdf = resultPdf;
		this.productType = productType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSampleTubeCode() {
		return sampleTubeCode;
	}

	public void setSampleTubeCode(String sampleTubeCode) {
		this.sampleTubeCode = sampleTubeCode;
	}

	public Date getGeneChipFileDt() {
		return geneChipFileDt;
	}

	public void setGeneChipFileDt(Date geneChipFileDt) {
		this.geneChipFileDt = geneChipFileDt;
	}

	public String getGeneInterprFile() {
		return geneInterprFile;
	}

	public void setGeneInterprFile(String geneInterprFile) {
		this.geneInterprFile = geneInterprFile;
	}

	public String getGeneInterprJson() {
		return geneInterprJson;
	}

	public void setGeneInterprJson(String geneInterprJson) {
		this.geneInterprJson = geneInterprJson;
	}

	public Date getGeneInterprCreateDt() {
		return geneInterprCreateDt;
	}

	public void setGeneInterprCreateDt(Date geneInterprCreateDt) {
		this.geneInterprCreateDt = geneInterprCreateDt;
	}

	public Date getGeneInterprUpdateDt() {
		return geneInterprUpdateDt;
	}

	public void setGeneInterprUpdateDt(Date geneInterprUpdateDt) {
		this.geneInterprUpdateDt = geneInterprUpdateDt;
	}

	public Boolean getIsValidSample() {
		return isValidSample;
	}

	public void setIsValidSample(Boolean isValidSample) {
		this.isValidSample = isValidSample;
	}

	public Integer getIsProcessing() {
		return isProcessing;
	}

	public void setIsProcessing(Integer isProcessing) {
		this.isProcessing = isProcessing;
	}

	public String getResultHtml() {
		return resultHtml;
	}

	public void setResultHtml(String resultHtml) {
		this.resultHtml = resultHtml;
	}

	public String getResultPdf() {
		return resultPdf;
	}

	public void setResultPdf(String resultPdf) {
		this.resultPdf = resultPdf;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

}
