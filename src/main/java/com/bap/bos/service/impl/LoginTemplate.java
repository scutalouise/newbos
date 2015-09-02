package com.bap.bos.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bap.authority.service.impl.AuthorityServiceImpl;
import com.bap.authority.util.RoleFunction;
import com.bap.authority.util.StaffRole;
import com.bap.bos.dao.StaffDao;
import com.bap.bos.domain.Staff;
import com.bap.bos.service.Login;
/**
 * 登陆
 * @author zhulong
 *
 */
@Service("loginService")
public class LoginTemplate extends AuthorityServiceImpl implements Login {
	@Resource private StaffDao staffDao;
	
	@Transactional(readOnly=true)
	public List<RoleFunction> Login_function(String admin, String pas) {
		List<Staff> result=this.adminCheck(admin, pas);
		if(result==null||result.size()<=0){
			return null;
		}else{
			List<StaffRole> Roles=this.selStaffRoles(admin);
			List<Integer> RoleNo=new ArrayList<Integer>();
			for(int i=0;i<Roles.size();i++){
				RoleNo.add(Roles.get(i).getRole_RoleNo());
			}
			return this.selRoleFunction(RoleNo);	
		}		
	}
	
	@Transactional(readOnly=true)
	public List<Staff> adminCheck(String admin, String pas){
		if(admin.trim().equals("")||admin.trim().equals("")){//用户名或密码为空
			return null;
		}else{
			return staffDao.selStaffDetail(admin, pas);
		}
	}
	
	public StaffDao getStaffDao() {
		return staffDao;
	}
	public void setStaffDao(StaffDao staffDao) {
		this.staffDao = staffDao;
	}
	
}
