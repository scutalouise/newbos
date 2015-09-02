package com.bap.bos.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 销售价格变价预设实体类
 * @author zhulong
 *
 */
public class SettingSellPrice implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String SettingSellPrice_StationNo;
	private String SettingSellPrice_ProductNum;
	private String SettingSellPrice_StaffNo;
	private Date SettingSellPrice_EffDate;
	private Date SettingSellPrice_ChDate;
	private float SettingSellPrice_SellPrice;
	private String SettingSellPrice_IsExec;
	private String SettingSellPrice_IsSync;
	private Date SettingSellPrice_SyncDate;
	public String getSettingSellPrice_StationNo() {
		return SettingSellPrice_StationNo;
	}
	public void setSettingSellPrice_StationNo(String settingSellPriceStationNo) {
		SettingSellPrice_StationNo = settingSellPriceStationNo;
	}
	public String getSettingSellPrice_ProductNum() {
		return SettingSellPrice_ProductNum;
	}
	public void setSettingSellPrice_ProductNum(String settingSellPriceProductNum) {
		SettingSellPrice_ProductNum = settingSellPriceProductNum;
	}
	public String getSettingSellPrice_StaffNo() {
		return SettingSellPrice_StaffNo;
	}
	public void setSettingSellPrice_StaffNo(String settingSellPriceStaffNo) {
		SettingSellPrice_StaffNo = settingSellPriceStaffNo;
	}
	public Date getSettingSellPrice_EffDate() {
		return SettingSellPrice_EffDate;
	}
	public void setSettingSellPrice_EffDate(Date settingSellPriceEffDate) {
		SettingSellPrice_EffDate = settingSellPriceEffDate;
	}
	public Date getSettingSellPrice_ChDate() {
		return SettingSellPrice_ChDate;
	}
	public void setSettingSellPrice_ChDate(Date settingSellPriceChDate) {
		SettingSellPrice_ChDate = settingSellPriceChDate;
	}
	public float getSettingSellPrice_SellPrice() {
		return SettingSellPrice_SellPrice;
	}
	public void setSettingSellPrice_SellPrice(float settingSellPriceSellPrice) {
		SettingSellPrice_SellPrice = settingSellPriceSellPrice;
	}
	public String getSettingSellPrice_IsExec() {
		return SettingSellPrice_IsExec;
	}
	public void setSettingSellPrice_IsExec(String settingSellPriceIsExec) {
		SettingSellPrice_IsExec = settingSellPriceIsExec;
	}
	public String getSettingSellPrice_IsSync() {
		return SettingSellPrice_IsSync;
	}
	public void setSettingSellPrice_IsSync(String settingSellPriceIsSync) {
		SettingSellPrice_IsSync = settingSellPriceIsSync;
	}
	public Date getSettingSellPrice_SyncDate() {
		return SettingSellPrice_SyncDate;
	}
	public void setSettingSellPrice_SyncDate(Date settingSellPriceSyncDate) {
		SettingSellPrice_SyncDate = settingSellPriceSyncDate;
	}
	
	
	
}
