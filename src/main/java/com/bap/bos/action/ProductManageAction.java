package com.bap.bos.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bap.bos.domain.SettingSellPrice;
import com.bap.bos.service.ChangePetrolService;
import com.bap.bos.service.SettingSellPriceService;
import com.bap.bos.util.BaseAction;
import com.bap.bos.util.Page;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 *产品管理
 * 
 * @author zhulong
 * 
 */
@SuppressWarnings("serial")
@Component("productManageAction")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ProductManageAction extends BaseAction {
	
	Logger logger = LoggerFactory.getLogger(ProductManageAction.class);
	
	/*注入*/
	@Resource private SettingSellPriceService settingSellPriceService;
	@Resource private ChangePetrolService changePetrolService;
	/*分页*/
	private Page page=new Page();
	private String to_PageNum="";
	private String pageMsg;
	/*属性*/
	private String startDate;
	private String endDate;
	private String ProductType;
	private String ProductName;
	private String ProductNum;
	
	private String TankNum;
	
	private String StationNo;
	private String StaffNo;
	private String EffDate;
	private String Price;
	/*返回值*/
	private List<Object> SellPrice;
	private List<Object> ChangePetrol;
	private List<Object> SellPricePlan;
	private String Tank;
	private String Msg;
	private String error;
	/**
	 * 查询产品变价
	 * @return
	 */
	public String selSellPrice(){
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
			totalCounts=settingSellPriceService.selSellPrice(startDate, endDate, "").size();
			
		}else{
			if("-1".equals(this.getProductType())){
				totalCounts=settingSellPriceService.selSellPrice(this.getStartDate(),this.getEndDate(),"").size();
			}else if(this.getProductType()==null){
				totalCounts=settingSellPriceService.selSellPrice(this.getStartDate(),this.getEndDate(),"").size();
			}
			else{
				totalCounts=settingSellPriceService.selSellPrice(this.getStartDate(),this.getEndDate(),this.getProductType()).size();
			}
			
		}
		/*分页查询显示*/
		if(this.getProductType()==null && (this.getProductName()==null || "".equals(this.getProductName()))){
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
			SellPrice=settingSellPriceService.selSellPrice(this.getStartDate(), this.getEndDate(),"", page);
			return "success";
		}else if(this.getProductType()!=null && (this.getProductName()==null || "".equals(this.getProductName()))){
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
				SellPrice=settingSellPriceService.selSellPrice(this.getStartDate(), this.getEndDate(),"", page);
			}else{
				SellPrice=settingSellPriceService.selSellPrice(this.getStartDate(), this.getEndDate(),this.getProductType(), page);
			}
			return "success";
		}else{
			/*选择产品名称查询*/
			/*设定页数*/
			totalCounts=settingSellPriceService.selSellPriceByProductName(this.getStartDate(), this.getEndDate(),this.getProductName()).size();
			
			Integer pages=0;
			if((totalCounts%page.getPageSize())==0){
				pages=Math.round(totalCounts/page.getPageSize());
			}else{
				pages=Math.round(totalCounts/page.getPageSize())+1;
			}
			page.setPages(pages);
			SellPrice=settingSellPriceService.selSellPriceByProductName(this.getStartDate(), this.getEndDate(),this.getProductName(), page);
			return "success";
		}
	}
	/**
	 * 查询产品变价计划
	 * @return
	 */
	public String selSellPricePlan(){
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");	
		/*初始化，获得记录总数*/
		Integer totalCounts=0;//总条数
		if(this.getStartDate()==null&&this.getEndDate()==null
				&&this.getProductNum()==null){
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
			totalCounts=settingSellPriceService.selSellPricePlan(startDate, endDate, "").size();
		}else{
			if("-1".equals(this.getProductNum())){
				totalCounts=settingSellPriceService.selSellPricePlan(this.getStartDate(),this.getEndDate(),"").size();
			}else if(this.getProductType()==null){
				totalCounts=settingSellPriceService.selSellPricePlan(this.getStartDate(),this.getEndDate(),"").size();
			}else{
				totalCounts=settingSellPriceService.selSellPricePlan(this.getStartDate(),this.getEndDate(),this.getProductNum()).size();
			}
		}
		/*分页查询显示*/
		if(this.getProductNum()==null){
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
			SellPricePlan=settingSellPriceService.selSellPricePlan(this.getStartDate(), this.getEndDate(),"", page);
			return "success";
		}else{
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
			if("-1".equals(this.getProductNum())){
				SellPricePlan=settingSellPriceService.selSellPricePlan(this.getStartDate(), this.getEndDate(),"", page);
			}else{
				SellPricePlan=settingSellPriceService.selSellPricePlan(this.getStartDate(), this.getEndDate(),this.getProductNum(), page);
			}
			return "success";
		}
	}
	/**
	 * 保存产品变价计划
	 * @return
	 * @throws ParseException 
	 */
	public String saveSellPricePlan() throws ParseException{
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");	
		
		SettingSellPrice SettingSellPrice=new SettingSellPrice();
		SettingSellPrice.setSettingSellPrice_StationNo(StationNo);
		SettingSellPrice.setSettingSellPrice_ProductNum(ProductNum);
		SettingSellPrice.setSettingSellPrice_StaffNo(StaffNo);
		SettingSellPrice.setSettingSellPrice_EffDate(formatDate.parse(EffDate));
		SettingSellPrice.setSettingSellPrice_ChDate(formatDate.parse(EffDate));
		SettingSellPrice.setSettingSellPrice_SellPrice(Price == null ? 0:Float.valueOf(Price));
		SettingSellPrice.setSettingSellPrice_IsExec("1");
		SettingSellPrice.setSettingSellPrice_IsSync("1");
		SettingSellPrice.setSettingSellPrice_SyncDate(new Date());
		try {
			settingSellPriceService.saveSellPricePlan(SettingSellPrice);
			Msg="添加成功！";
			return "success";
		} catch (Exception e) {
			logger.error("保存产品变价计划异常！",e);
			error="添加失败！";
			return "input";
		}
		
		
	}
	public String delSellPricePlan() throws ParseException{
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		SettingSellPrice SettingSellPrice=new SettingSellPrice();
		SettingSellPrice.setSettingSellPrice_StationNo(StationNo);
		SettingSellPrice.setSettingSellPrice_ProductNum(ProductNum);
		SettingSellPrice.setSettingSellPrice_EffDate(formatDate.parse(EffDate));
		try {
			settingSellPriceService.delSellPricePlan(SettingSellPrice);
			Msg="删除成功！";
			return "success";
		} catch (Exception e) {
			logger.error("删除预售价格失败！",e);
			error="删除失败！";
			return "input";
		}
		
		
	}
	
	/**
	 * 油品换号查询
	 * @return
	 */
	public String selChangePetrol(){
		System.out.print("TankNum:"+TankNum);
		SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");	
		/*初始化，获得记录总数*/
		Integer totalCounts=0;//总条数
		if(this.getStartDate()==null&&this.getEndDate()==null
				&&this.getTankNum()==null){
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
			totalCounts=changePetrolService.selChangePetrol(startDate, endDate, "").size();			
		}else{
			 if("-1".equals(this.getTankNum())&&(!"".equals(this.getStartDate().trim())
						&& !"".equals(this.getEndDate().trim()))){
				totalCounts=changePetrolService.selChangePetrol(this.getStartDate(), this.getEndDate(), "").size();
			}else if("-1".equals(this.getTankNum())&&("".equals(this.getStartDate().trim())
					||"".equals(this.getEndDate().trim()))){
				totalCounts=changePetrolService.selChangePetrol("", "", "").size();
			}else{
				totalCounts=changePetrolService.selChangePetrol(this.getStartDate(),this.getEndDate(),this.getTankNum()).size();
			}
			
		}
		/*分页查询显示*/
		if((!(this.getStartDate()==null || "".equals(this.getStartDate().trim())))&&(!(this.getEndDate()==null || "".equals(this.getEndDate().trim())))
				&& (this.getTankNum()==null || "".equals(this.getTankNum())||("-1".equals(this.getTankNum())))){
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
			ChangePetrol=changePetrolService.selChangePetrol(this.getStartDate(), this.getEndDate(),"", page);
			return "success";
		}else if((this.getStartDate()==null || "".equals(this.getStartDate().trim()))||(this.getEndDate()==null || "".equals(this.getEndDate().trim()))
				&& (!(this.getTankNum()==null || "".equals(this.getTankNum())))){
			/*选择了灌号进行查找*/
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
			ChangePetrol=changePetrolService.selChangePetrol("", "",this.getTankNum(), page);	
			return "success";
		}else{
			/*选择时间、灌号查询*/
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
			ChangePetrol=changePetrolService.selChangePetrol(this.getStartDate(), this.getEndDate(),this.getTankNum(), page);
			for(int i=0;i<ChangePetrol.size();i++){
				Object[] a=(Object[]) ChangePetrol.get(i);
				System.out.println(a.length);
				System.out.println(a[0]);
				System.out.println(a[1]);
				System.out.println(a[2]);
				System.out.println(a[3]);
				System.out.println(a[4]);
			}
			
			return "success";
		}
	}
	/**
	 * 油罐加载-查询条件
	 * @return
	 */
	public String selTank(){

		ObjectMapper objectMapper = new ObjectMapper();  
		try{
			Tank = objectMapper.writeValueAsString(changePetrolService.selTank());
		}catch(Exception e){
			logger.error("油罐查询条件加载异常！",e);
		}
		return "success";
	}
	
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
	public List<Object> getSellPrice() {
		return SellPrice;
	}
	public void setSellPrice(List<Object> sellPrice) {
		SellPrice = sellPrice;
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
	public String getProductType() {
		return ProductType;
	}
	public void setProductType(String productType) {
		ProductType = productType;
	}
	public String getProductName() {
		return ProductName;
	}
	public void setProductName(String productName) {
		ProductName = productName;
	}
	public String getTankNum() {
		return TankNum;
	}
	public void setTankNum(String tankNum) {
		TankNum = tankNum;
	}

	public List<Object> getChangePetrol() {
		return ChangePetrol;
	}
	public void setChangePetrol(List<Object> changePetrol) {
		ChangePetrol = changePetrol;
	}
	public String getTank() {
		return Tank;
	}
	public void setTank(String tank) {
		Tank = tank;
	}
	public String getProductNum() {
		return ProductNum;
	}
	public void setProductNum(String productNum) {
		ProductNum = productNum;
	}
	public String getStationNo() {
		return StationNo;
	}
	public void setStationNo(String stationNo) {
		StationNo = stationNo;
	}
	public String getStaffNo() {
		return StaffNo;
	}
	public void setStaffNo(String staffNo) {
		StaffNo = staffNo;
	}
	public String getEffDate() {
		return EffDate;
	}
	public void setEffDate(String effDate) {
		EffDate = effDate;
	}
	public String getPrice() {
		return Price;
	}
	public void setPrice(String price) {
		Price = price;
	}
	public List<Object> getSellPricePlan() {
		return SellPricePlan;
	}
	public void setSellPricePlan(List<Object> sellPricePlan) {
		SellPricePlan = sellPricePlan;
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

	
}
