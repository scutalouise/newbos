package com.bap.bos.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.bap.bos.domain.SaveToBank;
import com.bap.bos.util.Page;

public interface BankReceiptDao {

	/**
	 * 保存存款信息
	 * @param ds
	 */
	
	public void saveSaveToBank(SaveToBank sb);

	/**
	 * 删除存款信息
	 * @param ds
	 */
	
	public void delSaveToBank(SaveToBank sb);

	/**
	 * 更新存款信息
	 * @param sql
	 */
	
	public void updateSaveToBank(String sql);

	/**
	 * 查询存款信息
	 * @param start
	 * @param end
	 * @return
	 */
	
	public List<Object> selSaveToBank(String start, String end);

	
	public List<Object> selSaveToBank(String start, String end, Page page);
	
	
	public List<SaveToBank> selOneSaveToBank(String StationNo,String Receipt);

}