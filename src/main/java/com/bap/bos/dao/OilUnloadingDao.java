package com.bap.bos.dao;

import java.util.List;

import com.bap.bos.domain.OilUnloadingData;

public interface OilUnloadingDao {

	/**
	 * 查询卸油数据
	 * @param OrderNum	订单号
	 * @return
	 */
	
	public List<OilUnloadingData> selOilUnloadingData(String OrderNum);
	
	/**
	 * 添加卸油数据
	 * @param OilUnloadingData
	 */
	
	public void saveOilUnloadingData(OilUnloadingData OilUnloadingData);
	
	/**
	 * 
	 * @param orderNum
	 * @author edgar_chan
	 * @since 2015年4月14日
	 */
	public void deleteOilUnloadingData(String orderNum,String status);

}