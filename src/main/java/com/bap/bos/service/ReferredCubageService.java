package com.bap.bos.service;

import java.util.List;
import java.util.Map;

import com.bap.bos.domain.ReferredCubage;
import com.bap.bos.util.Page;

/**
 * 油罐容积参照表
 * 
 * @author edgar_chan     lineshow7@gmail.com
 * @since 2015年4月3日
 */
public interface ReferredCubageService {

	ReferredCubage get(Long id);

	List<ReferredCubage> get(Page page);

	String add(ReferredCubage referredCubage);

	void fakeDelete(Long id);
	
	/**
	 * 根据 油罐号 或者 油罐高度 查询油罐具体容积等信息
	 * @param params
	 * @param page
	 * @return
	 * @author edgar_chan
	 * @since 2015年4月3日
	 */
	List<ReferredCubage> get(Map<String,Object> params,Page page);
	
	
	/**
	 * 根据油罐信息和高度 删除 数据
	 * @param stationNo
	 * @param tankNum
	 * @param height
	 * @author edgar_chan
	 * @since 2015年4月3日
	 */
	void fakeDelete(String stationNo,String tankNum,Integer height);
	
	/**
	 * 获取满足条件的总行数
	 * @param params
	 * @return
	 * @author edgar_chan 
	 * @since 2015-4-4
	 */
	int getTotalRowNum(Map<String,Object> params);
	
	Double getCubage(String tankNum,Integer height);
}
