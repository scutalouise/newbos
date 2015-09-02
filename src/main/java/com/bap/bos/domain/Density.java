package com.bap.bos.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Transient;

/**
 * 平均密度实体类
 * @author zhulong
 *
 */
public class Density implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String Density_ProdunctNum;
	private String Density_StationNo;
	private Double Density_Density;
	private Date Density_StartDate;
	private Date Density_endDate;
	private String Density_IsSync;
	private Date Density_SyncDate;
	private Double densityManual; //手动修正密度
	@Transient
	private String productName; 
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDensity_ProdunctNum() {
		return Density_ProdunctNum;
	}
	public void setDensity_ProdunctNum(String densityProdunctNum) {
		Density_ProdunctNum = densityProdunctNum;
	}
	public Double getDensity_Density() {
		return Density_Density;
	}
	public void setDensity_Density(Double density_Density) {
		Density_Density = density_Density;
	}
	public Double getDensityManual() {
		return densityManual;
	}
	public void setDensityManual(Double densityManual) {
		this.densityManual = densityManual;
	}
	public Date getDensity_StartDate() {
		return Density_StartDate;
	}
	public void setDensity_StartDate(Date density_StartDate) {
		Density_StartDate = density_StartDate;
	}
	public Date getDensity_endDate() {
		return Density_endDate;
	}
	public void setDensity_endDate(Date density_endDate) {
		Density_endDate = density_endDate;
	}
	public String getDensity_IsSync() {
		return Density_IsSync;
	}
	public void setDensity_IsSync(String density_IsSync) {
		Density_IsSync = density_IsSync;
	}
	public Date getDensity_SyncDate() {
		return Density_SyncDate;
	}
	public void setDensity_SyncDate(Date density_SyncDate) {
		Density_SyncDate = density_SyncDate;
	}
	public String getDensity_StationNo() {
		return Density_StationNo;
	}
	public void setDensity_StationNo(String density_StationNo) {
		Density_StationNo = density_StationNo;
	}

	public String getProductName() {
		return productName;
	}
	
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public Density(String density_ProdunctNum, String density_StationNo, Date density_startDate) {
		Density_ProdunctNum = density_ProdunctNum;
		Density_StationNo = density_StationNo;
		Density_StartDate = density_startDate;
	}
	
	public Density() {
	}
	
}
