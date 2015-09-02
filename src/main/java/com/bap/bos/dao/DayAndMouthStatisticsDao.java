package com.bap.bos.dao;

import java.util.List;

import com.bap.bos.domain.DayTransVerify;
import com.bap.bos.domain.MonthDateAera;
import com.bap.bos.domain.TransDay;
import com.bap.bos.domain.TransMonth;
import com.bap.bos.domain.TransType;

public interface DayAndMouthStatisticsDao {

	
	/**
	 * 统计日期段中某枪号 某交易类型下的TransItem中合计金额/升数/笔数
	 * @param transType 交易类型
	 * @param nozzleNo 枪号
	 * @param dates 日期段
	 * @return
	 * @author edgar_chan
	 * @since 2015年4月15日
	 */
	public Object[] getTransItemSumVolAndSumAmount(String transType,String nozzleNo,String[] dates);

	
	/**
	 * 统计日期段中某枪号 某交易类型下的Transcard中合计金额/升数/笔数
	 * @param transType
	 * @param nozzleNo
	 * @param dates
	 * @return
	 * @author edgar_chan
	 * @since 2015年4月15日
	 */
	public Object[] getTransCardSumVolAndSumAmount(String transType,String nozzleNo,String[] dates);

	/**
	 * 查询客户类型
	 * @return
	 */
	

	/**
	 * 获取最近一个日统计日期
	 * @return
	 */
	String getlatestTransDayDate();
//	
//	public abstract List<CustomerType> selCustomerType();
	/**
	 * 查询交易类型
	 * @return
	 */
	
	public List<TransType> selTransType();
	/**
	 * 查询日结记录表
	 * @return
	 */
	
	public List<DayTransVerify> selDayTransVerify();
	
	/**
	 * 查询日统计记录
	 * @return
	 */
	
	public List<String> selTransDay();
	
	/**
	 * 查询本站所有油枪枪号
	 * @return
	 */
	
	public List<String> selNozzleSetting();
	
	/**
	 * 保存日统计记录
	 * @param TransDay
	 */
	
	public void saveTransDay(TransDay TransDay);
	
	/**
	 * 查询每月的日统计
	 * @param startDate - 年月日，例如20140201
	 * * @param startDate - 年月日，例如20140231
	 * @return
	 */
	
	public List<TransDay> selTransDay(String startDate,String endDate);
	
	/**
	 * 保存每月记录
	 * @param TransMonth
	 */
	
	public void saveMonthDay(TransMonth TransMonth);
	
	/**
	 * 查询月结 天的跨度
	 * @return
	 * @author edgar_chan
	 * @since 2015年4月15日
	 */
	public MonthDateAera getMonthStaticDaySpan();
	
	/**
	 * 月结统计
	 * @param startDate
	 * @param endDate
	 * @return
	 * @author edgar_chan
	 * @since 2015年4月15日
	 */
	public List<Object[]> getTransDaySumVolAndSumAmountAndCount(String startDate,String endDate);
	
	/**
	 * 是否做过ym此月的月统计
	 * @param ym 年月 yyyyMM
	 * @return
	 * @author edgar_chan
	 * @since 2015年4月15日
	 */
	boolean haveDoneMonthStatistic(String ym);
	
	/**
	 * 获取已日结确认未日统计的日期
	 * @param standardDate
	 * @return
	 * @author edgar_chan
	 * @since 2015年4月16日
	 */
	List<String> getNoStaticDayTransVerify(String standardDate);
	

	/**
	 * 获取最近的月统计月份
	 * @param TransMonth
	 */
	
	String getLatestMonthDay();
	
	/**
	 * 获取最远一个日统计日期
	 * @return
	 * @author edgar_chan 
	 * @since 2015-4-18
	 */
	String getFarestTransDayDate();
	
}