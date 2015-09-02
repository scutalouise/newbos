package com.bap.authority.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bap.authority.dao.GroupDao;
import com.bap.authority.domain.Group;
import com.bap.authority.service.GroupService;

@Service
public class GroupServiceImpl implements GroupService {
	@Resource GroupDao groupDao;
	/**
	 * 添加组别
	 * @param Group
	 */
	@Transactional
	public void saveGroup(Group Group){
		groupDao.addGroup(Group);
	}
	/**
	 * 删除组别
	 * @param Group
	 */
	@Transactional
	public void delGroup(Group Group){
		/*删除组别-同时删除组别角色对应关系*/
		groupDao.delGroup(Group);
	//	groupDao.delGroupRole("delete from tb_RGRelation where RGRelation_GroupNo='"+Group.getGroup_GroupNo()+"'");
	}
	/**
	 * 修改组别信息
	 * @param role
	 */
	@Transactional
	public void updateGroup(Group Group){
		String sql="update tb_Group set Group_GroupState='"+Group.getGroup_GroupState()+"'," +
				"Group_GroupName='"+Group.getGroup_GroupName()+"'," +
				"Group_Remark='"+Group.getGroup_Remark()+"' " +
				"where Group_GroupNo='"+Group.getGroup_GroupNo()+"'";
		groupDao.updateGroup(sql);
	}
	/**
	 * 查询系统组别信息
	 * @param SystemFlag 系统标识（编码）
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Group> selGroup(String SystemFlag){
		String hql="from Group where Group_SystemFlag='"+SystemFlag+"'";
		 return groupDao.selGroup(hql);
	}
	/**
	 * 查询组别详细信息
	 * @param GroupNo 组别编码
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Group> selGroupDetail(String GroupNo){
		String hql="from Group where Group_GroupNo='"+GroupNo+"'";
		return groupDao.selGroup(hql);
	}
	
	
	public GroupDao getGroupDao() {
		return groupDao;
	}
	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}
	
	
	
}
