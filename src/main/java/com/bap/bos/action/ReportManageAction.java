package com.bap.bos.action;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bap.bos.dao.NozzleSettingDao;
import com.bap.bos.domain.TransItem;
import com.bap.bos.service.ProductService;
import com.bap.bos.service.ReportManageService;
import com.bap.bos.service.ShiftVerifyService;
import com.bap.bos.service.TranItemService;
import com.bap.bos.util.BaseAction;
import com.bap.bos.util.DateUtil;
import com.bap.bos.util.SellDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@SuppressWarnings("serial")
@Component("reportManageAction")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ReportManageAction extends BaseAction {
	
	Logger logger = LoggerFactory.getLogger(ReportManageAction.class);
	
	/*注入操作类*/
	@Resource private ReportManageService reportManageService;
	@Resource private TranItemService tranItemService;
	@Resource private ProductService productService;
	@Resource private ShiftVerifyService shiftVerifyService;
	
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
	@Resource(name="dataSource")
	private ComboPooledDataSource dataSource;
	@Resource
	private NozzleSettingDao nozzleSettingDao;

	/*返回值*/
	private String ShiftNo;
	private List<TransItem> TransItem;
	private List<SellDetails> sellDetails;
	private String NozzleDetail;
	
	/**
	 * 查询班别编号
	 * @return
	 */
	public String selShiftNo(){
		String d=this.getTransItem_ShiftDate();
		ShiftNo = selShiftNo(d);
		return "success";
	}
	
	/**
	 * 查询班别编号
	 * @return
	 */
	private String selShiftNo(String d){
		String[] c=d.split("-");
		String ShiftDate=c[0]+c[1]+c[2];
		ObjectMapper objectMapper = new ObjectMapper();  
		try{
			return objectMapper.writeValueAsString(reportManageService.selCurrentShift(StationNo, ShiftDate));
		}catch(Exception e){
			logger.error("查询班别编号异常！",e);
		}
		return "";
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
		Parameter.put("ShiftDate", ShiftDate);
		Parameter.put("YearMonth", Integer.valueOf(YearMonth));
		Parameter.put("ShiftNo", ShiftNo[0]);
		Parameter.put("StartTime", ShiftNo[1]);
		Parameter.put("EndTime", ShiftNo[2]);
		Parameter.put("stationName", StationName);
		con=dataSource.getConnection();
		return "success";
	}
	/**
	 * 销售日报表
	 * @return
	 * @throws Exception
	 */
	public String DaySellVolReport() throws Exception{
		if("1".equals(this.getType())){
			//升数统计
			String d=this.getTransItem_ShiftDate();
			
			Date[] datas =	shiftVerifyService.getShiftDateSpan(StationNo, StringUtils.replace(d, "-",""));
			if(datas != null){
				Parameter.put("StartTime", new DateTime(datas[0]).toString("yyyy/MM/dd HH:mm:ss"));
				Parameter.put("EndTime", new DateTime(datas[1]).toString("yyyy/MM/dd HH:mm:ss"));
			}
			
			String[] c=d.split("-");
			String SelDate=c[0]+c[1]+c[2];
			String YearMonth=c[0]+c[1];
			Parameter.put("SelDate", SelDate);
			Parameter.put("YearMonth", Integer.valueOf(YearMonth));
			Parameter.put("stationName", StationName);
			con=dataSource.getConnection();	
			return "Vol";
		}else if("2".equals(this.getType())){
			//金额统计
			String d=this.getTransItem_ShiftDate();
			
			Date[] datas =	shiftVerifyService.getShiftDateSpan(StationNo, StringUtils.replace(d, "-",""));
			if(datas != null){
				Parameter.put("StartTime", new DateTime(datas[0]).toString("yyyy/MM/dd HH:mm:ss"));
				Parameter.put("EndTime", new DateTime(datas[1]).toString("yyyy/MM/dd HH:mm:ss"));
			}
			
			String[] c=d.split("-");
			String SelDate=c[0]+c[1]+c[2];
			String YearMonth=c[0]+c[1];
			Parameter.put("SelDate", SelDate);
			Parameter.put("YearMonth", Integer.valueOf(YearMonth));
			Parameter.put("stationName", StationName);
			con=dataSource.getConnection();	
			return "Money";
		}else if("3".equals(this.getType())){
			//金额升数统计
			String d=this.getTransItem_ShiftDate();
			String e=this.getEndDate();	
			Date[] datas =	shiftVerifyService.getShiftDateSpan(StationNo, StringUtils.replace(d, "-",""),StringUtils.replace(e, "-",""));
			if(datas[0] != null){
				Parameter.put("StartTime", new DateTime(datas[0]).toString("yyyy/MM/dd HH:mm:ss"));
			}
			if(datas[1] != null){
				Parameter.put("EndTime", new DateTime(datas[1]).toString("yyyy/MM/dd HH:mm:ss"));
			}
			
			String[] c=d.split("-");
			String[] b=e.split("-");
			String SelDate=c[0]+c[1]+c[2];
			String endDate=b[0]+b[1]+b[2];
			String YearMonth=c[0]+c[1];
			Parameter.put("SelDate", SelDate);
			Parameter.put("endDate", endDate);
			Parameter.put("YearMonth", Integer.valueOf(YearMonth));
			Parameter.put("stationName", StationName);
			con=dataSource.getConnection();	
			return "MoneyVol";
		}else{
			return "input";
		}
		
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
			Parameter.put("YearMonth", Integer.valueOf(YearMonth));
			Parameter.put("signYearMonth", YearMonth+"%");
	//		Parameter.put("ProductCount",ProductCount);
			Parameter.put("lastSignYearMonth",LastYearMonth+"%");
			Parameter.put("stationName", StationName);
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
			Parameter.put("startDate", startDate);
			Parameter.put("endDate", endDate);
			Parameter.put("stationName", StationName);
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
	//		Integer ProductCount=productDao.selProductDetail().size();
			String[] c=d.split("-");
			String YearMonth=c[0]+c[1];
			Parameter.put("YearMonth", Integer.valueOf(YearMonth));
			Parameter.put("signYearMonth", YearMonth+"%");
	//		Parameter.put("ProductCount",ProductCount);
			Parameter.put("lastSignYearMonth",LastYearMonth+"%");
			Parameter.put("stationName", StationName);
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
			Parameter.put("YearMonth", Integer.valueOf(YearMonth));
			Parameter.put("startDate", c[0]+c[1]+c[2]);
			Parameter.put("endDate", e[0]+e[1]+e[2]);
			Parameter.put("SignEndDate", e[0]+e[1]+e[2]);
		//	Parameter.put("ProductCount",ProductCount);
			Parameter.put("SignLastDate",h[0]+h[1]+h[2]);
			Parameter.put("stationName", StationName);
			
			Date[] datas =	shiftVerifyService.getShiftDateSpan(StationNo, StringUtils.replace(a, "-",""),StringUtils.replace(b, "-",""));
			if(datas[0] != null){
				Parameter.put("StartTime", new DateTime(datas[0]).toString("yyyy/MM/dd HH:mm:ss"));
			}
			if(datas[1] != null){
				Parameter.put("EndTime", new DateTime(datas[1]).toString("yyyy/MM/dd HH:mm:ss"));
			}
			
			con=dataSource.getConnection();
			return "Stocktaking";
		}else if("2".equals(this.getType())){
			String[] e=b.split("-");
			Parameter.put("YearMonth", Integer.valueOf(YearMonth));
			Parameter.put("startDate", c[0]+c[1]+c[2]);
			Parameter.put("endDate", e[0]+e[1]+e[2]);
			Parameter.put("SignEndDate", e[0]+e[1]+e[2]);
		//	Parameter.put("ProductCount",ProductCount);
			Parameter.put("SignLastDate",h[0]+h[1]+h[2]);
			Parameter.put("stationName", StationName);
			Date[] datas =	shiftVerifyService.getShiftDateSpan(StationNo, StringUtils.replace(a, "-",""),StringUtils.replace(b, "-",""));
			if(datas[0] != null){
				Parameter.put("StartTime", new DateTime(datas[0]).toString("yyyy/MM/dd HH:mm:ss"));
			}
			if(datas[1] != null){
				Parameter.put("EndTime", new DateTime(datas[1]).toString("yyyy/MM/dd HH:mm:ss"));
			}
			
			con=dataSource.getConnection();
			return "Stocktaking1";
		}else if("3".equals(this.getType())){
			Parameter.put("selDate", c[0]+c[1]+c[2]);
			Parameter.put("selDateMonthDay", c[1]+"-"+c[2]);
			Parameter.put("stationName", StationName);
			con=dataSource.getConnection();
			return "celiangTank";
		}else{
			return "input";
		}
       
	}
	
	
	/**
	 * 加油机加油统计报表
	 * @return
	 */
	public String NozzleReport() throws Exception{
		String a=this.getStartDate();
		String b=this.getEndDate();
		String[] c=a.split("-");
		String[] d=b.split("-");
		String startDate=c[0]+c[1]+c[2];
		String endDate=d[0]+d[1]+d[2];
		Parameter.put("startDate", startDate);
		Parameter.put("endDate", endDate);
		Parameter.put("stationName", StationName);
		con=dataSource.getConnection();	
		return "success";
	}
	
	/**
	 * 查询所有枪
	 * @return
	 */
	public String selNozzle(){
		ObjectMapper objectMapper = new ObjectMapper();  
		try{
			NozzleDetail = objectMapper.writeValueAsString(nozzleSettingDao.selNozzleSetting());
		}catch(Exception e){
			logger.error(" 查询所有枪异常！",e);
		}
		return "success";
	}
	
	/**
	 * 加油明细报表
	 * @return
	 * @throws Exception
	 */
	public String TransDetailsReport()throws Exception{
		con=dataSource.getConnection();	
		Integer Type=Integer.valueOf(this.getType());
		String Result="";
		String a,b,startDate,endDate,YearMonth;
		String[] c,d,endDateArray; 
		Date[] datas =	new Date[2];
		switch (Type) {
		case 1:
			//加油明细报表
			a=this.getStartDate();
			endDate=this.getEndDate();
			c=a.split("-");
			startDate=c[0]+c[1]+c[2];
			YearMonth=c[0]+c[1];
			endDateArray=endDate.split("-");
			
			datas =	shiftVerifyService.getShiftDateSpan(StationNo, StringUtils.replace(a, "-",""),StringUtils.replace(endDate, "-",""));
			if(datas[0] != null){
				String startTimeStr = new DateTime(datas[0]).toString("yyyy/MM/dd HH:mm:ss");
				logger.debug("加油明细报表开始查询时间:"+startTimeStr);
				Parameter.put("StartTime", startTimeStr);
			}
			if(datas[1] != null){
				String endTimeStr = new DateTime(datas[1]).toString("yyyy/MM/dd HH:mm:ss");
				logger.debug("加油明细报表结束查询时间:"+endTimeStr);
				Parameter.put("EndTime", endTimeStr);
			}
			logger.debug("加油明细报表YearMonth:"+YearMonth);
			logger.debug("加油明细报表startDate:"+startDate);
			logger.debug("加油明细报表endDate:"+ endDateArray[0]+endDateArray[1]+endDateArray[2]);
			logger.debug("加油明细报表NozNo:"+this.getNozzleNo());
			logger.debug("加油明细报表ProductNum:"+this.getNozzleNo());
			logger.debug("加油明细报表StationName:"+StationName);
			
			Parameter.put("YearMonth", Integer.valueOf(YearMonth));
			Parameter.put("startDate", startDate);
			Parameter.put("endDate", endDateArray[0]+endDateArray[1]+endDateArray[2]);
			Parameter.put("NozNo", this.getNozzleNo());
			Parameter.put("CardNo", this.getCardNo().trim());
			Parameter.put("ProductNum", this.getProductNum());
			Parameter.put("stationName", StationName);
			Result="TransDetails";
			break;
		case 2:
			//补录交易明细报表
			a=this.getStartDate();
			c=a.split("-");
			startDate=c[0]+c[1]+c[2];
			YearMonth=c[0]+c[1];
			
			datas =	shiftVerifyService.getShiftDateSpan(StationNo, StringUtils.replace(a, "-",""));
			if(datas[0] != null){
				Parameter.put("StartTime", new DateTime(datas[0]).toString("yyyy/MM/dd HH:mm:ss"));
			}
			if(datas[1] != null){
				Parameter.put("EndTime", new DateTime(datas[1]).toString("yyyy/MM/dd HH:mm:ss"));
			}
			
			Parameter.put("YearMonth", Integer.valueOf(YearMonth));
			Parameter.put("startDate", startDate);
			Parameter.put("stationName", StationName);
			Result="BuLuTrans";
			break;
		case 3:
			//回罐交易明细报表
			a=this.getStartDate();
			b=this.getEndDate();
			
			datas =	shiftVerifyService.getShiftDateSpan(StationNo, StringUtils.replace(a, "-",""),StringUtils.replace(b, "-",""));
			if(datas[0] != null){
				Parameter.put("StartTime", new DateTime(datas[0]).toString("yyyy/MM/dd HH:mm:ss"));
			}
			if(datas[1] != null){
				Parameter.put("EndTime", new DateTime(datas[1]).toString("yyyy/MM/dd HH:mm:ss"));
			}
			
			c=a.split("-");
			d=b.split("-");
			startDate=c[0]+c[1]+c[2];
			endDate=d[0]+d[1]+d[2];
			YearMonth=c[0]+c[1];
			Parameter.put("YearMonth", Integer.valueOf(YearMonth));
			Parameter.put("startDate", startDate);
			Parameter.put("endDate", endDate);
			Parameter.put("stationName", StationName);
			Result="HuiGuanTrans";
			break;
		case 4:
			//自用卡交易明细报表
			a=this.getStartDate();
			b=this.getEndDate();
			
			
			datas =	shiftVerifyService.getShiftDateSpan(StationNo, StringUtils.replace(a, "-",""),StringUtils.replace(b, "-",""));
			if(datas[0] != null){
				Parameter.put("StartTime", new DateTime(datas[0]).toString("yyyy/MM/dd HH:mm:ss"));
			}
			if(datas[1] != null){
				Parameter.put("EndTime", new DateTime(datas[1]).toString("yyyy/MM/dd HH:mm:ss"));
			}
			
			c=a.split("-");
			d=b.split("-");
			startDate=c[0]+c[1]+c[2];
			endDate=d[0]+d[1]+d[2];
			YearMonth=c[0]+c[1];
			Parameter.put("YearMonth", Integer.valueOf(YearMonth));
			Parameter.put("startDate", startDate);
			Parameter.put("endDate", endDate);
			Parameter.put("stationName", StationName);
			Result="ZiYongKaTrans";
			break;
		case 5:
			//客户卡交易细报表
			a=this.getStartDate();
			b=this.getEndDate();
			
			datas =	shiftVerifyService.getShiftDateSpan(StationNo, StringUtils.replace(a, "-",""),StringUtils.replace(b, "-",""));
			if(datas[0] != null){
				Parameter.put("StartTime", new DateTime(datas[0]).toString("yyyy/MM/dd HH:mm:ss"));
			}
			if(datas[1] != null){
				Parameter.put("EndTime", new DateTime(datas[1]).toString("yyyy/MM/dd HH:mm:ss"));
			}
			
			c=a.split("-");
			d=b.split("-");
			startDate=c[0]+c[1]+c[2];
			endDate=d[0]+d[1]+d[2];
			YearMonth=c[0]+c[1];
			Parameter.put("YearMonth", Integer.valueOf(YearMonth));
			Parameter.put("startDate", startDate);
			Parameter.put("endDate", endDate);
			Parameter.put("stationName", StationName);
			Result="KeHuiKaTrans";
			break;
		case 6:
			//优惠卡交易明细报表
			a=this.getStartDate();
			b=this.getEndDate();
			
			datas =	shiftVerifyService.getShiftDateSpan(StationNo, StringUtils.replace(a, "-",""),StringUtils.replace(b, "-",""));
			if(datas[0] != null){
				Parameter.put("StartTime", new DateTime(datas[0]).toString("yyyy/MM/dd HH:mm:ss"));
			}
			if(datas[1] != null){
				Parameter.put("EndTime", new DateTime(datas[1]).toString("yyyy/MM/dd HH:mm:ss"));
			}
			
			c=a.split("-");
			d=b.split("-");
			startDate=c[0]+c[1]+c[2];
			endDate=d[0]+d[1]+d[2];
			YearMonth=c[0]+c[1];
			Parameter.put("YearMonth", Integer.valueOf(YearMonth));
			Parameter.put("startDate", startDate);
			Parameter.put("endDate", endDate);
			Parameter.put("stationName", StationName);
			Result="YouHuiKaTrans";
			break;
		case 7:
			//现金交易细报表
			a=this.getStartDate();
			b=this.getEndDate();
			
			datas =	shiftVerifyService.getShiftDateSpan(StationNo, StringUtils.replace(a, "-",""),StringUtils.replace(b, "-",""));
			if(datas[0] != null){
				Parameter.put("StartTime", new DateTime(datas[0]).toString("yyyy/MM/dd HH:mm:ss"));
			}
			if(datas[1] != null){
				Parameter.put("EndTime", new DateTime(datas[1]).toString("yyyy/MM/dd HH:mm:ss"));
			}
			
			c=a.split("-");
			d=b.split("-");
			startDate=c[0]+c[1]+c[2];
			endDate=d[0]+d[1]+d[2];
			YearMonth=c[0]+c[1];
			Parameter.put("YearMonth", Integer.valueOf(YearMonth));
			Parameter.put("startDate", startDate);
			Parameter.put("endDate", endDate);
			Parameter.put("stationName", StationName);
			Result="XianJinTrans";
			break;
		default:
			break;
		}
		return Result;
	}
	/**
	 * 银行存款报表
	 * @return
	 * @throws Exception
	 */
	public String BankDepositReport() throws Exception{
		String a=this.getStartDate();
		String b=this.getEndDate();
		
		Date[] datas =	shiftVerifyService.getShiftDateSpan(StationNo, StringUtils.replace(a, "-",""),StringUtils.replace(b, "-",""));
		if(datas[0] != null){
			Parameter.put("StartTime", new DateTime(datas[0]).toString("yyyy/MM/dd HH:mm:ss"));
		}
		if(datas[1] != null){
			Parameter.put("EndTime", new DateTime(datas[1]).toString("yyyy/MM/dd HH:mm:ss"));
		}
		
		
/*		select Station_Name,SaveToBank_ActSaved,SaveToBank_Bank,SaveToBank_SaveTime,SaveToBank_Remark
		from tb_SaveToBank
		left join tb_Station on Station_No=SaveToBank_StationNo
		where  SaveToBank_IsSync <> 2 and SaveToBank_SaveTime between $P{startDate} and $P{endDate}
		*/
		
		String[] c=a.split("-");
		String[] d=b.split("-");
		String startDate=c[0]+"-"+c[1]+"-"+c[2]+" 00:00:00";
		String endDate=d[0]+"-"+d[1]+"-"+d[2]+" 23:59:59";
		Parameter.put("startDate", startDate);
		Parameter.put("endDate", endDate);
		Parameter.put("stationName", StationName);
		Parameter.put("stationName", StationName);
		Parameter.put("TotalActSaved", StationName);
		
		
		con=dataSource.getConnection();	
		return "success";
	}
	
	/**
	 * 月销售计划完成统计报表
	 * @return
	 * @throws Exception
	 */
	public String MonthSellPlanReport() throws Exception{
		String a=this.getStartDate();
		String b=this.getEndDate();
		String[] c=a.split("-");
		String[] d=b.split("-");
		String startDate=c[0]+c[1];
		String endDate=d[0]+d[1];
		
		Parameter.put("startDate", startDate);
		Parameter.put("endDate", endDate);
		Parameter.put("stationName", StationName);
		con=dataSource.getConnection();	
		return "success";
	}
	
	/**
	 * 同期报表
	 * @return
	 */
	public String yearOnYearShift() throws Exception{
		String d=this.getStartDate();
		Parameter.put("Year", d);
		Parameter.put("stationName", StationName);
		con=dataSource.getConnection();	
		return "success";
	}
	/**
	 * 销售图表
	 * @return
	 * @throws Exception
	 
	private StringBuffer vpnbuffer = null; 
	public String SellAnalyzeGraph()throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest ();
		con=dataSource.getConnection();	
		Integer Type=Integer.valueOf(this.getType());
		String Result="";
		String a,Date,Date1,YearMonth;
		String[] c; 
		
		switch (Type) {
		case 1:
			//加油站日时点加油分析图
			a=this.getStartDate();
			c=a.split("-");
			Date=c[0]+c[1]+c[2];
			YearMonth=c[0]+c[1];
			Parameter.put("YearMonth", Integer.valueOf(YearMonth));
			Parameter.put("Date", Date);
			vpnbuffer = new StringBuffer();  
			File reportFile = new File(ServletActionContext.getServletContext().getRealPath(  
					File.separator +"bos"+File.separator + "Report" + File.separator + "加油站日时点加油分析图.jasper"));
			try {  
	            ChartReportUtil.getAndExpChart(vpnbuffer, con, request, reportFile,Parameter);  
			} catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        System.out.println("sbuffer"+vpnbuffer);  
			
			//直接操作生成的jrxml文件 
//			String fileName = request.getSession().getServletContext()
//										.getRealPath("/bos/Report/加油站月油品销量分析图.jrxml");
//			System.out.print("fileName:"+fileName);
//	        JasperReport jasperReport = null;  
//	        JasperPrint jasperPrint = null;
//	        jasperReport = JasperCompileManager.compileReport(fileName);//编译jrxml文件  
//	        jasperPrint = JasperFillManager.fillReport(jasperReport, Parameter,con);  
//	        JasperExportManager.exportReportToHtmlFile(jasperPrint, request.getSession().getServletContext()
//					.getRealPath("/")+"first.html");
			Result="DayTime";
			break;
		case 2:
			//加油站日付油结构分析图
			a=this.getStartDate();
			c=a.split("-");
			Date=c[0]+c[1]+c[2];
			Parameter.put("Date", Date);
			Result="DayStructure";
			break;
		case 3:
			//加油站日销量交易类型分析图
			a=this.getStartDate();
			c=a.split("-");
			Date=c[0]+c[1]+c[2];
			Parameter.put("Date", Date);
			Result="DayTransType";
			break;
		case 4:
			//加油站月油品销量分析图
			Date=this.getStartDate();
		//	Parameter.put("SignYear", Date+"%");
			Parameter.put("Year", Date);
			Result="MonthSell";
			break;
		case 5:
			//加油站年销售对比分析图
			Date=this.getStartDate();
			Date1=this.getEndDate();
			Parameter.put("SignOneDate", Date+"%");
			Parameter.put("SignTwoDate", Date1+"%");
			Parameter.put("OneYear", Date);
			Parameter.put("TwoYear", Date1);
			Result="YearSellCompare";
			break;
		default:
			break;
		}
		return Result;
	}
	
	
//	public String reportSellDetails() throws Exception{
//		Parameter.put("startDate", this.getStartDate());
//		Parameter.put("endDate", this.getEndDate());
//        String[] c=startDate.split("-");
//		String YearMonth=c[0]+c[1];
//		Parameter.put("YearMonth", Integer.valueOf(YearMonth)); 
//		Parameter.put("StaffNo", this.getStaffNo()); 
//		Parameter.put("CardNo", this.getCardNo());
//		Parameter.put("NozzleNo", this.getNozzleNo()); 
//		con=dataSource.getConnection();	
//		return "success";	
//	}
	*/
	
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
	public List<TransItem> getTransItem() {
		return TransItem;
	}
	public void setTransItem(List<TransItem> transItem) {
		TransItem = transItem;
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
	public NozzleSettingDao getNozzleSettingDao() {
		return nozzleSettingDao;
	}
	public void setNozzleSettingDao(NozzleSettingDao nozzleSettingDao) {
		this.nozzleSettingDao = nozzleSettingDao;
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
	public String getStationName() {
		return StationName;
	}
	public void setStationName(String stationName) {
		StationName = stationName;
	}

	
	

}
