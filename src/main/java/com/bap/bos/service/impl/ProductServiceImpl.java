package com.bap.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bap.bos.dao.ProductDao;
import com.bap.bos.domain.Product;
import com.bap.bos.service.ProductService;

/**
 * 
 * 
 * @author edgar_chan     lineshow7@gmail.com
 * @since 2015年3月16日
 */
@Service
public class ProductServiceImpl implements ProductService {

	@Resource
	private ProductDao productDao;

	@Override
	@Transactional(readOnly=true)
	public List<Product> selProductDetail() {
		return productDao.selProductDetail();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Product> selProductDetail(String Product_Type) {
		return productDao.selProductDetail(Product_Type);
	}

	@Override
	@Transactional(readOnly=true)
	public String selProductName(String Product_Num) {
		return productDao.selProductName(Product_Num);
	}

	@Override
	@Transactional(readOnly=true)
	public String selProductNum(String ProductName) {
		return productDao.selProductNum(ProductName);
	}
	
	
	
}
