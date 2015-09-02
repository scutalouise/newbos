package com.bap.authority.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.bap.authority.dao.RoleDao;
import com.bap.authority.domain.RFRelation;
import com.bap.authority.domain.Role;
import com.bap.authority.domain.SRRelation;
import com.bap.authority.util.RoleFunction;
import com.bap.authority.util.StaffRole;
import com.bap.bos.util.DaoTemplate;

/**
 * 角色管理实现类
 * 
 * @author zhulong
 * 
 */
@Repository("roleDao")
public class RoleDaoImpl extends DaoTemplate<Role, String> implements RoleDao {
	@Resource (name = "sessionFactory") 
	private SessionFactory sessionFactory;

	/* (non-Javadoc)
	 * @see com.authority.dao.impl.RoleDao#addRole(com.authority.daomain.Role)
	 */
	public void addRole(Role role) {
		try {
			getSession().save(role);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/* (non-Javadoc)
	 * @see com.authority.dao.impl.RoleDao#updateRole(java.lang.String)
	 */

	
	public void updateRole(String sql) {
		try {
			Query query = getSession().createSQLQuery(sql);
			query.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see com.authority.dao.impl.RoleDao#delRole(com.authority.daomain.Role)
	 */
	public void delRole(Role role) {
		try {
			getSession().delete(role);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see com.authority.dao.impl.RoleDao#selFunction(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	
	public List<Role> selRole(String sql) {
		Query query=getSession().createQuery(sql);
		List<Role> lists=(List<Role>)query.list();
		return lists;
	}
	/**
	 * 添加角色-功能
	 */
	
	public void addRoleFunction(RFRelation rf) {
		try {
			getSession().save(rf);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/* (non-Javadoc)
	 * @see com.authority.dao.impl.RoleDao#delRole_Function(java.lang.String)
	 */
	public void delRole_Function(String sql) {
		try {
			Query query = getSession().createSQLQuery(sql);
			query.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/* (non-Javadoc)
	 * @see com.authority.dao.impl.RoleDao#selRole_Function(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<RoleFunction> selRole_Function(String RoleNo){
		String sql="select fc.Function_FunctionNo as Function_FunctionNo," +
				"fc.Function_FunctionName as Function_FunctionName," +
				"rf.RFRelation_RoleNo as RFRelation_RoleNo," +
				"fc.Function_FunctionState as Function_State,"+
				"fc.Function_Type as Function_Type," +
				"fc.Function_ParentNo as Function_ParentNo," +
				"fc.Function_SubNo as Function_SubNo," +
				"fc.Function_URL as Function_URL "+
				"from Function as fc,RFRelation as rf " +
				"where rf.RFRelation_RoleNo='"+RoleNo+"' and rf.RFRelation_FunctionNo=fc.Function_FunctionNo order by fc.Function_FunctionNo";
		Query query=getSession().createQuery(sql);
		List<RoleFunction> lists=(List<RoleFunction>)query.setResultTransformer(
				Transformers.aliasToBean(RoleFunction.class)).list();
		return lists;
		
	}
	/**
	 * 查询多角色用户功能
	 * @param RoleNo 多角色RoleNo,List<Integer>类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	
	public List<RoleFunction> selRoles_Function(List<Integer> RoleNo){
		String sql="select distinct fc.Function_FunctionNo as Function_FunctionNo," +
		"fc.Function_FunctionName as Function_FunctionName," +
		"fc.Function_FunctionState as Function_State,"+
		"fc.Function_Type as Function_Type," +
		"fc.Function_ParentNo as Function_ParentNo," +
		"fc.Function_SubNo as Function_SubNo," +
		"fc.Function_URL as Function_URL "+
		"from Function as fc,RFRelation as rf " +
		"where";
		for(int i=0,k=0;i<RoleNo.size();i++){
			if((k+1)<RoleNo.size()){
				sql+=" rf.RFRelation_RoleNo="+RoleNo.get(i)+" or ";
			}else{
				sql+=" rf.RFRelation_RoleNo="+RoleNo.get(i);
			}
			k++;
		}
		sql+=" and rf.RFRelation_FunctionNo=fc.Function_FunctionNo order by fc.Function_FunctionNo";
		Query query=getSession().createQuery(sql);
		List<RoleFunction> lists=(List<RoleFunction>)query.setResultTransformer(
				Transformers.aliasToBean(RoleFunction.class)).list();
		return lists;
	}
	/**
	 * 查询员工对应角色
	 * @param StaffNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	
	public	List<StaffRole> selStaffRoles(String StaffNo){
		String sql="select rl.Role_RoleNo as Role_RoleNo," +
				"rl.Role_RoleName as Role_RoleName," +
				"rl.Role_RoleState as Role_RoleState," +
				"rl.Role_Remark as Role_Remark," +
				"rl.Role_SystemFlag as Role_SystemFlag" +
				" from Role as rl,SRRelation as sr " +
				"where rl.Role_RoleNo=sr.SRRelation_RoleNo and sr.SRRelation_StaffNo='"+StaffNo+"'";
	
		Query query=getSession().createQuery(sql);
		List<StaffRole> lists=(List<StaffRole>)query.setResultTransformer(
				Transformers.aliasToBean(StaffRole.class)).list();
		return lists;
	}
	/**
	 * 添加员工-角色关系
	 * @param SRRelation
	 */
	
	public void saveSRRelation(SRRelation SRRelation){
		getSession().save(SRRelation);
	}
	/**
	 * 删除员工-角色关系
	 * @param SRRelation
	 */	
	
	public void delSRRelation(SRRelation SRRelation){
		getSession().delete(SRRelation);
	}
	/**
	 * 删除员工-角色关系
	 * @param SRRelation
	 */	
	
	public void delSRRelation(String StaffNo,String RoleNo){
		try {
			Query query=getSession().createSQLQuery("delete from tb_SRRelation where SRRelation_StaffNo=:StaffNo and SRRelation_RoleNo=:RoleNo");
			query.setString("StaffNo", StaffNo);
			query.setString("RoleNo", RoleNo);
			query.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	/**
	 * 删除员工-所有角色
	 * @param StaffNo
	 */
	
	public void delSRRelation(String StaffNo){
		try {
			Query query=getSession().createSQLQuery("delete from tb_SRRelation where SRRelation_StaffNo=:StaffNo");
			query.setString("StaffNo", StaffNo);
			query.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
	}

}
