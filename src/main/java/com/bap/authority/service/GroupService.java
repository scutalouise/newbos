package com.bap.authority.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.bap.authority.domain.Group;

public interface GroupService {

	/**
	 * 添加组别
	 * @param Group
	 */
	public void saveGroup(Group Group);

	/**
	 * 删除组别
	 * @param Group
	 */
	@Transactional
	public void delGroup(Group Group);

	/**
	 * 修改组别信息
	 * @param role
	 */
	public void updateGroup(Group Group);
	/**
	 * 查询组别信息
	 * @param SystemFlag 系统标识（编码）
	 * @return
	 */
	public List<Group> selGroup(String SystemFlag);
	/**
	 * 查询组别详细信息
	 * @param GroupNo 组别编码
	 * @return
	 */
	public List<Group> selGroupDetail(String GroupNo);

}