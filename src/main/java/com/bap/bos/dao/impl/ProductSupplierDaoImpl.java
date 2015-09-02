package com.bap.bos.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.bap.bos.dao.ProductSupplierDao;
import com.bap.bos.domain.ProductSupplier;
import com.bap.bos.util.DaoTemplate;
import com.bap.bos.util.Page;


@Repository
public class ProductSupplierDaoImpl extends DaoTemplate<ProductSupplier, String> implements ProductSupplierDao {

	@Override
	public void deleteSQL(String s) {
		super.deleteSQL(s);
	}

	@Override
	public void saveObject(ProductSupplier o) {
		super.saveObject(o);
	}

	@Override
	public List<ProductSupplier> selectObject(String s, Page page) {
		// TODO Auto-generated method stub
		return super.selectObject(s, page);
	}

	@Override
	public List<ProductSupplier> selectObject(String s) {
		// TODO Auto-generated method stub
		return super.selectObject(s);
	}

	@Override
	public void updateObject(ProductSupplier o) {
		// TODO Auto-generated method stub
		super.updateObject(o);
	}

	@Override
	public boolean isRepeatedName(ProductSupplier supplier) {
		StringBuilder hql = new StringBuilder();
		hql.append("select count(*) from ProductSupplier p ");

		if(supplier.getProductSupplier_key() != null){
			hql.append(" where p.ProductSupplier_key != ? and p.ProductSupplier_Name = ?");
			Query query = this.getSession().createQuery(hql.toString());
			query.setInteger(0, supplier.getProductSupplier_key());
			query.setString(1, supplier.getProductSupplier_Name());
			return (Long)query.uniqueResult() > 0;
			
		}else{
			hql.append(" where p.ProductSupplier_Name = ?");
			Query query = this.getSession().createQuery(hql.toString());
			query.setString(0, supplier.getProductSupplier_Name());
			return (Long)query.uniqueResult() > 0;
		}
	}
	
	
	
}
