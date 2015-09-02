package com.bap.bos.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.bap.bos.dao.ReferredCubageDao;
import com.bap.bos.domain.ReferredCubage;
import com.bap.bos.service.ReferredCubageService;
import com.bap.bos.util.Page;

/**
 * 
 * 
 * @author edgar_chan     lineshow7@gmail.com
 * @since 2015年4月3日
 */
@Service
public class ReferredCubageServiceImpl implements ReferredCubageService{

	@Resource
	private ReferredCubageDao referredCubageDao;

	@Override
	@Transactional(readOnly=true)
	public ReferredCubage get(Long id) {
		return referredCubageDao.get(id);
	}

	@Override
	@Transactional(readOnly=true)
	public List<ReferredCubage> get(Page page) {
		return referredCubageDao.get(page);
	}

	@Override
	@Transactional
	public String add(ReferredCubage referredCubage) {
		
		if(!isReasonableCubage(referredCubage))
			return "unreasonableCubage";
		
		referredCubageDao.fakeDelete(referredCubage.getTankSetting().getTanksetting_StationNo(),referredCubage.getTankSetting().getTanksetting_TankNum() , referredCubage.getHeight());
		referredCubageDao.add(referredCubage);

		return null;
	}
	
	/**
	 * 校验用户输入的针对某一高度的容积是否介于该罐前后两个高度对应的容积值之间
	 * @param referredCubage
	 * @return
	 * @author edgar_chan 
	 * @since 2015-4-4
	 */
	private boolean isReasonableCubage(ReferredCubage referredCubage){
		List<Integer> heightList = new ArrayList<Integer>();
		heightList.add(referredCubage.getHeight()-1);
		heightList.add(referredCubage.getHeight()+1);
		
		List<ReferredCubage> referredCubageList = referredCubageDao.get(referredCubage.getTankSetting(), heightList);
		
		if(!CollectionUtils.isEmpty(referredCubageList)){
			if(referredCubageList.size() == 2){
				Double minCubage = referredCubageList.get(0).getCubage();
				Double maxCubage = referredCubageList.get(1).getCubage();
				return (minCubage.compareTo(referredCubage.getCubage()) < 0 && maxCubage.compareTo(referredCubage.getCubage()) > 0);
			}
			
			ReferredCubage referredCubage1 = referredCubageList.iterator().next();
			
			return referredCubage1.getHeight() < referredCubage.getHeight() ? referredCubage1.getCubage().compareTo(referredCubage.getCubage()) < 0 : referredCubage1.getCubage().compareTo(referredCubage.getCubage()) > 0;
		}
		return true;
	}
	

	@Override
	@Transactional
	public void fakeDelete(Long id) {
		referredCubageDao.fakeDelete(id);
	}
	
	@Override
	@Transactional(readOnly=true)
	public List<ReferredCubage> get(Map<String,Object> params,Page page) {
		return referredCubageDao.get(params, page);
	}
	
	@Override
	@Transactional(readOnly=true)
	public Double getCubage(String tankNum,Integer height) {
		Double cubage =  referredCubageDao.get(tankNum, height);
		return cubage == null ? 0d:cubage;
	}
	
	@Override
	@Transactional(readOnly=true)
	public int getTotalRowNum(Map<String,Object> params) {
		return referredCubageDao.get(params);
	}

	@Override
	@Transactional
	public void fakeDelete(String stationNo, String tankNum, Integer height) {
		referredCubageDao.fakeDelete(stationNo, tankNum, height);
	}
	
}
