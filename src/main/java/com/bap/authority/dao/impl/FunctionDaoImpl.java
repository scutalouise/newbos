package com.bap.authority.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.bap.authority.dao.FunctionDao;
import com.bap.authority.domain.Function;
import com.bap.bos.util.DaoTemplate;

/**
 * 系统功能管理实现类
 * 
 * @author zhulong
 * 
 */

@Repository
public class FunctionDaoImpl extends DaoTemplate<Function, String> implements FunctionDao {
	@Resource (name = "sessionFactory") 
	private SessionFactory sessionFactory;

	/* (non-Javadoc)
	 * @see com.authority.dao.impl.FunctionDao#addFunction(com.authority.daomain.Function)
	 */
	
	public void addFunction(Function function) {
		try {
			getSession().save(function);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.authority.dao.impl.FunctionDao#updateFunction(com.authority.daomain.Function)
	 */
	
	public void updateFunction(String sql) {
		try {
			Query query = getSession().createSQLQuery(sql);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see com.authority.dao.impl.FunctionDao#delFunction(com.authority.daomain.Function)
	 */
	public void delFunction(Function function) {
		try {
			getSession().delete(function);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.authority.dao.impl.FunctionDao#selFunction(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	
	public List<Function> selFunction(String sql) {
		Query query=getSession().createQuery(sql);
		List<Function> lists=(List<Function>)query.list();
		return lists;

	}

}
