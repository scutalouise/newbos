package com.bap.bos.dao;

import java.util.List;

import org.joda.time.DateTime;

import com.bap.bos.domain.RestockBill;

public interface RestockBillDao {

	/**
	 * 保存入库单
	 * @param RestockBill
	 */
	
	public void saveRestockBill(RestockBill RestockBill);

	/**
	 * 查询当前日期已经存在的入库单数
	 * @param OrderBillNumFront
	 * @return
	 */
	
	public Integer selRestockBill(String RestockBillNumFront);

	
	/**
	 * 获取一段时间的进货量
	 * @param dates
	 * @return
	 * @author edgar_chan
	 * @since 2015年5月6日
	 */
	 List<Object[]> getRestockAmountBySpan(DateTime[] dates);
}