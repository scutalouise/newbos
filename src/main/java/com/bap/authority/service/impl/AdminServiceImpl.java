package com.bap.authority.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bap.authority.dao.AdminDao;
import com.bap.authority.domain.Admin;
import com.bap.authority.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService  {
	@Resource private AdminDao ad;

	public AdminDao getAd() {
		return ad;
	}
	public void setAd(AdminDao ad) {
		this.ad = ad;
	}
	/* (non-Javadoc)
	 * @see com.authority.service.impl.AdminService#userLogin(java.lang.String, java.lang.String)
	 */
	@Transactional(readOnly=true)
	public Map<String, Object> userLogin(String admin_admin, String admin_pas) {
		Map<String, Object> result = new HashMap<String, Object>();
		Admin user = null;
		Admin resultUser = null;
		if(admin_admin.trim().equals("")||admin_pas.trim().equals("")){//用户名或密码为空
			result.put("flag", "0");
			return result;
		}else{
			user = new Admin();
			user.setAdmin_admin(admin_admin);
			user.setAdmin_pas(admin_pas);
			resultUser = ad.adminLogin(user);
			if (resultUser == null) {
				result.put("flag", "1");
				return result;
			} else {
				result.put("user", resultUser);
				result.put("flag", "2");
				return result;
			}
		}
		
	}

	/* (non-Javadoc)
	 * @see com.authority.service.impl.AdminService#userUpdate(com.authority.daomain.Admin)
	 */
	@Transactional
	public void userUpdate(Admin admin){
		ad.pasUpdate(admin);
	}
	
	

}
