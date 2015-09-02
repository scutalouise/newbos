package com.bap.bos.util;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * DaoTemplate<O,S>
 * @author zhulong
 *
 * @param <O> Object
 * @param <S> String
 */
public class DaoTemplate<O,S> {
	
	@Autowired
	public SessionFactory sessionFactory;
	
	public Session getSession(){
		Session session = null;
		try{
			session  = sessionFactory.getCurrentSession();
		}catch(Exception e){
			if(session == null) session = sessionFactory.openSession();
		}
		return session;
	}
	
	
	/**
	 * 添加对象-Object
	 * @param t
	 */
	public void saveObject(O o){
		try {
			getSession().save(o);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	/**
	 * 修改对象-SQL
	 * @param t
	 */
	public void updateSQL(S s){
		Query query = getSession().createSQLQuery(s.toString());
		query.executeUpdate();	
	}
	/**
	 * 修改对象-Object
	 * @param t
	 */
	public void updateObject(O o){
		getSession().update(o);
	}
	/**
	 * 删除对象-Object
	 * @param t
	 */
	public void deleteObject(O o){
		try {
			getSession().delete(o);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 删除对象-SQL
	 * @param t
	 */
	public void deleteSQL(S s){
		try {
			Query query = getSession().createSQLQuery(s.toString());
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 查询对象-Object,不分页
	 * @param t
	 */
	@SuppressWarnings("unchecked")
	public List<O> selectObject(S s){	
		List<O> tt =getSession().createQuery(s.toString()).list();
		return (List<O>)tt;
	}
	
	/**
	 * 查询对象-Object,分页
	 * @param t
	 */
	@SuppressWarnings("unchecked")
	public List<O> selectObject(S s,Page page){	
		Query query=getSession().createQuery(s.toString());
		query.setFirstResult((page.getPageNum()-1)*page.getPageSize());
		query.setMaxResults(page.getPageSize());
		return (List<O>)query.list();	
	}
	/**
	 * 查询对象-SQL
	 * @param s 语句
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<O> selectSQLObject(S s){
		return (List<O>)getSession().createSQLQuery(s.toString()).list();
	}
	/**
	 * 查询对象-SQL
	 * @param s 语句
	 * @param page 分页类
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<O> selectSQLObject(S s,Page page){
		SQLQuery SQLQuery=getSession().createSQLQuery(s.toString());
		SQLQuery.setFirstResult((page.getPageNum()-1)*page.getPageSize());
		SQLQuery.setMaxResults(page.getPageSize());
		return (List<O>)SQLQuery.list();	
	}
	
	
	
}
