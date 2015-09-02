package com.bap.bos.dao;

import java.util.List;

import com.bap.bos.domain.TransCard;

public interface TransCardDao {

	/**
	 * 查询每日未结交易
	 * @return
	 */
	
	public List<TransCard> selDaysNoPayTransCard(String[] dates) throws Exception;
	/**
	 * 查询每日交易总账
	 * @return int[0]-交易笔数；int[1]-交易总金额;int[2]-交易总升数
	 */
	
	
	public int[] selDaysTransTotal(String[] dates) throws Exception;
	/**
	 * 更新当日是否对账标识
	 * @param TxCreateTime
	 */
	
	public void updateBalanceAcc(String[] dates) throws Exception;

}