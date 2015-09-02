package com.bap.bos.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 职位设定实体类
 * @author zhulong
 *
 */
public class PositionType implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String PositionType_Num;
	private String PositionType_Desc;
	private String PositionType_IsSync;
	private Date   PositionType_SyncDate;
	public String getPositionType_Num() {
		return PositionType_Num;
	}
	public void setPositionType_Num(String positionTypeNum) {
		PositionType_Num = positionTypeNum;
	}
	public String getPositionType_Desc() {
		return PositionType_Desc;
	}
	public void setPositionType_Desc(String positionTypeDesc) {
		PositionType_Desc = positionTypeDesc;
	}
	public String getPositionType_IsSync() {
		return PositionType_IsSync;
	}
	public void setPositionType_IsSync(String positionTypeIsSync) {
		PositionType_IsSync = positionTypeIsSync;
	}
	public Date getPositionType_SyncDate() {
		return PositionType_SyncDate;
	}
	public void setPositionType_SyncDate(Date positionTypeSyncDate) {
		PositionType_SyncDate = positionTypeSyncDate;
	}
	


	
	


	
}
