package com.bap.bos.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 气站实体类
 * 
 * @author zhulong
 * 
 */
public class Station implements Serializable {

	private static final long serialVersionUID = 1L;

	private String Station_No;
	private String Station_Name;
	private String Station_Aliases;
	private String Station_municipalityNo;
	private String Station_Address;
	private String Station_Phone1;
	private String Station_Phone2;
	private String Station_Fax;
	private String Station_Type;
	private String Station_IP;
	private String Station_BusinessModel;
	private String Station_Class;
	private String Station_IsSync;
	private Date Station_SyncDate;

	public String getStation_No() {
		return Station_No;
	}

	public void setStation_No(String stationNo) {
		Station_No = stationNo;
	}

	public String getStation_Name() {
		return Station_Name;
	}

	public void setStation_Name(String stationName) {
		Station_Name = stationName;
	}

	public String getStation_municipalityNo() {
		return Station_municipalityNo;
	}

	public void setStation_municipalityNo(String stationMunicipalityNo) {
		Station_municipalityNo = stationMunicipalityNo;
	}

	public String getStation_Address() {
		return Station_Address;
	}

	public void setStation_Address(String stationAddress) {
		Station_Address = stationAddress;
	}

	public String getStation_Phone1() {
		return Station_Phone1;
	}

	public void setStation_Phone1(String stationPhone1) {
		Station_Phone1 = stationPhone1;
	}

	public String getStation_Phone2() {
		return Station_Phone2;
	}

	public void setStation_Phone2(String stationPhone2) {
		Station_Phone2 = stationPhone2;
	}

	public String getStation_Fax() {
		return Station_Fax;
	}

	public void setStation_Fax(String stationFax) {
		Station_Fax = stationFax;
	}

	public String getStation_Type() {
		return Station_Type;
	}

	public void setStation_Type(String stationType) {
		Station_Type = stationType;
	}

	public String getStation_IP() {
		return Station_IP;
	}

	public void setStation_IP(String stationIP) {
		Station_IP = stationIP;
	}

	public String getStation_IsSync() {
		return Station_IsSync;
	}

	public void setStation_IsSync(String stationIsSync) {
		Station_IsSync = stationIsSync;
	}

	public Date getStation_SyncDate() {
		return Station_SyncDate;
	}

	public void setStation_SyncDate(Date stationSyncDate) {
		Station_SyncDate = stationSyncDate;
	}

	public String getStation_Aliases() {
		return Station_Aliases;
	}

	public void setStation_Aliases(String stationAliases) {
		Station_Aliases = stationAliases;
	}

	public String getStation_BusinessModel() {
		return Station_BusinessModel;
	}

	public void setStation_BusinessModel(String stationBusinessModel) {
		Station_BusinessModel = stationBusinessModel;
	}

	public String getStation_Class() {
		return Station_Class;
	}

	public void setStation_Class(String stationClass) {
		Station_Class = stationClass;
	}

}
