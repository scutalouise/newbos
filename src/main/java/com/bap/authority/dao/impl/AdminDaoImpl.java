package com.bap.authority.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.bap.authority.dao.AdminDao;
import com.bap.authority.domain.Admin;
import com.bap.bos.util.DaoTemplate;


/**
 * 管理员操作实现类
 * @author zhulong
 *
 */
@Repository

public class AdminDaoImpl extends DaoTemplate<Admin, String> implements AdminDao{
	@Resource (name = "sessionFactory") 
	private SessionFactory sessionFactory;

	//用户登录
	/* (non-Javadoc)
	 * @see com.authority.dao.impl.AdminDao#adminLogin(com.authority.daomain.Admin)
	 */
	public Admin adminLogin(Admin user) {
		Admin resultUser = null;
		try {
			Query query = sessionFactory
					.getCurrentSession()
					.createQuery(
							"from Admin as ad where ad.admin_admin=:admin and ad.admin_pas=:pas");
			query.setString("admin", user.getAdmin_admin());
			query.setString("pas", user.getAdmin_pas());
			if (query.list().size() > 0) {
				resultUser = (Admin) query.list().iterator().next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultUser;
	}

	// 用户密码修改
	/* (non-Javadoc)
	 * @see com.authority.dao.impl.AdminDao#pasUpdate(com.authority.daomain.Admin)
	 */
	/* (non-Javadoc)
	 * @see com.authority.dao.impl.AdminDao#pasUpdate(com.authority.daomain.Admin)
	 */
	public void pasUpdate(Admin admin) {
		try {
			getSession().update(admin);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testAddAdmin(Admin admin){
		Session s=getSession();
		s.save(admin);
		s.flush();
	}


}
