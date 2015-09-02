package com.bap.bos.dao;

import java.util.List;

import com.bap.bos.domain.ProductSupplier;
import com.bap.bos.util.Page;

public interface ProductSupplierDao {

	public abstract void deleteSQL(String s);

	public abstract void saveObject(ProductSupplier o);

	public abstract List<ProductSupplier> selectObject(String s, Page page);

	public abstract List<ProductSupplier> selectObject(String s);

	public abstract void updateObject(ProductSupplier o);
	
	/**
	 * 是否供应商名称重复
	 * @param supplier
	 * @return
	 * @author edgar_chan
	 * @since 2015年4月9日
	 */
	public boolean isRepeatedName(ProductSupplier supplier);
	

}