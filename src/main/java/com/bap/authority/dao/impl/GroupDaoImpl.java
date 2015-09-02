package com.bap.authority.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.bap.authority.dao.GroupDao;
import com.bap.authority.domain.Group;
import com.bap.bos.util.DaoTemplate;

/**
 * 组别管理实现类
 * 
 * @author zhulong
 * 
 */

@Repository
public class GroupDaoImpl extends DaoTemplate<Group, String> implements GroupDao {
	@Resource (name = "sessionFactory") 
	private SessionFactory sessionFactory;

	/**
	 * 添加组别
	 * @param Group
	 */
	
	public void addGroup(Group Group) {
		try {
			getSession().save(Group);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 更新组别
	 * @param sql
	 */
	
	public void updateGroup(String sql) {
		try {
			Query query = getSession().createSQLQuery(sql);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/**
	 * 删除组别
	 * @param Group
	 */
	public void delGroup(Group Group) {
		try {
			getSession().delete(Group);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/**
	 * 查询组别
	 * @param HQL
	 * @return
	 */
	@SuppressWarnings("unchecked")
	
	public List<Group> selGroup(String HQL) {
		Query query=getSession().createQuery(HQL);
		List<Group> lists=(List<Group>)query.list();
		return lists;
	}
	
	

}
