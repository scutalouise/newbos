package com.bap.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bap.bos.dao.ProductSupplierDao;
import com.bap.bos.domain.ProductSupplier;
import com.bap.bos.service.ProductSupplierService;
import com.bap.bos.util.Page;


@Service
public class ProductSupplierServiceImpl implements ProductSupplierService {
	@Resource private ProductSupplierDao productSupplierDao;
	
	@Transactional
	public void deleteSQL(int ind) {
		String sql="delete from tb_ProductSupplier where ProductSupplier_key='"+ind+"'";
		productSupplierDao.deleteSQL(sql);
	}

	@Transactional
	public String saveObject(ProductSupplier o) {
		if(productSupplierDao.isRepeatedName(o))return "repeatName";
		productSupplierDao.saveObject(o);
		return "success";
	}

	@Transactional
	public List<ProductSupplier> selectObject(String Name, Page page) {
		String sql="from ProductSupplier where 1=1 ";
		if("".equals(Name.trim())){
			sql+="order by ProductSupplier_Name";
		}else{
			sql+="and ProductSupplier_Name like '"+Name+"%' order by ProductSupplier_Name";
		}
		return productSupplierDao.selectObject(sql, page);
	}

	@Transactional
	public List<ProductSupplier> selectObject(String Name) {
		String sql="from ProductSupplier where 1=1 ";
		if("".equals(Name.trim())){
			sql+="order by ProductSupplier_Name";
		}else{
			sql+="and ProductSupplier_Name like '"+Name+"%' order by ProductSupplier_Name";
		}
		return productSupplierDao.selectObject(sql);
	}

	@Transactional
	public String updateObject(ProductSupplier o) {
		if(productSupplierDao.isRepeatedName(o))return "repeatName";
		productSupplierDao.updateObject(o);
		return "success";
	}

	
}
