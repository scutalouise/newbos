package com.bap.authority.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.bap.authority.domain.RFRelation;
import com.bap.authority.domain.Role;
import com.bap.authority.domain.SRRelation;
import com.bap.authority.util.RoleFunction;
import com.bap.authority.util.StaffRole;

public interface RoleDao {

	/**
	 * 添加角色
	 * @param role
	 */
	@Transactional
	public void addRole(Role role);

	/**
	 * 修改角色
	 * @param sql
	 */

	@Transactional
	public void updateRole(String sql);

	/**
	 * 删除角色
	 * @param role
	 */
	public void delRole(Role role);

	/**
	 * 查询角色
	 * @param sql
	 * @return
	 */
	@Transactional
	public List<Role> selRole(String sql);
	/**
	 * 添加角色-功能
	 */
	@Transactional
	public void addRoleFunction(RFRelation rf);
	/**
	 * 删除角色-功能关系
	 * @param sql
	 */
	public void delRole_Function(String sql);

	/**
	 * 查询角色-功能
	 * @param RoleNo
	 * @return
	 */
	@Transactional
	public List<RoleFunction> selRole_Function(String RoleNo);
	
	/**
	 * 查询多角色用户功能
	 * @param RoleNo 多角色RoleNo,List<Integer>类型
	 * @return
	 */
	@Transactional
	public List<RoleFunction> selRoles_Function(List<Integer> RoleNo);
	
	/**
	 * 查询员工对应角色
	 * @param StaffNo
	 * @return
	 */
	@Transactional
	public	List<StaffRole> selStaffRoles(String StaffNo);
	
	/**
	 * 添加员工-角色关系
	 * @param SRRelation
	 */
	@Transactional
	public void saveSRRelation(SRRelation SRRelation);
	
	/**
	 * 删除员工-角色关系
	 * @param SRRelation
	 */	
	public void delSRRelation(SRRelation SRRelation);
	/**
	 * 删除员工-角色关系
	 * @param SRRelation
	 */	
	@Transactional
	public void delSRRelation(String StaffNo,String RoleNo);
	/**
	 * 删除员工-所有角色
	 * @param StaffNo
	 */
	@Transactional
	public void delSRRelation(String StaffNo);
	

}