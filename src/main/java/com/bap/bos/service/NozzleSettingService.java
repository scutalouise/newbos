package com.bap.bos.service;

import java.util.List;

import com.bap.bos.domain.NozzleSetting;

/**
 * 
 * @author edgar_chan    lineshow7@gmail.com
 * @since 2015-3-16
 */
public interface NozzleSettingService {

	public List<NozzleSetting> selNozzleSetting();
	/**
	 * 查询油罐对应油枪
	 * @param TankNum
	 * @return
	 */
	
	public List<NozzleSetting> selNozzleTank(String TankNum);
}
