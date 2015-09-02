package com.bap.bos.dao;

import java.util.List;

import com.bap.bos.domain.OrderBill;
import com.bap.bos.util.Page;

public interface OrderBillDao {

	/**
	 * 保存订单
	 * @param OrderBill
	 */
	public abstract void saveOrderBill(OrderBill OrderBill);

	/**
	 * 更新订单
	 * @param sql 更新sql语句
	 */
	public abstract void updateOrderBill(String sql);
	
	/**
	 * 更新订单
	 * @param OrdereBill - 订单类
	 */
	public void updateOrdereBill(OrderBill OrdereBill);

	/**
	 * 查询当前日期已经存在的订单数
	 * @param OrderBillNumFront
	 * @return
	 */

	public abstract Integer selOrderBill(String OrderBillNumFront);
	/**
	 * 查询订单详情
	 * @param OrderBillNum
	 * @return
	 */
	public Object selOrderBillDetail(String OrderBillNum);
	
	/**
	 * 查询订单
	 * @param start 创建订单时间-始
	 * @param end 创建订单时间-末
	 * @param ProductType 产品类型
	 * @param Status 订单状态
	 * @return
	 */
	public abstract List<OrderBill> selOrderBill(String StationNo,String start,String end,
			String QueryType,String QueryContext,String Status);

	public abstract List<OrderBill> selOrderBill(String StationNo,String start,String end,
			String QueryType,String QueryContext,String Status,
			Page page);
	/**
	 * 更新订单录入方式
	 * @param orderNum
	 * @param recordStatus
	 * @author edgar_chan
	 * @since 2015年5月6日
	 */
	void upadteOrderBillRecordStatus(String orderNum,Boolean recordStatus);
	
	/**
	 * 入库时更新订单
	 * @param status
	 * @param isRemedy
	 * @param billNum
	 * @author edgar_chan
	 * @since 2015年5月15日
	 */
	public void updateOrderBillOnRestock(String status,Boolean isRemedy,String billNum);
}