package com.bap.authority.dao;

import com.bap.authority.domain.Admin;

public interface AdminDao {

	//用户登录
	/* (non-Javadoc)
	 * @see com.authority.dao.impl.AdminDao#adminLogin(com.authority.daomain.Admin)
	 */
	public Admin adminLogin(Admin user);

	// 用户密码修改
	/* (non-Javadoc)
	 * @see com.authority.dao.impl.AdminDao#pasUpdate(com.authority.daomain.Admin)
	 */
	public void pasUpdate(Admin admin);
	
	public void testAddAdmin(Admin admin);

}