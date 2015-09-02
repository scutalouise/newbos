package com.bap.authority.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.bap.authority.domain.Role;
import com.bap.authority.util.RoleFunction;

public interface RoleService {

	/**
	 * 添加角色
	 */
	@Transactional
	public void savaRole(Role role);
	
	/**
	 * 删除角色
	 * @param role
	 */
	@Transactional
	public void delRole(Role role);

	/**
	 * 修改角色信息
	 * @param role
	 */
	public void update(Role role);

	/**
	 * 查询系统角色
	 * @param SystemFlag 系统No
	 * @return
	 */
	public List<Role> selSysRole(String SystemFlag);
	/**
	 * 查询角色详情
	 * @param SystemFlag
	 * @return
	 */
	public List<Role> selSysRoleDetails(String RoleNo);
	/**
	 * 查询角色-功能
	 * @param RoleNo 角色No
	 * @return
	 */
	public List<RoleFunction> selRoleFunction(String RoleNo);
	/**
	 * 系统角色更新（删除角色之前所有功能关联，添加选中功能关联）
	 * @param RoleFunctionNo 角色对应功能编号（编号为字符串，用逗号隔开）
	 * @param RoleNo  角色编号
	 */
	@Transactional
	public void updateRoleFunction(String RoleFunctionNo,String RoleNo);

}