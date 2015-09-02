package com.bap.bos.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bap.bos.domain.Repertory;
import com.bap.bos.service.ProductService;
import com.bap.bos.service.RepertoryManageService;
import com.bap.bos.util.BaseAction;
import com.bap.bos.util.Page;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 库存管理
 * 
 * @author zhulong
 * 
 */
@SuppressWarnings("serial")
@Component("repertoryManageAction")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class RepertoryManageAction extends BaseAction {
	
	Logger logger = LoggerFactory.getLogger(RepertoryManageAction.class);
	
	
	/* 分页 */
	private Page page = new Page();
	private String to_PageNum = "";
	private String pageMsg;
	/* 注入 */
	@Resource
	ProductService productService;
	@Resource
	RepertoryManageService repertoryManageService;
	/* 属性值 */
	private String StationNo;
	private String ProductType;
	private String ProductNum;
	private String ShiftNo;
	private String TankNum;
	private String ProductVol;
	private String Unit;
	private String Temp;
	private String Density;
	private String Pressure;
	private String WaterLevel;
	private String PetrolLevel;
	private String TotalVol;
	private String Remark;
	private String AddTime;
	
	private String startDate;
	private String endDate;
	/* 返回值 */
	private String Msg;
	private String error;
	private List<Object> repertory;
	private String product;
	private String tank;

	SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 查询产品信息-名称
	 * 
	 * @return
	 */
	public String selProduct() {
			ObjectMapper objectMapper = new ObjectMapper();  
			try{
				if (null == this.getProductType()) {
					product = objectMapper.writeValueAsString(productService.selProductDetail("1"));
				}else{
					product = objectMapper.writeValueAsString(productService.selProductDetail(this.getProductType().trim()));
				}
			}catch(Exception e){
				logger.error("查询产品信息异常！",e);
			}
			return "success";
	}

	/**
	 * 查询灌信息-灌号
	 * 
	 * @return
	 */
	public String selTank() {
		ObjectMapper objectMapper = new ObjectMapper();  
		try{
			tank = objectMapper.writeValueAsString(repertoryManageService.selTank(this.getProductNum().trim()));
		}catch(Exception e){
			logger.error("查询灌信息-灌号异常！",e);
		}
		return "success";
	}

	/**
	 * 查询班别编号
	 * 
	 * @return
	 */
	public String selShiftNo() {
		ShiftNo = repertoryManageService.currentShiftNo(this.getStationNo());
		return "success";
	}

	/**
	 * 保存库存记录 
	 * @return
	 * @throws Exception
	 */
	public String saveRepertory() throws Exception {
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyyMMddHHmmss");	
		Repertory R = new Repertory();
		R.setRepertory_StationNo(this.getStationNo());
		R.setRepertory_TankNum(this.getTankNum());
		R.setRepertory_CreateTime(formatDate.format(new Date()).substring(8, 14));
		R.setRepertory_AddTime(new Date());
	//	R.setRepertory_Density(Float.valueOf(this.getDensity()));
		R.setRepertory_Temp(Float.valueOf(this.getTemp()));
		R.setRepertory_ProductVol(Float.valueOf(this.getProductVol()));
		R.setRepertory_ProductNum(this.getProductNum());
		R.setRepertory_ProductType(this.getProductType());
		R.setRepertory_ShiftNo(this.getShiftNo());
		R.setRepertory_Remark(this.getRemark());
		R.setRepertory_Unit(this.getUnit());
		R.setRepertory_IsSync("2");
		R.setRepertory_SyncDate(new Date());
		
		if ("0".equals(this.getProductType().trim())) {
			//油品添加
			R.setRepertory_PetrolLevel(Float.valueOf(this.getPetrolLevel()));
			R.setRepertory_WaterLevel(Float.valueOf(this.getWaterLevel()));
			R.setRepertory_TotalVol(Float.valueOf(this.getTotalVol()));
			try {
				repertoryManageService.saveRepertory(R);
				this.setMsg("添加成功。");
			} catch (Exception e) {
				logger.error("添加油品失败,输入内容不完整或已经添加当前班别的此类产品库存。",e);
				this.setError("添加失败,输入内容不完整或已经添加当前班别的此类产品库存。");
				e.printStackTrace();
			}
			return "success";
		} else if ("1".equals(this.getProductType().trim())) {
			//气品添加
			//R.setRepertory_Pressure(Float.valueOf(this.getPressure()));
			try {
				repertoryManageService.saveRepertory(R);
				this.setMsg("添加成功。");
			} catch (Exception e) {
				logger.error("添加油品失败,输入内容不完整或已经添加当前班别的此类产品库存。",e);
				e.printStackTrace();
				this.setError("添加失败,输入内容不完整或已经添加当前班别的此类产品库存。");
			}	
			return "success";
		} else {
			this.setError("由于网络等其他原因，添加失败。");
			return "input";
		}
	}
	
	/**
	 * 查询库存记录
	 * @return
	 */
	public String selRepertory(){
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");	
		/*初始化，获得记录总数*/
		Integer totalCounts=0;//总条数
		if(this.getStartDate()==null&&this.getEndDate()==null
				&&this.getProductType()==null){
			/*第一次初始化*/
			String currDate=formatDate.format(new Date());
			String curr[]=currDate.split("-");
			startDate=curr[0]+"-"+curr[1]+"-"+"01";
			if("01"==curr[2]){
				//每月第一天录入时
				 endDate=curr[0]+"-"+curr[1]+"-"+"01";
			}else{
				 endDate=curr[0]+"-"+curr[1]+"-"+(Integer.valueOf(curr[2]));
			}
			totalCounts=repertoryManageService.selRepertory(startDate, endDate, "").size();
			
		}else{
			if("-1".equals(this.getProductType())){
				totalCounts=repertoryManageService.selRepertory(this.getStartDate(),this.getEndDate(),"").size();
			}else if(this.getProductType()==null){
				totalCounts=repertoryManageService.selRepertory(this.getStartDate(),this.getEndDate(),"").size();
			}
			else{
				totalCounts=repertoryManageService.selRepertory(this.getStartDate(),this.getEndDate(),this.getProductType()).size();
			}
			
		}
		/*分页查询显示*/
		if(this.getProductType()==null && (this.getShiftNo()==null || "".equals(this.getShiftNo()))){
			/*设定页数*/
			Integer pages=0;
			if((totalCounts%page.getPageSize())==0){
				pages=Math.round(totalCounts/page.getPageSize());
			}else{
				pages=Math.round(totalCounts/page.getPageSize())+1;
			}
			page.setPages(pages);
			/*跳转第几页*/
			if(!(this.getTo_PageNum().trim()=="")){
				page.setPageNum(Integer.valueOf(this.getTo_PageNum().trim()));
			}
			/*分页查询*/
			repertory=repertoryManageService.selRepertory(this.getStartDate(), this.getEndDate(),"", page);
			return "success";
		}else if(this.getProductType()!=null && (this.getShiftNo()==null || "".equals(this.getShiftNo()))){
			/*选择了类型和时间进行查找*/
			/*设定页数*/
			Integer pages=0;
			if((totalCounts%page.getPageSize())==0){
				pages=Math.round(totalCounts/page.getPageSize());
			}else{
				pages=Math.round(totalCounts/page.getPageSize())+1;
			}
			page.setPages(pages);
			/*跳转第几页*/
			if(!(this.getTo_PageNum().trim()=="")){
				page.setPageNum(Integer.valueOf(this.getTo_PageNum().trim()));
			}
			/*分页查询*/
			if("-1".equals(this.getProductType())){
				repertory=repertoryManageService.selRepertory(this.getStartDate(), this.getEndDate(),"", page);
			}else{
				repertory=repertoryManageService.selRepertory(this.getStartDate(), this.getEndDate(),this.getProductType(), page);
			}
			return "success";
		}else{
			/*选择存在班别编号*/
			page.setPages(1);
			repertory=repertoryManageService.selRepertory(this.getShiftNo(),page);
			return "success";
		}
	
	}
	/**
	 * 库存签核
	 * @return
	 */
	public String signRepertory(){
		Repertory R = new Repertory();
		R.setRepertory_StationNo(this.getStationNo());
		R.setRepertory_TankNum(this.getTankNum());
		R.setRepertory_ProductType(this.getProductType());
		R.setRepertory_ShiftNo(this.getShiftNo());
		if(repertoryManageService.signRepertory(R)){
			this.setMsg("签核成功。");
		}else{
			this.setMsg("签核失败。");
		}
		return "success";
	}
	/**
	 * 库存修改
	 * @return
	 * @throws Exception 
	 */
	public String updateRepertory() throws Exception{
		Repertory R = new Repertory();
		R.setRepertory_StationNo(this.getStationNo());
		R.setRepertory_TankNum(this.getTankNum());
		R.setRepertory_AddTime(formatDate.parse(this.getAddTime()));
//		R.setRepertory_Density(Float.valueOf(this.getDensity()));
		R.setRepertory_Temp(Float.valueOf(this.getTemp()));
		R.setRepertory_ProductVol(Float.valueOf(this.getProductVol()));
		R.setRepertory_ProductNum(this.getProductNum());
		R.setRepertory_ProductType(this.getProductType());
		R.setRepertory_ShiftNo(this.getShiftNo());
		R.setRepertory_Remark(this.getRemark());
		R.setRepertory_Unit(this.getUnit());
		R.setRepertory_IsSync("2");
		R.setRepertory_SyncDate(new Date());

		if ("0".equals(this.getProductType().trim())) {
			//油品库存修改
			R.setRepertory_PetrolLevel(Float.valueOf(this.getPetrolLevel()));
			R.setRepertory_WaterLevel(Float.valueOf(this.getWaterLevel()));
			R.setRepertory_TotalVol(Float.valueOf(this.getTotalVol()));
			
			if(repertoryManageService.updateRepertory(R)){
				this.setMsg("修改成功。");
				return "success1";
			}else{
				this.setError("修改失败,输入内容不完整。");
				return "input1";
			}	
			
		} else if ("1".equals(this.getProductType().trim())) {
			//气品库存修改
	//		R.setRepertory_Pressure(Float.valueOf(this.getPressure()));
			if(repertoryManageService.updateRepertory(R)){
				this.setMsg("修改成功。");
				return "success2";
			}else{
				this.setError("修改失败,输入内容不完整。");
				return "input2";
			}
			
		} else {
			this.setError("由于网络等其他原因，添加失败。");
			return "input";
		}
	}
	
	
	/**
	 * 删除库存记录
	 * @return
	 */
	public String delRepertory(){
		try{
			Repertory R = new Repertory();
			R.setRepertory_StationNo(this.getStationNo());
			R.setRepertory_TankNum(this.getTankNum());
			R.setRepertory_ProductType(this.getProductType());
			R.setRepertory_ShiftNo(this.getShiftNo());
			R.setRepertory_AddTime(formatDate.parse(this.getAddTime()));
			String jieguo=repertoryManageService.delRepertory(R);
			if("success".equals(jieguo)){
				this.setMsg("删除库存信息成功。");
			}else if("UnDel".equals(jieguo)){
				this.setError("当前库存信息已经同步到总部服务器，不能进行删除。");
			}else{
				this.setError("由于网络等其他原因，库存信息删除失败。");
			}	
			return "success";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			this.setError("删除库存信息失败。");
			return "input";
		}	
	}
	
	/* set get 方法开始 */
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getTo_PageNum() {
		return to_PageNum;
	}

	public void setTo_PageNum(String toPageNum) {
		to_PageNum = toPageNum;
	}

	public String getPageMsg() {
		return pageMsg;
	}

	public void setPageMsg(String pageMsg) {
		this.pageMsg = pageMsg;
	}

	public RepertoryManageService getRepertoryManageService() {
		return repertoryManageService;
	}

	public void setRepertoryManageService(
			RepertoryManageService repertoryManageService) {
		this.repertoryManageService = repertoryManageService;
	}

	public String getStationNo() {
		return StationNo;
	}

	public void setStationNo(String stationNo) {
		StationNo = stationNo;
	}

	public String getMsg() {
		return Msg;
	}

	public void setMsg(String msg) {
		Msg = msg;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public List<Object> getRepertory() {
		return repertory;
	}

	public void setRepertory(List<Object> repertory) {
		this.repertory = repertory;
	}

	public String getProductType() {
		return ProductType;
	}

	public void setProductType(String productType) {
		ProductType = productType;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getTank() {
		return tank;
	}

	public void setTank(String tank) {
		this.tank = tank;
	}

	public String getProductNum() {
		return ProductNum;
	}

	public void setProductNum(String productNum) {
		ProductNum = productNum;
	}

	public String getShiftNo() {
		return ShiftNo;
	}

	public void setShiftNo(String shiftNo) {
		ShiftNo = shiftNo;
	}

	public String getTankNum() {
		return TankNum;
	}

	public void setTankNum(String tankNum) {
		TankNum = tankNum;
	}

	public String getProductVol() {
		return ProductVol;
	}

	public void setProductVol(String productVol) {
		ProductVol = productVol;
	}

	public String getUnit() {
		return Unit;
	}

	public void setUnit(String unit) {
		Unit = unit;
	}

	public String getTemp() {
		return Temp;
	}

	public void setTemp(String temp) {
		Temp = temp;
	}

	public String getDensity() {
		return Density;
	}

	public void setDensity(String density) {
		Density = density;
	}

	public String getPressure() {
		return Pressure;
	}

	public void setPressure(String pressure) {
		Pressure = pressure;
	}

	public String getWaterLevel() {
		return WaterLevel;
	}

	public void setWaterLevel(String waterLevel) {
		WaterLevel = waterLevel;
	}

	public String getPetrolLevel() {
		return PetrolLevel;
	}

	public void setPetrolLevel(String petrolLevel) {
		PetrolLevel = petrolLevel;
	}

	public String getTotalVol() {
		return TotalVol;
	}

	public void setTotalVol(String totalVol) {
		TotalVol = totalVol;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
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

	public String getAddTime() {
		return AddTime;
	}

	public void setAddTime(String addTime) {
		AddTime = addTime;
	}

}
