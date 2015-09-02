package com.bap.bos.service;

import java.util.List;

import com.bap.bos.domain.OilUnloadingData;
/**
 * 
 * @author edgar
 * @since 2015-3-16
 */
public interface OilUnloadingService {

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
	
	void saveOilUnloadingData(OilUnloadingData OilUnloadingData);
	
	
	/**
	 * 批量添加卸油数据
	 * @param oilUnloadingDataList
	 * @author edgar_chan 
	 * @since 2015-4-6
	 */
	void saveOilUnloadingData(List<OilUnloadingData> oilUnloadingDataList);
	
	
	/**
	 * 删除卸油数据
	 * @param orderNum
	 * @author edgar_chan
	 * @since 2015年4月14日
	 */
	void deleteOilUnloadingData(String orderNum,String status);
}
