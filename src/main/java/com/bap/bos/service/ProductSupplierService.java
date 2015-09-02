package com.bap.bos.service;

import java.util.List;

import com.bap.bos.domain.ProductSupplier;
import com.bap.bos.util.Page;

public interface ProductSupplierService {

	public abstract void deleteSQL(int ind);

	public abstract String saveObject(ProductSupplier o);

	public abstract List<ProductSupplier> selectObject(String Name, Page page);

	public abstract List<ProductSupplier> selectObject(String Name);

	public abstract String updateObject(ProductSupplier o);

}