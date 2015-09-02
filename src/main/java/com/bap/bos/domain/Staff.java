package com.bap.bos.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 员工信息实体类
 * @author zhulong
 *
 */
public class Staff implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String Staff_No;
	private String Staff_CardNo;
	private String Staff_Name;
	private String Staff_Sex;
	private String Staff_Phone;
	private String Staff_ID;
	private String Staff_Address;
	private String Staff_Position;
	private String Staff_PW;
	private Date   Staff_JoinTime;
	private Date   Staff_LeftTime;
	private String Staff_IsSync;
	private Date   Staff_SyncDate;
	public String getStaff_No() {
		return Staff_No;
	}
	public void setStaff_No(String staffNo) {
		Staff_No = staffNo;
	}
	public String getStaff_CardNo() {
		return Staff_CardNo;
	}
	public void setStaff_CardNo(String staffCardNo) {
		Staff_CardNo = staffCardNo;
	}
	public String getStaff_Name() {
		return Staff_Name;
	}
	public void setStaff_Name(String staffName) {
		Staff_Name = staffName;
	}
	public String getStaff_Phone() {
		return Staff_Phone;
	}
	public void setStaff_Phone(String staffPhone) {
		Staff_Phone = staffPhone;
	}
	public String getStaff_ID() {
		return Staff_ID;
	}
	public void setStaff_ID(String staffID) {
		Staff_ID = staffID;
	}
	public String getStaff_Address() {
		return Staff_Address;
	}
	public void setStaff_Address(String staffAddress) {
		Staff_Address = staffAddress;
	}
	public String getStaff_Position() {
		return Staff_Position;
	}
	public void setStaff_Position(String staffPosition) {
		Staff_Position = staffPosition;
	}
	
	public String getStaff_PW() {
		return Staff_PW;
	}
	public void setStaff_PW(String staffPW) {
		Staff_PW = staffPW;
	}
	public Date getStaff_JoinTime() {
		return Staff_JoinTime;
	}
	public void setStaff_JoinTime(Date staffJoinTime) {
		Staff_JoinTime = staffJoinTime;
	}
	public Date getStaff_LeftTime() {
		return Staff_LeftTime;
	}
	public void setStaff_LeftTime(Date staffLeftTime) {
		Staff_LeftTime = staffLeftTime;
	}
	public String getStaff_IsSync() {
		return Staff_IsSync;
	}
	public void setStaff_IsSync(String staffIsSync) {
		Staff_IsSync = staffIsSync;
	}
	public Date getStaff_SyncDate() {
		return Staff_SyncDate;
	}
	public void setStaff_SyncDate(Date staffSyncDate) {
		Staff_SyncDate = staffSyncDate;
	}
	public String getStaff_Sex() {
		return Staff_Sex;
	}
	public void setStaff_Sex(String staffSex) {
		Staff_Sex = staffSex;
	}


	
	


	
}
