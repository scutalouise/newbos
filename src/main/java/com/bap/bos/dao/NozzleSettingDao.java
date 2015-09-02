package com.bap.bos.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.bap.bos.domain.NozzleSetting;

public interface NozzleSettingDao {

	
	public List<NozzleSetting> selNozzleSetting();
	/**
	 * 查询油罐对应油枪
	 * @param TankNum
	 * @return
	 */
	
	public List<NozzleSetting> selNozzleTank(String TankNum);
	

}