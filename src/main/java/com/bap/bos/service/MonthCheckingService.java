package com.bap.bos.service;

import java.util.List;

import org.joda.time.DateTime;

import com.bap.bos.domain.MonthDateAera;
import com.bap.bos.web.pojo.MonthChecking;
import com.bap.bos.web.pojo.MonthCheckingDetail;

/**
 * 月盘点service
 * 
 * @author edgar_chan     lineshow7@gmail.com
 * @since 2015年5月7日
 */
public interface MonthCheckingService {

	
	/**
	 * 整合月盘点报表数据
	 * @param checkingMonth
	 * @return
	 * @author edgar_chan
	 * @since 2015年5月7日
	 */
	public List<MonthChecking> integrateMonthCheckingData(String checkingMonth);
	
	
	/**
	 * 月盘点 分油品 销售细化
	 * @param checkingMonth
	 * @return
	 * @author edgar_chan
	 * @since 2015年5月8日
	 */
	public List<MonthCheckingDetail> integrateMonthCheckingDetailData(String checkingMonth);
	
	/**
	 * 获取月周期
	 * @return
	 * @author edgar_chan
	 * @since 2015年5月8日
	 */
	public MonthDateAera getMonthAreaSpan();
	/**
	 * 月盘点 分油品 销售细化
	 * @param checkingMonth
	 * @return
	 * @author edgar_chan
	 * @since 2015年5月8日
	 */
	public List<MonthCheckingDetail> integrateMonthCheckingDetailData(DateTime[] monthSpan);
	/**
	 * 整合月盘点报表数据
	 * @param checkingMonth
	 * @return
	 * @author edgar_chan
	 * @since 2015年5月7日
	 */
	public List<MonthChecking> integrateMonthCheckingData(DateTime[] monthSpan);
}
