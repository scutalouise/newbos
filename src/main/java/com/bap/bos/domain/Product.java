package com.bap.bos.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 产品信息实体类
 * @author zhulong
 *
 */
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String Product_Num;
	private String Product_Name;
	private String Product_Type;
	private String Product_SellPrice;
	private String Product_CostPrice;
	private String Product_SupplierNum;
	private Date Product_EffectTime;
	private String Product_Active;
	private String Product_IsSync;
	private Date   Product_SyncDate;
	public String getProduct_Num() {
		return Product_Num;
	}
	public void setProduct_Num(String productNum) {
		Product_Num = productNum;
	}
	public String getProduct_Name() {
		return Product_Name;
	}
	public void setProduct_Name(String productName) {
		Product_Name = productName;
	}
	public String getProduct_Type() {
		return Product_Type;
	}
	public void setProduct_Type(String productType) {
		Product_Type = productType;
	}
	public String getProduct_SellPrice() {
		return Product_SellPrice;
	}
	public void setProduct_SellPrice(String productSellPrice) {
		Product_SellPrice = productSellPrice;
	}
	public String getProduct_CostPrice() {
		return Product_CostPrice;
	}
	public void setProduct_CostPrice(String productCostPrice) {
		Product_CostPrice = productCostPrice;
	}
	public String getProduct_SupplierNum() {
		return Product_SupplierNum;
	}
	public void setProduct_SupplierNum(String productSupplierNum) {
		Product_SupplierNum = productSupplierNum;
	}
	public Date getProduct_EffectTime() {
		return Product_EffectTime;
	}
	public void setProduct_EffectTime(Date productEffectTime) {
		Product_EffectTime = productEffectTime;
	}
	public String getProduct_Active() {
		return Product_Active;
	}
	public void setProduct_Active(String productActive) {
		Product_Active = productActive;
	}
	public String getProduct_IsSync() {
		return Product_IsSync;
	}
	public void setProduct_IsSync(String productIsSync) {
		Product_IsSync = productIsSync;
	}
	public Date getProduct_SyncDate() {
		return Product_SyncDate;
	}
	public void setProduct_SyncDate(Date productSyncDate) {
		Product_SyncDate = productSyncDate;
	}


	
	


	
}
