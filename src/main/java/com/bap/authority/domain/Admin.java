package com.bap.authority.domain;

import java.io.Serializable;

/**
 * 管理员实体类
 * @author zhulong
 *
 */
public class Admin implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String admin_admin;
	private String admin_pas;
	public String getAdmin_admin() {
		return admin_admin;
	}
	public void setAdmin_admin(String adminAdmin) {
		admin_admin = adminAdmin;
	}
	public String getAdmin_pas() {
		return admin_pas;
	}
	public void setAdmin_pas(String adminPas) {
		admin_pas = adminPas;
	}

	
}
