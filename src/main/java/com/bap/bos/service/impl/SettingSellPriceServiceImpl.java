package com.bap.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bap.bos.dao.SettingSellPriceDao;
import com.bap.bos.domain.SettingSellPrice;
import com.bap.bos.service.SettingSellPriceService;
import com.bap.bos.util.Page;

/**
 * 
 * 
 * @author edgar_chan     lineshow7@gmail.com
 * @since 2015年3月18日
 */
@Service
public class SettingSellPriceServiceImpl implements SettingSellPriceService{
	
	@Resource
	private SettingSellPriceDao settingSellPriceDao;

	@Override
	@Transactional(readOnly=true)
	public List<Object> selSellPrice(String start, String end,
			String ProductType, Page page) {
		return settingSellPriceDao.selSellPrice(start, end, ProductType, page);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Object> selSellPrice(String start, String end,
			String ProductType) {
		
		return settingSellPriceDao.selSellPrice(start, end, ProductType);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Object> selSellPrice(String ProductName) {
		
		return settingSellPriceDao.selSellPrice(ProductName);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Object> selSellPrice(String ProductName, Page page) {
		
		return settingSellPriceDao.selSellPrice(ProductName, page);
	}

	@Override
	@Transactional
	public void saveSellPricePlan(SettingSellPrice settingSellPrice) {
		settingSellPriceDao.saveSellPricePlan(settingSellPrice);
	}

	@Override
	@Transactional
	public void delSellPricePlan(SettingSellPrice settingSellPrice) {
		settingSellPriceDao.delSellPricePlan(settingSellPrice);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Object> selSellPricePlan(String start, String end,
			String ProductNum, Page page) {
		return settingSellPriceDao.selSellPricePlan(start, end, ProductNum, page);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Object> selSellPricePlan(String start, String end,
			String ProductNum) {
		return settingSellPriceDao.selSellPricePlan(start, end,ProductNum);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Object> selSellPriceByProductName(String start, String end,
			String ProductName, Page page) {
		return settingSellPriceDao.selSellPriceByProductName(start, end, ProductName,page);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Object> selSellPriceByProductName(String start, String end,
			String ProductName) {
		return settingSellPriceDao.selSellPriceByProductName( start,  end,
				 ProductName);
	}

}
