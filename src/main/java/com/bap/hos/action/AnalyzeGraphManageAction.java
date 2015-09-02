package com.bap.hos.action;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.jasperreports.engine.JasperRunManager;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bap.bos.domain.TransItem;
import com.bap.bos.util.BaseAction;
import com.bap.bos.util.SellDetails;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@SuppressWarnings("serial")

@Component("hosAnalyzeGraphManageAction")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AnalyzeGraphManageAction extends BaseAction {
	/*注入操作类*/

	/*属性*/
	private String TransItem_ShiftDate;//班别日期
	private String StationNo;

	private String startDate;
	private String startDate1;
	private String endDate;
	private String staffNo;
	private String cardNo;
	private String nozzleNo;
	private String Type;
	
	private Map<String,Object> Parameter=new HashMap<String,Object>();
	private Connection  con; 
	@Resource(name="dataSourceHos")
	private ComboPooledDataSource dataSource;

	/*返回值*/
	private String ShiftNo;
	private List<TransItem> TransItem;
	private List<SellDetails> sellDetails;
	
	private StringBuffer vpnbuffer = null; 
	
	/**
	 * 销售图表
	 * @return
	 * @throws Exception
	 */

	public String SellAnalyzeGraph()throws Exception{
		con=dataSource.getConnection();	
		String[] station=this.getStationNo().split("_");;
		Integer Type=Integer.valueOf(this.getType());
		String Result="";
		String a,Date,Date1,YearMonth,reportPath,destFileName;
		String[] c; 
		switch (Type) {
		case 1:
			//加油站日时点加油分析图
			a=this.getStartDate1();
			c=a.split("-");
			Date=c[0]+c[1]+c[2].substring(0,2);
			YearMonth=c[0]+c[1];
			Parameter.put("stationName", station[1]);
			Parameter.put("Date", Date);
			Parameter.put("YearMonth", Integer.valueOf(YearMonth));
			Parameter.put("StationNo", station[0]);
			reportPath=ServletActionContext.getServletContext().getRealPath(File.separator 
					+"bos"+File.separator + "hosReport" + File.separator + "加油站日时点加油分析图.jasper");
			destFileName=ServletActionContext.getServletContext().getRealPath(File.separator 
					+"bos"+File.separator + "hosReport" + File.separator + "JYZRSDFXT.PDF");
			//JasperRunManager.runReportToHtmlFile(reportPath, destFileName, Parameter, con); 
			JasperRunManager.runReportToPdfFile(reportPath, destFileName, Parameter, con);
			con.close();
			Result="DayTime";
			break;
		case 2:
			//加油站日付油结构分析图
			a=this.getStartDate1();
			c=a.split("-");
			Date=c[0]+c[1]+c[2].substring(0,2);
			Parameter.put("stationName", station[1]);
			Parameter.put("Date", Date);
			Parameter.put("StationNo", station[0]);
			reportPath=ServletActionContext.getServletContext().getRealPath(File.separator 
					+"bos"+File.separator + "hosReport" + File.separator + "加油站日付油结构分析图.jasper");
			destFileName=ServletActionContext.getServletContext().getRealPath(File.separator 
					+"bos"+File.separator + "hosReport" + File.separator + "JYZRFYJGFXT.PDF");
			JasperRunManager.runReportToPdfFile(reportPath, destFileName, Parameter, con);
			con.close();
			Result="DayStructure";
			break;
		case 3:
			//加油站日销量交易类型分析图
			a=this.getStartDate1();
			c=a.split("-");
			Date=c[0]+c[1]+c[2].substring(0,2);;
			Parameter.put("Date", Date);
			Parameter.put("stationName", station[1]);
			Parameter.put("StationNo", station[0]);
			reportPath=ServletActionContext.getServletContext().getRealPath(File.separator 
					+"bos"+File.separator + "hosReport" + File.separator + "加油站日销量交易类型分析图.jasper");
			destFileName=ServletActionContext.getServletContext().getRealPath(File.separator 
					+"bos"+File.separator + "hosReport" + File.separator + "JYZRXLJYLXFXT.PDF");
			JasperRunManager.runReportToPdfFile(reportPath, destFileName, Parameter, con);
			con.close();
			Result="DayTransType";
			break;
		case 4:
			//加油站月油品销量分析图
			Date=this.getStartDate();
			System.out.print("Date:"+Date);
			Parameter.put("stationName", station[1]);
			Parameter.put("SignYear", Date+"%");
			Parameter.put("Year", Date);
			Parameter.put("StationNo", station[0]);
			reportPath=ServletActionContext.getServletContext().getRealPath(File.separator 
					+"bos"+File.separator + "hosReport" + File.separator + "加油站月油品销量分析图.jasper");
			destFileName=ServletActionContext.getServletContext().getRealPath(File.separator 
					+"bos"+File.separator + "hosReport" + File.separator + "JYZYYPXLFXT.PDF");
			JasperRunManager.runReportToPdfFile(reportPath, destFileName, Parameter, con);
			con.close();
			Result="MonthSell";
			break;
		case 5:
			//加油站年销售对比分析图
			Date=this.getStartDate();
			Date1=this.getEndDate();
			Parameter.put("stationName", station[1]);
			Parameter.put("SignOneDate", Date+"%");
			Parameter.put("SignTwoDate", Date1+"%");
			Parameter.put("OneYear", Date);
			Parameter.put("TwoYear", Date1);
			Parameter.put("StationNo", station[0]);
			System.out.print("Date:"+Date+"Date1:"+Date1);
			reportPath=ServletActionContext.getServletContext().getRealPath(File.separator 
					+"bos"+File.separator + "hosReport" + File.separator + "加油站年销售对比分析图.jasper");
			destFileName=ServletActionContext.getServletContext().getRealPath(File.separator 
					+"bos"+File.separator + "hosReport" + File.separator + "JYZYXSDBFXT.PDF");
			JasperRunManager.runReportToPdfFile(reportPath, destFileName, Parameter, con);
			con.close();
			Result="YearSellCompare";
			break;
		default:
			break;
		}
		return Result;
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
	public StringBuffer getVpnbuffer() {
		return vpnbuffer;
	}
	public void setVpnbuffer(StringBuffer vpnbuffer) {
		this.vpnbuffer = vpnbuffer;
	}
	public String getStartDate1() {
		return startDate1;
	}
	public void setStartDate1(String startDate1) {
		this.startDate1 = startDate1;
	}
	
	

}
