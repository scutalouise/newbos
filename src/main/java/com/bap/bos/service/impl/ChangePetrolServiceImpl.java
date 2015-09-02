package com.bap.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bap.bos.dao.ChangePetrolDao;
import com.bap.bos.domain.Tanksetting;
import com.bap.bos.service.ChangePetrolService;
import com.bap.bos.util.Page;

/**
 * 
 * 
 * @author edgar_chan     lineshow7@gmail.com
 * @since 2015年3月18日
 */
@Service
public class ChangePetrolServiceImpl implements ChangePetrolService{
	@Resource
	private ChangePetrolDao changePetrolDao;

	@Override
	@Transactional(readOnly=true)
	public List<Object> selChangePetrol(String start, String end,
			String TankNum, Page page) {
		
		return changePetrolDao.selChangePetrol(start, end, TankNum, page);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Object> selChangePetrol(String start, String end, String TankNum) {
		
		return changePetrolDao.selChangePetrol(start, end, TankNum);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Tanksetting> selTank() {
		
		return changePetrolDao.selTank();
	}
	
	
}
