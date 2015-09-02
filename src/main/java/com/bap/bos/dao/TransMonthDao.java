package com.bap.bos.dao;

import java.util.List;

/**
 * 月交易统计
 * 
 * @author edgar_chan     lineshow7@gmail.com
 * @since 2015年5月7日
 */
public interface TransMonthDao {
	
	/**
	 * 获取月销售量
	 * @param ym
	 * @return
	 * @author edgar_chan
	 * @since 2015年5月7日
	 */
	List<Object[]> getProductTransAmounts(String ym);
	
	/**
	 * 获取回罐量
	 * @param ym
	 * @return
	 * @author edgar_chan
	 * @since 2015年5月7日
	 */
	List<Object[]> getProductTransInfo(String ym ,String... types);
}
