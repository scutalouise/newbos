package com.bap.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bap.bos.dao.OilUnloadingDao;
import com.bap.bos.domain.OilUnloadingData;
import com.bap.bos.service.OilUnloadingService;

@Service
public class OilUnloadingServiceImpl implements OilUnloadingService{

	@Resource
	private OilUnloadingDao oilUnloadingDao;

	@Override
	@Transactional(readOnly=true)
	public List<OilUnloadingData> selOilUnloadingData(String OrderNum) {
		return oilUnloadingDao.selOilUnloadingData(OrderNum);
	}

	@Override
	@Transactional
	public void saveOilUnloadingData(OilUnloadingData OilUnloadingData) {
		oilUnloadingDao.saveOilUnloadingData(OilUnloadingData);
	}
	
	@Override
	@Transactional
	public void saveOilUnloadingData(List<OilUnloadingData> oilUnloadingDataList) {
		for(OilUnloadingData oilUnloadingData : oilUnloadingDataList){
			oilUnloadingDao.saveOilUnloadingData(oilUnloadingData);
		}
	}
	
	/**
	 * 删除卸油数据
	 * @param orderNum
	 * @author edgar_chan
	 * @since 2015年4月14日
	 */
	public void deleteOilUnloadingData(String orderNum,String status){
		oilUnloadingDao.deleteOilUnloadingData(orderNum, status);
	}
	
}
