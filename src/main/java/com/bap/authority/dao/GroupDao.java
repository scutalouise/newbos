package com.bap.authority.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.bap.authority.domain.Group;

public interface GroupDao {

	/**
	 * 添加组别
	 * @param Group
	 */
	@Transactional
	public void addGroup(Group Group);

	/**
	 * 更新组别
	 * @param sql
	 */
	@Transactional
	public void updateGroup(String sql);

	/**
	 * 删除组别
	 * @param Group
	 */
	public void delGroup(Group Group);

	/**
	 * 查询组别
	 * @param HQL
	 * @return
	 */
	@Transactional
	public List<Group> selGroup(String HQL);

}