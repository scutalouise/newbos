package com.bap.authority.service;

import java.util.List;

import com.bap.authority.domain.Function;

public interface FunctionService {

	/**
	 * 添加系统功能
	 * @param function
	 */
	public void addFunction(Function function);

	/**
	 * 更新系统功能
	 * @param function
	 */
	public void updateFunction(Function function);

	/**
	 * 查询SubNo级别的所有功能
	 * @param SubNo 功能级别号
	 * @return
	 */
	public List<Function> selRankFunction(Integer SubNo, String SystemFlag);
	/**
	 * 查询系统功能
	 * @param SystemFlag 系统编号
	 * @return
	 */
	public List<Function> selSysFunction(String SystemFlag);
	/**
	 * 查询ParentNo的子功能
	 * @param ParentNo 父节点编号
	 * @param SystemFlag 功能级别编号
	 * @return
	 */
	public List<Function> selChildFunction(Integer ParentNo, String SystemFlag);
	/**
	 * 查询功能详情
	 * @param FunctionNo 功能编号
	 * @return
	 */
	public List<Function> selFunctionDetails(Integer FunctionNo);
	
	/**
	 * 功能信息删除
	 * 
	 * @param FunctionNo 删除的功能No
	 * @param SubNo	删除的功能级别
	 */
	public void delFunction(Integer FunctionNo, Integer SubNo);

}