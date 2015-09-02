package com.bap.bos.dao;

import java.util.List;
import java.util.Map;

import com.bap.bos.domain.ReferredCubage;
import com.bap.bos.domain.Tanksetting;
import com.bap.bos.util.Page;

/**
 * 油罐容积参考表
 * 
 * @author edgar_chan     lineshow7@gmail.com
 * @since 2015年4月3日
 */
public interface ReferredCubageDao {
	
	ReferredCubage get(Long id);
	
	List<ReferredCubage> get(Page page);
	
	void add(ReferredCubage referredCubage);
	
	/**
	 * 通过罐号和高获取容积信息
	 * @param tankSetting
	 * @param heightList
	 * @author edgar_chan 
	 * @since 2015-4-4
	 */
	List<ReferredCubage> get(Tanksetting tankSetting,List<Integer> heightList);
	
	void fakeDelete(Long id);
	
	List<ReferredCubage> get(Map<String,Object> params,Page page);

	int get(Map<String,Object> params);

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
	 * 根据油罐号和高度获取对应的容积
	 * @param tankNum
	 * @param height
	 * @return
	 * @author edgar_chan 
	 * @since 2015-4-6
	 */
	public Double get(String tankNum,Integer height);
}
