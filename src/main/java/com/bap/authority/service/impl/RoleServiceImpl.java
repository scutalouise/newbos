package com.bap.authority.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bap.authority.dao.RoleDao;
import com.bap.authority.domain.RFRelation;
import com.bap.authority.domain.Role;
import com.bap.authority.service.RoleService;
import com.bap.authority.util.RoleFunction;
/**
 * 角色-功能管理业务逻辑处理实体类
 * @author zhulong
 *
 */

@Service
public class RoleServiceImpl implements RoleService {
	@Resource private RoleDao roleDao;
	/**
	 * 添加角色
	 */
	@Transactional
	public void savaRole(Role role){
		roleDao.addRole(role);
	}
	/**
	 * 删除角色
	 * @param role
	 */
	@Transactional
	public void delRole(Role role){
		/*删除角色对应的功能*/
		String sql="delete from tb_RFRelation where RFRelation_RoleNo='"+role.getRole_RoleNo()+"'";
		roleDao.delRole_Function(sql);	
		/*删除角色*/
		roleDao.delRole(role);
	}
	/* (non-Javadoc)
	 * @see com.authority.service.impl.RoleService#update(com.authority.daomain.Role)
	 */
	@Transactional
	public void update(Role role){
		String sql="update tb_Role set Role_RoleState='"+role.getRole_RoleState()+"'," +
				"Role_RoleName='"+role.getRole_RoleName()+"'," +
				"Role_Remark='"+role.getRole_Remark()+"' " +
				"where Role_RoleNo='"+role.getRole_RoleNo()+"'";
		roleDao.updateRole(sql);
	}
	/**
	 * 查询系统角色
	 * @param GroupNo 组别No
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Role> selSysRole(String GroupNo){
		//String sql="from Role where Role_SystemFlag='"+SystemFlag+"'";
		String sql="from Role where Role_GroupNo='"+GroupNo+"'";
		List<Role> lists=roleDao.selRole(sql);
		return lists;
	}
	/**
	 * 查询角色详情
	 * @param SystemFlag
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Role> selSysRoleDetails(String RoleNo){
		String sql="from Role where Role_RoleNo='"+RoleNo+"'";
		List<Role> lists=roleDao.selRole(sql);
		return lists;
	}
	
	/**
	 * 查询角色-功能
	 * @param RoleNo 角色No
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<RoleFunction> selRoleFunction(String RoleNo){
		return roleDao.selRole_Function(RoleNo);
	}
	/**
	 * 删除角色-功能关系
	 * @param rf
	 */
	@Transactional
	public void delRoleFunction(String RoleNo){
		String sql="delete from tb_RFRelation where RFRelation_RoleNo="+Integer.valueOf(RoleNo)+"";
		roleDao.delRole_Function(sql);
	}
	/**
	 * 系统角色更新（删除角色之前所有功能关联，添加选中功能关联）
	 * @param RoleFunctionNo 角色对应功能编号（编号为字符串，用逗号隔开）
	 * @param RoleNo  角色编号
	 */
	@Transactional
	public void updateRoleFunction(String RoleFunctionNo,String RoleNo){
		/*删除原始关联*/
		this.delRoleFunction(RoleNo);
		/*添加新关联*/
		String[] FunctionNo=RoleFunctionNo.split(",");
		for(int i=0;i<FunctionNo.length;i++){
			RFRelation RFRelation =new RFRelation();
			RFRelation.setRFRelation_FunctionNo(Integer.valueOf(FunctionNo[i]));
			RFRelation.setRFRelation_RoleNo(Integer.valueOf(RoleNo));
			RFRelation.setRFRelation_IsSync("1");
			RFRelation.setRFRelation_SyncDate(new Date());
			roleDao.addRoleFunction(RFRelation);
		}
	}
	
	public RoleDao getroleDao() {
		return roleDao;
	}
	public void setroleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}


	
	
	
	
	
	
	
	

}
