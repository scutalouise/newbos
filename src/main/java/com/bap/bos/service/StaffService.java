package com.bap.bos.service;

import java.util.List;

import com.bap.bos.domain.PositionType;
import com.bap.bos.domain.Staff;
import com.bap.bos.util.Page;

/**
 * 
 * 
 * @author edgar_chan     lineshow7@gmail.com
 * @since 2015年3月16日
 */
public interface StaffService {


	/**
	 * 查询职位划分表
	 * @param hql
	 * @return
	 */
	
	public List<PositionType> selPositionType();

	/**
	 * 查询员工信息
	 * @param hql
	 * @return
	 */
	
	public List<Staff> selStaffDetail(String StaffNo,String StaffPW);
	
	/**
	 * 查询ALL员工信息
	 * @param hql
	 * @return
	 */
	
	public List<Object> selAllStaffsDetail(Page page);
	public List<Staff> selAllStaffsDetail();
	/**
	 * 查询员工信息-自动补全
	 * @param FirstName - 姓氏
	 * @return
	 */
	
	public List<Staff> selStaffName(String FirstName);
	/**
	 * 查询员工信息
	 * @param Name -姓名
	 * @param page -分页类
	 * @return
	 */
	
	public List<Object> selStaffName(String Name,Page page);
	/**
	 * 查询员工信息-自动补全
	 * @param FirstNo - 员工编号前几位
	 * @return
	 */
	
	public List<Staff> selStaffNo(String FirstNo);
	/**
	 * 查询员工信息
	 * @param No -员工编号
	 * @param page -分页类
	 * @return
	 */
	
	public List<Object> selStaffNo(String No,Page page);
	/**
	 * 查询员工信息-自动补全
	 * @param FirstID - 员工身份证前几位
	 * @return
	 */
	
	public List<Staff> selStaffID(String FirstID);
	/**
	 * 查询员工信息
	 * @param ID -员工身份证号
	 * @param page -分页类
	 * @return
	 */
	
	public List<Object> selStaffID(String ID,Page page);
	/**
	 * 查询员工信息
	 * @param Status -员工在职状态
	 * @param page -分页类
	 * @return
	 */
	
	public List<Object> selStaffStatus(String Status,Page page);
	
	public List<Object> selStaffStatus(String Status);
	/**
	 * 查询员工上下班信息
	 * @param start	查询开始时间点
	 * @param end	查询结束时间点
	 * @param selType	查询类型   -1：全部    1：姓名    2：员工号    3：身份证号	
	 * @param selWord	查询关键字
	 * @return
	 */
	
	public List<Object> selAllStaffGroupsInfo(String start, String end,String selType,String selWord) ;
	public List<Object> selAllStaffGroupsInfo(String start, String end,String selType,String selWord,Page page);

}
