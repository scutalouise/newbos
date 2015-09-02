package com.bap.bos.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.bap.bos.domain.DayTransVerify;

public interface DayAndMouthStatisticsService {

	/**
	 * 每日统计
	 */
	@Transactional
	public void dayStatistics();

	/**
	 * 比对已经日结但未进行统计的日期
	 * @param TransDay	已经统计的日期记录List
	 * @param DayTransVerify	已经日结的日期记录List
	 * @return
	 */
	public List<String> compareTransDayAndVerify(List<Object> TransDay,
			List<DayTransVerify> DayTransVerify);
	/**
	 * 月统计
	 * @throws ParseException 
	 */
	@Transactional
	public void monthStatistics() throws ParseException;
	
	/**
	 * 初始化
	 * 
	 * @author edgar_chan
	 * @since 2015年5月18日
	 */
	void initDensity();

}