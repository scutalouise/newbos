package com.bap.bos.dao;

import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import com.bap.bos.domain.Density;
import com.bap.bos.util.Page;

public interface DensityDao {

	/**
	 * 保存密度
	 * @param Density
	 */
	
	public abstract void saveDensity(Density Density);

	/**
	 * 删除密度
	 * @param Density_ID
	 */
	
	public abstract void deleteDensity(int Density_ID);

	void deleteDensity(String stationNo,String startDate,String productNum);
	/**
	 * 更新密度
	 * @param Density
	 */
	
	public abstract void updateDensity(Density Density);

	/**
	 * 查询密度
	 * @param Density_ProdunctNum	产品编号
	 * @param page	分页类
	 * @return
	 */
	
	public abstract List<Object> selDensity(String Density_ProdunctNum,
			Page page);

	
	public abstract List<Object> selDensity(String Density_ProdunctNum);
	
	/**
	 * 查看某种油品密度设置是否与已设置好的油品密度时间范围有冲突
	 * @param density
	 * @return
	 * @author edgar_chan
	 * @since 2015年4月16日
	 */
	boolean existConfictingDensityDateSpan(Density density);
	
	/**
	 * 根据参数获取密度分页列表 默认按月由近及远排序
	 * @param params
	 * @param page
	 * @return
	 * @author edgar_chan
	 * @since 2015年4月30日
	 */
	List<Density> get(Map<String, Object> params, Page page);
	
	/**
	 * 根据参数获取密度总条数
	 * @param params
	 * @return
	 * @author edgar_chan
	 * @since 2015年4月30日
	 */
	int get(Map<String, Object> params);
	
	/**
	 * 获取一个月区间的所有设置密度
	 * @param dates
	 * @return
	 * @author edgar_chan
	 * @since 2015年5月6日
	 */
	List<Density> getDensities(DateTime[] dates);
	
	/**
	 * 删除密度
	 * @param density
	 * @author edgar_chan
	 * @since 2015年5月6日
	 */
	void delete(Density density);
	
	/**
	 * 获取数量
	 * @return
	 * @author edgar_chan
	 * @since 2015年5月18日
	 */
	long count();
	
}