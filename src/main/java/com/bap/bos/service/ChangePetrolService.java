package com.bap.bos.service;

import java.util.List;

import com.bap.bos.domain.Tanksetting;
import com.bap.bos.util.Page;

public interface ChangePetrolService {
	/**
	 * 查询油品换号
	 * @param start 换号时间
	 * @param end	换号时间
	 * @param TankNum	油罐编号
	 * @param page	分页类
	 * @return
	 */
	
	public List<Object> selChangePetrol(String start, String end,
			String TankNum, Page page);

	
	public List<Object> selChangePetrol(String start, String end, String TankNum);
	/**
	 * 查询油罐信息-所有油罐
	 * @return
	 */
	
	public List<Tanksetting> selTank();
}
