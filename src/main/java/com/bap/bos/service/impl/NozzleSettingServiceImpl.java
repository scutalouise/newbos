package com.bap.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bap.bos.dao.NozzleSettingDao;
import com.bap.bos.domain.NozzleSetting;
import com.bap.bos.service.NozzleSettingService;

/**
 * 
 * @author edgar_chan    lineshow7@gmail.com
 * @since 2015-3-16
 */
@Service
public class NozzleSettingServiceImpl implements NozzleSettingService{

	@Resource
	private NozzleSettingDao nozzleSettingDao;

	@Override
	@Transactional(readOnly=true)
	public List<NozzleSetting> selNozzleSetting() {
		return nozzleSettingDao.selNozzleSetting();
	}

	@Override
	@Transactional(readOnly=true)
	public List<NozzleSetting> selNozzleTank(String tankNum) {
		return nozzleSettingDao.selNozzleTank(tankNum);
	}
	
}
