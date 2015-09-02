package com.bap.bos.domain;

import java.io.Serializable;
import java.util.Date;
/**
 * 油品换号实体类
 * @author zhulong
 *
 */
public class ChangePetrol implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String ChangePetrol_No;
	private String ChangePetrol_StationNo;
	private String ChangePetrol_StaffNo;
	private String ChangePetrol_TankNum;
	private String ChangePetrol_NewPetrolNum;
	private String ChangePetrol_OldPetrolNum;
	private Date ChangePetrol_EffDate;
	private Date ChangePetrol_ChDate;
	private String ChangePetrol_SellPrice;
	public String getChangePetrol_No() {
		return ChangePetrol_No;
	}
	public void setChangePetrol_No(String changePetrolNo) {
		ChangePetrol_No = changePetrolNo;
	}
	public String getChangePetrol_StationNo() {
		return ChangePetrol_StationNo;
	}
	public void setChangePetrol_StationNo(String changePetrolStationNo) {
		ChangePetrol_StationNo = changePetrolStationNo;
	}
	public String getChangePetrol_StaffNo() {
		return ChangePetrol_StaffNo;
	}
	public void setChangePetrol_StaffNo(String changePetrolStaffNo) {
		ChangePetrol_StaffNo = changePetrolStaffNo;
	}
	public String getChangePetrol_TankNum() {
		return ChangePetrol_TankNum;
	}
	public void setChangePetrol_TankNum(String changePetrolTankNum) {
		ChangePetrol_TankNum = changePetrolTankNum;
	}
	public String getChangePetrol_NewPetrolNum() {
		return ChangePetrol_NewPetrolNum;
	}
	public void setChangePetrol_NewPetrolNum(String changePetrolNewPetrolNum) {
		ChangePetrol_NewPetrolNum = changePetrolNewPetrolNum;
	}
	public String getChangePetrol_OldPetrolNum() {
		return ChangePetrol_OldPetrolNum;
	}
	public void setChangePetrol_OldPetrolNum(String changePetrolOldPetrolNum) {
		ChangePetrol_OldPetrolNum = changePetrolOldPetrolNum;
	}
	public Date getChangePetrol_EffDate() {
		return ChangePetrol_EffDate;
	}
	public void setChangePetrol_EffDate(Date changePetrolEffDate) {
		ChangePetrol_EffDate = changePetrolEffDate;
	}
	public Date getChangePetrol_ChDate() {
		return ChangePetrol_ChDate;
	}
	public void setChangePetrol_ChDate(Date changePetrolChDate) {
		ChangePetrol_ChDate = changePetrolChDate;
	}
	public String getChangePetrol_SellPrice() {
		return ChangePetrol_SellPrice;
	}
	public void setChangePetrol_SellPrice(String changePetrolSellPrice) {
		ChangePetrol_SellPrice = changePetrolSellPrice;
	}
	
	

}
