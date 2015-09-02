package com.bap.hos.action;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bap.bos.service.ShiftVerifyService;
import com.bap.bos.util.BaseAction;
import com.bap.bos.util.DateUtil;
import com.bap.bos.util.SellDetails;
import com.bap.hos.service.HosReportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@SuppressWarnings("serial")
@Component("hosReportManageAction")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ReportManageAction extends BaseAction {
	
	Logger logger = LoggerFactory.getLogger(ReportManageAction.class);
	
	@Resource
	private ShiftVerifyService shiftVerifyService;
	
	@Resource
	private HosReportService hosReportService;
	/*属性*/
	private String TransItem_ShiftDate;//班别日期
	private String StationNo;
	private String StationName;

	private String startDate;
	private String endDate;
	private String staffNo;
	private String cardNo;
	private String nozzleNo;
	private String Type;
	private String ProductNum;
	
	private Map<String,Object> Parameter=new HashMap<String,Object>();
	private Connection  con; 
	@Resource(name="dataSourceHos")
	private ComboPooledDataSource dataSource;

	/*返回值*/
	private String ShiftNo;
	private List<SellDetails> sellDetails;
	private String NozzleDetail;
	private String stations;
	
	/**
	 * 查询油站
	 */
	public String selStation(){
		ObjectMapper objectMapper = new ObjectMapper();  
		try{
			stations = objectMapper.writeValueAsString(hosReportService.selStationDetail());
		}catch(Exception e){
			logger.error("查询油站异常！",e);
		}
		return "success";
	}
	/**
	 * 查询班别编号
	 * @return
	*/
	public String selShiftNo(){
		String d=this.getTransItem_ShiftDate();
		String[] c=d.split("-");
		String ShiftDate=c[0]+c[1]+c[2];
		
		ObjectMapper objectMapper = new ObjectMapper();  
		try{
			ShiftNo = objectMapper.writeValueAsString(hosReportService.selCurrentShift(StationNo, ShiftDate));
		}catch(Exception e){
			logger.error("查询班别编号异常！",e);
		}
		return "success";
	}
	/**
	 * 班报表
	 * @return
	 */
	public String reportShift() throws Exception{
		String d=this.getTransItem_ShiftDate();
		String[] c=d.split("-");
		String ShiftDate=c[0]+c[1]+c[2];
		String YearMonth=c[0]+c[1];
		String[] ShiftNo=this.getShiftNo().split("-");
		String[] station=this.getStationNo().split("_");
		Parameter.put("stationName", station[1]);
		Parameter.put("ShiftDate", ShiftDate);
		Parameter.put("YearMonth", Integer.valueOf(YearMonth));
		Parameter.put("ShiftNo", ShiftNo[0]);
		Parameter.put("StartTime", ShiftNo[1]);
		Parameter.put("EndTime", ShiftNo[2]);
		con=dataSource.getConnection();	
		return "success";
	}
	/**
	 * 销售日报表
	 * @return
	 * @throws Exception
	 */
	public String DaySellVolReport() throws Exception{
		//金额升数统计
		String d=this.getTransItem_ShiftDate();
		String e=this.getEndDate();
		String[] c=d.split("-");
		String[] b=e.split("-");
		String SelDate=c[0]+c[1]+c[2];
		String endDate=b[0]+b[1]+b[2];
		String YearMonth=c[0]+c[1];
		String[] station=this.getStationNo().split("_");
		
		String[] dates = new String[2];
		
		dates =	shiftVerifyService.getShiftDateSpanAsString(station[0], SelDate,endDate);
		if(dates[0] != null){
			Parameter.put("StartTime", dates[0]);
		}
		if(dates[1] != null){
			Parameter.put("EndTime", dates[1]);
		}
		
		Parameter.put("stationName", station[1]);
		Parameter.put("SelDate", SelDate);
		Parameter.put("endDate", endDate);
		Parameter.put("YearMonth", Integer.valueOf(YearMonth));
		Parameter.put("StationNo", station[0]);
		con=dataSource.getConnection();	
		return "MoneyVol";	
	}
	
	/**
	 * 月盘点/结算报表
	 * @return
	 * @throws Exception
	 */
	public String MonthReport() throws Exception{	
		
		if("1".equals(this.getType())){
			//月盘点表
			String d=this.getTransItem_ShiftDate();
			SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM");
			Date a=formatDate.parse(d);
			DateUtil DateUtil=new DateUtil();
			String LastYearMonth = DateUtil.getLastMonth(a);
			System.out.print(LastYearMonth);
	//		Integer ProductCount=productDao.selProductDetail().size();
			String[] c=d.split("-");
			String YearMonth=c[0]+c[1];
			String[] station=this.getStationNo().split("_");
			Parameter.put("stationName", station[1]);
			Parameter.put("YearMonth", Integer.valueOf(YearMonth));
			Parameter.put("signYearMonth", YearMonth+"%");
	//		Parameter.put("ProductCount",ProductCount);
			Parameter.put("lastSignYearMonth",LastYearMonth+"%");
			con=dataSource.getConnection();	
	        return "pandian";
		}else if("2".equals(this.getType())){
			//月结算表
			String d=this.getTransItem_ShiftDate();
			String a=this.getEndDate();
			String[] c=d.split("-");
			String[] b=a.split("-");
			String startDate=c[0]+c[1];
			String endDate=b[0]+b[1];
			String[] station=this.getStationNo().split("_");
			Parameter.put("stationName", station[1]);
			Parameter.put("startDate", startDate);
			Parameter.put("endDate", endDate);
			Parameter.put("StationNo", station[0]);
			con=dataSource.getConnection();	
			return "jiesuan";
		}else if("3".equals(this.getType())){
			//月盘点表
			String d=this.getTransItem_ShiftDate();
			SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM");
			Date a=formatDate.parse(d);
			DateUtil DateUtil=new DateUtil();
			String LastYearMonth = DateUtil.getLastMonth(a);
			System.out.print(LastYearMonth);
			String[] c=d.split("-");
			String YearMonth=c[0]+c[1];
			String[] station=this.getStationNo().split("_");
			Parameter.put("stationName", station[1]);
			Parameter.put("YearMonth", Integer.valueOf(YearMonth));
			Parameter.put("signYearMonth", YearMonth+"%");
			Parameter.put("StationNo", station[0]);
			Parameter.put("lastSignYearMonth",LastYearMonth+"%");
			con=dataSource.getConnection();	
	        return "pandian1";
		}
		else{
			return "input";
		}
		
	}
	/**
	 * 库存盘点
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({ "static-access", "deprecation" })
	public String StocktakingReport() throws Exception{
		String a=this.getStartDate();
		String b=this.getEndDate();
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
		//前一天
		Date d=formatDate.parse(a);
		DateUtil DateUtil=new DateUtil();
		String LastDate = DateUtil.getSpecifiedDayBefore(d.toLocaleString());
	//	System.out.print(LastDate);
	//	Integer ProductCount=productDao.selProductDetail().size();
		String[] c=a.split("-");
		String[] h=LastDate.split("-");
		String YearMonth=c[0]+c[1];
		if("1".equals(this.getType())){
			String[] e=b.split("-");
			String[] station=this.getStationNo().split("_");
			Parameter.put("stationName", station[1]);
			Parameter.put("YearMonth", Integer.valueOf(YearMonth));
			Parameter.put("startDate", c[0]+c[1]+c[2]);
			Parameter.put("endDate", e[0]+e[1]+e[2]);
			Parameter.put("SignEndDate", e[0]+e[1]+e[2]);
		//	Parameter.put("ProductCount",ProductCount);
			Parameter.put("SignLastDate",h[0]+h[1]+h[2]);
			con=dataSource.getConnection();
			return "Stocktaking";
		}else if("2".equals(this.getType())){
			String[] station=this.getStationNo().split("_");
			
			String[] dates = shiftVerifyService.getShiftDateSpanAsString(station[0], StringUtils.replace(a, "-", ""), StringUtils.replace(b, "-", ""));
			
			String[] e=b.split("-");
			
			if(dates[0] != null){
				Parameter.put("StartTime", dates[0]);
			}
			if(dates[1] != null){
				Parameter.put("EndTime", dates[1]);
			}
			
			Parameter.put("stationName", station[1]);
			Parameter.put("YearMonth", Integer.valueOf(YearMonth));
			Parameter.put("startDate", c[0]+c[1]+c[2]);
			Parameter.put("endDate", e[0]+e[1]+e[2]);
			Parameter.put("SignEndDate", e[0]+e[1]+e[2]);
			Parameter.put("StationNo", station[0]);
			Parameter.put("SignLastDate",h[0]+h[1]+h[2]);
			con=dataSource.getConnection();
			return "Stocktaking1";
		}else{
			return "input";
		}
       
	}
	
	
	/**
	 * 银行存款报表
	 * @return
	 * @throws Exception
	 */
	public String BankDepositReport() throws Exception{
		String a=this.getStartDate();
		String b=this.getEndDate();
		String[] c=a.split("-");
		String[] d=b.split("-");
		String startDate=c[0]+"-"+c[1]+"-"+c[2]+" 00:00:00";
		String endDate=d[0]+"-"+d[1]+"-"+d[2]+" 23:59:59";
		String[] station=this.getStationNo().split("_");
		Parameter.put("stationName", station[1]);
		Parameter.put("startDate", startDate);
		Parameter.put("endDate", endDate);
		Parameter.put("StationNo", station[0]);
		con=dataSource.getConnection();	
		return "success";
	}
	


	public String getTransItem_ShiftDate() {
		return TransItem_ShiftDate;
	}
	public void setTransItem_ShiftDate(String transItemShiftDate) {
		TransItem_ShiftDate = transItemShiftDate;
	}

	public String getStationNo() {
		return StationNo;
	}

	public void setStationNo(String stationNo) {
		StationNo = stationNo;
	}

	public String getShiftNo() {
		return ShiftNo;
	}

	public void setShiftNo(String shiftNo) {
		ShiftNo = shiftNo;
	}
	public List<SellDetails> getSellDetails() {
		return sellDetails;
	}
	public void setSellDetails(List<SellDetails> sellDetails) {
		this.sellDetails = sellDetails;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getStaffNo() {
		return staffNo;
	}
	public void setStaffNo(String staffNo) {
		this.staffNo = staffNo;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getNozzleNo() {
		return nozzleNo;
	}
	public void setNozzleNo(String nozzleNo) {
		this.nozzleNo = nozzleNo;
	}
	
	public Map<String, Object> getParameter() {
		return Parameter;
	}
	public void setParameter(Map<String, Object> parameter) {
		Parameter = parameter;
	}
	public Connection getCon() {
		return con;
	}
	public void setCon(Connection con) {
		this.con = con;
	}
	public ComboPooledDataSource getDataSource() {
		return dataSource;
	}
	public void setDataSource(ComboPooledDataSource dataSource) {
		this.dataSource = dataSource;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		Type = type;
	}

	public String getNozzleDetail() {
		return NozzleDetail;
	}
	public void setNozzleDetail(String nozzleDetail) {
		NozzleDetail = nozzleDetail;
	}
	public String getProductNum() {
		return ProductNum;
	}
	public void setProductNum(String productNum) {
		ProductNum = productNum;
	}
	
	public String getStations() {
		return stations;
	}
	public void setStations(String stations) {
		this.stations = stations;
	}
	public String getStationName() {
		return StationName;
	}
	public void setStationName(String stationName) {
		StationName = stationName;
	}

	
	

}
