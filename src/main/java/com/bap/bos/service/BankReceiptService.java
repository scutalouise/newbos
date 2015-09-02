package com.bap.bos.service;

import java.util.List;

import com.bap.bos.domain.SaveToBank;
import com.bap.bos.util.Page;

public interface BankReceiptService {

	/**
	 * 保存银行存款记录
	 * @param sb
	 */
	public void saveBankReceipt(SaveToBank sb);

	/**
	 * 更新存款记录
	 * @param sb
	 * @param date 格式：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public String updateBankReceipt(SaveToBank sb) throws Exception;

	/**
	 * 删除银行存款记录
	 * @param sb
	 * @param date 格式：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public String delBankReceipt(SaveToBank sb);

	/**
	 * 查询银行存款记录
	 * @param start 开始时间
	 * @param end	结束时间	
	 * @param page	分页类
	 * @return
	 */
	public List<Object> selSaveToBank(String start, String end, Page page);
	public List<Object> selSaveToBank(String start,String end);
	/**
	 * 存款记录签核
	 * @param StationNo
	 * @param SaveToBank_Receipt
	 * @return
	 */
	public boolean signBankReceipt(String StationNo,String SaveToBank_Receipt);

}