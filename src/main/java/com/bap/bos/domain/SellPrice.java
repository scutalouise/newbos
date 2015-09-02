package com.bap.bos.domain;

import java.io.Serializable;

/**
 * 当前销售价格实体类
 * @author zhulong
 *
 */
public class SellPrice implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String SellPrice_ProductNum;
	private String SellPrice_Phy_Noz;
	private float SellPrice_SellPrice;
	public String getSellPrice_ProductNum() {
		return SellPrice_ProductNum;
	}
	public void setSellPrice_ProductNum(String sellPriceProductNum) {
		SellPrice_ProductNum = sellPriceProductNum;
	}
	public float getSellPrice_SellPrice() {
		return SellPrice_SellPrice;
	}
	public void setSellPrice_SellPrice(float sellPriceSellPrice) {
		SellPrice_SellPrice = sellPriceSellPrice;
	}
	public String getSellPrice_Phy_Noz() {
		return SellPrice_Phy_Noz;
	}
	public void setSellPrice_Phy_Noz(String sellPricePhyNoz) {
		SellPrice_Phy_Noz = sellPricePhyNoz;
	}
	
}
