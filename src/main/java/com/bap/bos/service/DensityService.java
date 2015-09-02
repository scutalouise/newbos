package com.bap.bos.service;

import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import com.bap.bos.domain.Density;
import com.bap.bos.util.Page;

/**
 * 
 * @author edgar_chan    lineshow7@gmail.com
 * @since 2015-3-16
 */
public interface DensityService {
	/**
	 * 保存密度
	 * @param Density
	 */
	
	public abstract String saveDensity(Density Density);

	/**
	 * 删除密度
	 * @param Density_ID
	 */
	
	public abstract void deleteDensity(int Density_ID);

	/**
	 * 更新密度
	 * @param Density
	 */
	
	String updateDensity(Density Density);

	/**
	 * 查询密度
	 * @param Density_ProdunctNum	产品编号
	 * @param page	分页类
	 * @return
	 */
	
	public abstract List<Object> selDensity(String Density_ProdunctNum,
			Page page);

	
	public abstract List<Object> selDensity(String Density_ProdunctNum);
	
	public void deleteDensity(String stationNo,String startDate,String productNum);
	
	/**
	 * 获取密度列表
	 * @param params
	 * @param page
	 * @return
	 * @author edgar_chan
	 * @since 2015年4月30日
	 */
	List<Density> get(Map<String,Object> params,Page page);
	
	/**
	 * 获取在输入参数限制下的数据条数
	 * @param params
	 * @return
	 * @author edgar_chan
	 * @since 2015年4月30日
	 */
	int getTotalRowNum(Map<String,Object> params);
	
	/**
	 * 产生月周期内的密度
	 * 
	 * @author edgar_chan
	 * @since 2015年5月4日
	 */
	/**
	 * 根据传入日期生成影响月的初始平均密度
	 * @param densityAffectDateStr  yyyyMMdd
	 * @author edgar_chan
	 * @since 2015年5月6日
	 */
	void generateMonthAreaDensity(String densityAffectDateStr) throws Exception ;
	
	/**
	 * 获取产品本期累积升数  上期节余升数
	 * @param monthSpan
	 * @return
	 * @author edgar_chan
	 * @since 2015年5月6日
	 */
	Map<String,Double[]> getProductAmountMap(DateTime[] monthSpan);
	
	/**
	 * 获取产品 本月区间 上月区间 有效平均密度
	 * @param monthSpan
	 * @return
	 * @author edgar_chan
	 * @since 2015年5月6日
	 */
	Map<String,Double[]> getDensitiesMap(DateTime[] monthSpan);

	/**
	 * 生成月区间的初始平均密度
	 * @param monthSpan
	 * @author edgar_chan
	 * @since 2015年5月6日
	 */
	void generateDensity(DateTime[] monthSpan,String stationNo);
}
