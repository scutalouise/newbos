package com.bap.authority.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bap.authority.dao.FunctionDao;
import com.bap.authority.dao.RoleDao;
import com.bap.authority.domain.Function;
import com.bap.authority.service.FunctionService;

/**
 * 系统功能管理业务逻辑处理实体类
 * 
 * @author zhulong
 * 
 */

@Service
public class FunctionServiceImpl implements FunctionService {
	@Resource
	private FunctionDao fd;
	@Resource private RoleDao rd;

	/**
	 * 添加系统功能
	 * @param function
	 */
	@Transactional
	public void addFunction(Function function) {
		fd.addFunction(function);
	}

	/**
	 * 更新系统功能
	 * @param function
	 */
	@Transactional
	public void updateFunction(Function function) {
		String sql = "update tb_Function set Function_FunctionName='"
				+ function.getFunction_FunctionName() + "' , "
				+ "Function_FunctionState='"
				+ function.getFunction_FunctionState() + "' , "
				+ "Function_URL='" + function.getFunction_URL()
				+ "' where Function_FunctionNo='"
				+ function.getFunction_FunctionNo() + "'";
		fd.updateFunction(sql);
	}

	/**
	 * 查询SubNo级别的所有功能
	 * @param SubNo 功能级别号
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Function> selRankFunction(Integer SubNo, String SystemFlag) {
		String sql = "from Function where Function_SubNo='" + SubNo
				+ "' and Function_SystemFlag='" + SystemFlag + "'";
		List<Function> list = fd.selFunction(sql);
		return list;
	}
	/**
	 * 查询系统功能
	 * @param SystemFlag 系统编号
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Function> selSysFunction(String SystemFlag){
		String hql = "from Function where Function_SystemFlag='" + SystemFlag + "'  order by Function_FunctionNo";
		List<Function> list = fd.selFunction(hql);
		return list;		
	}
	
	/**
	 * 查询ParentNo的子功能
	 * @param ParentNo 父节点编号
	 * @param SystemFlag 功能级别编号
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Function> selChildFunction(Integer ParentNo, String SystemFlag){
		String sql = "from Function where Function_ParentNo='" + ParentNo
		+ "' and Function_SystemFlag='" + SystemFlag + "'";
		List<Function> list = fd.selFunction(sql);
		return list;
	}

	/**
	 * 功能详细信息查询
	 */
	@Transactional(readOnly=true)
	public List<Function> selFunctionDetails(Integer FunctionNo) {
		String sql = "from Function where Function_FunctionNo='" + FunctionNo
				+ "'";
		List<Function> list = fd.selFunction(sql);
		return list;
	}

	/**
	 * 功能信息删除
	 * 
	 * @param FunctionNo 删除的功能No
	 * @param SubNo	删除的功能级别
	 */
	@Transactional
	public void delFunction(Integer FunctionNo, Integer SubNo) {
		
		/*当前功能*/
		List<Function> fc = this.selFunctionDetails(FunctionNo);
		
		/*当前删除功能下级功能*/
		String sql = "from Function where Function_ParentNo='" + FunctionNo
				+ "'";	
		List<Function> delFc = fd.selFunction(sql);
		
		/*第三级别需要删除的功能*/
		List<Function> delFc3 = null;
		/*第四级别需要删除的功能*/
		List<Function> delFc4 = null;
		if (delFc.size() == 0) {
			/* 无下级 */		
			fd.delFunction(fc.get(0));
		} else {
			/* 存在下级 */
			switch (SubNo) {
			case 1:
				for (int i = 0; i < delFc.size(); i++) {
					sql = "from Function where Function_ParentNo='"
							+ delFc.get(i).getFunction_FunctionNo() + "'";
					delFc3 = fd.selFunction(sql);
				}
				if (delFc3.size() == 0) {// 下级中第三级功能不存在
					/* 添加所有需要删除的功能 */
					delFc.addAll(fc);
					/* 进行删除 */
					for (int k = 0; k < delFc.size(); k++) {
						//删除角色-功能关系
						sql="delete from tb_RFRelation where RFRelation_FunctionNo='"+delFc.get(k).getFunction_FunctionNo()+"'";
						rd.delRole_Function(sql);
						//删除功能
						fd.delFunction(delFc.get(k));
					}
					break;
				} else {// 下级中第三级功能存在
					for (int m = 0; m < delFc3.size(); m++) {
						sql = "from Function where Function_ParentNo='"
								+ delFc3.get(m).getFunction_FunctionNo() + "'";
						delFc4 = fd.selFunction(sql);
					}
					if (delFc4.size() == 0) {
						/* 添加所有需要删除的功能 */
						delFc.addAll(fc);
						delFc.addAll(delFc3);
						/* 进行删除 */
						for (int k = 0; k < delFc.size(); k++) {
							//删除角色-功能关系
							sql="delete from tb_RFRelation where RFRelation_FunctionNo='"+delFc.get(k).getFunction_FunctionNo()+"'";
							rd.delRole_Function(sql);
							//删除功能
							fd.delFunction(delFc.get(k));
						}
						break;
					} else {
						/* 添加所有需要删除的功能 */
						delFc.addAll(fc);
						delFc.addAll(delFc3);
						delFc.addAll(delFc4);
						/* 进行删除 */
						for (int k = 0; k < delFc.size(); k++) {
							//删除角色-功能关系
							sql="delete from tb_RFRelation where RFRelation_FunctionNo='"+delFc.get(k).getFunction_FunctionNo()+"'";
							rd.delRole_Function(sql);
							//删除功能
							fd.delFunction(delFc.get(k));
						}
						break;
					}
				}
			case 2:		
				for (int m = 0; m < delFc.size(); m++) {
					sql = "from Function where Function_ParentNo='"
							+ delFc.get(m).getFunction_FunctionNo() + "'";
					delFc4 = fd.selFunction(sql);
				}
				if (delFc4.size() == 0) {
					/* 添加所有需要删除的功能 */
					delFc.addAll(fc);
					/* 进行删除 */
					for (int k = 0; k < delFc.size(); k++) {
						//删除角色-功能关系
						sql="delete from tb_RFRelation where RFRelation_FunctionNo='"+delFc.get(k).getFunction_FunctionNo()+"'";
						rd.delRole_Function(sql);
						//删除功能
						fd.delFunction(delFc.get(k));
					}
					break;
				} else {
					/* 添加所有需要删除的功能 */
					delFc.addAll(fc);
					delFc.addAll(delFc4);
					/* 进行删除 */
					for (int k = 0; k < delFc.size(); k++) {
						//删除角色-功能关系
						sql="delete from tb_RFRelation where RFRelation_FunctionNo='"+delFc.get(k).getFunction_FunctionNo()+"'";
						rd.delRole_Function(sql);
						//删除功能
						fd.delFunction(delFc.get(k));
					}
					break;
				}
			case 3:
				/* 添加所有需要删除的功能 */
				delFc.addAll(fc);
				/* 进行删除 */
				for (int k = 0; k < delFc.size(); k++) {
					//删除角色-功能关系
					sql="delete from tb_RFRelation where RFRelation_FunctionNo='"+delFc.get(k).getFunction_FunctionNo()+"'";
					rd.delRole_Function(sql);
					//删除功能
					fd.delFunction(delFc.get(k));
				}
				break;
			default:
				break;
			}

		}

	}

	public FunctionDao getFd() {
		return fd;
	}

	public void setFd(FunctionDao fd) {
		this.fd = fd;
	}
	public RoleDao getRd() {
		return rd;
	}
	public void setRd(RoleDao rd) {
		this.rd = rd;
	}

}
