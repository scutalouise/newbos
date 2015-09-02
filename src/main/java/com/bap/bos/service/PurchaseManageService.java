package com.bap.bos.service;

import java.util.List;

import com.bap.bos.domain.OrderBill;
import com.bap.bos.domain.RestockBill;
import com.bap.bos.util.Page;

public interface PurchaseManageService {

	/**
	 * 保存订单
	 * @param OrderBill
	 */
	public abstract void saveOrderBill(OrderBill OrderBill);

	/**
	 * 创建订单编号
	 * @param StationNo
	 * @return
	 */
	public abstract String createOrderBillNum(String StationNo);

	/**
	 * 查询订单
	 * @param start 开始时间	
	 * @param end 结束时间
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
	 * 创建入库单编号
	 * @param StationNo 站点编号
	 * @return
	 */
	public abstract String createRestockBillNum(String StationNo);

	/**
	 * 保存入库单
	 * @param RestockBill 入库单实体类
	 */
	public String saveRestockBill(RestockBill restockBill,String stationNo);
	/**
	 * 确认订单
	 * @param OrderBillNum
	 * @return
	 */
	public boolean verifyOrderBill(String OrderBillNum,String StaffNo);
	/**
	 * 取消订单
	 * @param OrderBillNum
	 * @return
	 */
	public boolean cancelOrderBill(String OrderBillNum);
	/**
	 * 查询订单详情
	 * @param OrderBillNum - 订单编号
	 * @return
	 */
	public Object selOrderBill(String OrderBillNum);
	/**
	 * 订货
	 * @param OrdereBill - 订单详情
	 * @return
	 */
	public boolean orderGoods(OrderBill OrdereBill);
	
	/**
	 * 卸油状态更新
	 * @param Num	订单号
	 * @param status	订单状态
	 * @return
	 */
	public boolean unloadingOil(String Num,String status);
	
	void upadteOrderBillRecordStatus(String num,Boolean status);

}