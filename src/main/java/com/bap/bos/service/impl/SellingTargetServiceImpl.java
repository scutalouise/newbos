package com.bap.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bap.bos.dao.SellingTargetDao;
import com.bap.bos.service.SellingTargetService;
import com.bap.bos.util.Page;

@Service
public class SellingTargetServiceImpl implements SellingTargetService{

	@Resource
	private SellingTargetDao sellingTargetDao;
	
	@Override
	@Transactional(readOnly=true)
	public List<Object> selSellTarget(String start, String end,
			String ProductName) {
		
		return sellingTargetDao.selSellTarget(start, end, ProductName);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Object> selSellTarget(String start, String end,
			String ProductName, Page page) {
		
		return sellingTargetDao.selSellTarget(start, end, ProductName, page);
	}

	@Override
	@Transactional
	public void deleteSQL(String s) {
		sellingTargetDao.deleteSQL(s);
	}

	@Override
	@Transactional
	public void saveObject(Object o) {
		sellingTargetDao.saveObject(o);
	}

	@Override
	@Transactional
	public void updateSQL(String s) {
		
		sellingTargetDao.updateSQL(s);
	}

	@Override
	@Transactional
	public void updateObject(Object o) {
		
		sellingTargetDao.updateObject(o);
	}

}
