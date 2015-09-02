package com.bap.authority.service;

import java.util.List;

import com.bap.authority.domain.SRRelation;
import com.bap.authority.util.RoleFunction;
import com.bap.authority.util.StaffRole;

public interface AuthorityService {
	
	/**
	 * 查询角色功能
	 * @param RoleNo 角色No列表
	 * @return
	 */
	public List<RoleFunction> selRoleFunction(List<Integer> RoleNo);
	
	/**
	 * 查询员工对应角色
	 * @param StaffNo
	 * @return 返回员工具有角色的详细信息
	 */
	public List<StaffRole> selStaffRoles(String StaffNo);
	/**
	 * 保存员工-角色关系
	 * @param SRRelation
	 */
	public void saveSRRelation(SRRelation SRRelation);
	/**
	 * 删除员工-角色关系
	 * @param SRRelation
	 */
	public void delSRRelation(SRRelation SRRelation);
	/**
	 * 删除员工-角色关系
	 * @param staffNo 员工编号
	 * @param ReleNo  角色编号
	 */
	public void delSRRelation(String StaffNo,String RoleNo);
	/**
	 * 删除员工-所有角色关系
	 * @param SRRelation
	 */
	public void delSRRelation(String StaffNo);

}