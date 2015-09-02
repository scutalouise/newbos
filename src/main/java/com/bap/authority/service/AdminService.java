package com.bap.authority.service;

import java.util.Map;

import com.bap.authority.domain.Admin;

public interface AdminService {

	/**
	 * 用户登陆
	 * @param admin_admin 账号
	 * @param admin_pas 密码
	 * @return flag=0 账号或密码为空;flag=1账号密码错误;flag=2正确返回管理员信息
	 */
	public Map<String, Object> userLogin(String admin_admin, String admin_pas);

	/**
	 * 用户密码修改
	 * @param admin 需要修改的用户
	 */
	public void userUpdate(Admin admin);

}