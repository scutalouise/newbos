package com.bap.authority.dao;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.bap.authority.domain.Function;

public interface FunctionDao {

	/**
	 * 添加系统功能
	 * 
	 * @param function
	 */
	@Transactional
	public void addFunction(Function function);

	/**
	 * 修改系统功能
	 * 
	 * @param function
	 */
	@Transactional
	public void updateFunction(String sql);

	public void delFunction(Function function);

	/**
	 * 查询系统功能
	 * 
	 * @param sql 传入要执行的SQL语句
	 * @return Function对象的List集合
	 */
	@Transactional
	public List<Function> selFunction(String sql);

}