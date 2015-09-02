package com.bap.authority.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bap.authority.dao.RoleDao;
import com.bap.authority.domain.SRRelation;
import com.bap.authority.service.AuthorityService;
import com.bap.authority.util.RoleFunction;
import com.bap.authority.util.StaffRole;
/**
 * 对外接口实现类
 * @author zhulong
 *
 */

@Service
public class AuthorityServiceImpl implements AuthorityService {
	@Resource private RoleDao rd;
	
	/* (non-Javadoc)
	 * @see com.authority.service.impl.AuthorityService#selRoleFunction(java.util.List)
	 */
	@Transactional(readOnly=true)
	public List<RoleFunction> selRoleFunction(List<Integer> RoleNo){
		if(RoleNo.size()==1){
			return rd.selRole_Function(String.valueOf(RoleNo.get(0)));		
		}else if(RoleNo.size()>1){
			return rd.selRoles_Function(RoleNo);		
		}else{
			return null;
		}
	}
	/**
	 * 查询员工对应角色
	 * @param StaffNo
	 * @return 返回员工具有角色的详细信息
	 */
	@Transactional(readOnly=true)
	public List<StaffRole> selStaffRoles(String StaffNo){
		return rd.selStaffRoles(StaffNo);
	}
	/**
	 * 保存员工-角色关系
	 * @param SRRelation
	 */
	@Transactional
	public void saveSRRelation(SRRelation SRRelation){
		try {
			rd.saveSRRelation(SRRelation);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 删除员工-角色关系
	 * @param SRRelation
	 */
	@Transactional
	public void delSRRelation(SRRelation SRRelation){
		try {
			rd.delSRRelation(SRRelation);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 删除员工-角色关系
	 * @param staffNo 员工编号
	 * @param ReleNo  角色编号
	 */
	@Transactional
	public void delSRRelation(String StaffNo,String RoleNo){
		rd.delSRRelation(StaffNo, RoleNo);
	}
	/**
	 * 删除员工-所有角色关系
	 * @param SRRelation
	 */
	@Transactional
	public void delSRRelation(String StaffNo){
		try {
			rd.delSRRelation(StaffNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public RoleDao getRd() {
		return rd;
	}
	public void setRd(RoleDao rd) {
		this.rd = rd;
	}
	
	
	
	
	
	
	
	

}
