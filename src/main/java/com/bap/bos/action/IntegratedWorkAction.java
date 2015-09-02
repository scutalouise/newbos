package com.bap.bos.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bap.bos.domain.PositionType;
import com.bap.bos.domain.Product;
import com.bap.bos.domain.ProductSupplier;
import com.bap.bos.domain.ReferredCubage;
import com.bap.bos.domain.Staff;
import com.bap.bos.domain.Station;
import com.bap.bos.domain.Tanksetting;
import com.bap.bos.service.ChangePetrolService;
import com.bap.bos.service.ProductService;
import com.bap.bos.service.ProductSupplierService;
import com.bap.bos.service.ReferredCubageService;
import com.bap.bos.service.StaffService;
import com.bap.bos.service.StationService;
import com.bap.bos.util.BaseAction;
import com.bap.bos.util.Page;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 综合业务管理
 * 
 * @author zhulong
 * 
 */
@SuppressWarnings("serial")
@Component("integratedWorkAction")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class IntegratedWorkAction extends BaseAction {
	
	Logger logger = LoggerFactory.getLogger(IntegratedWorkAction.class);
	
	@Resource private StaffService staffService;
	
	@Resource private ProductService productService;

	@Resource private StationService stationService;
	
	@Resource private ProductSupplierService productSupplierService;
	
	@Resource private ChangePetrolService changePetrolService;
	
	@Resource private ReferredCubageService referredCubageService;
	
	
	/*属性*/
	private String selType;
	private String Staff_Name;
	private String Staff_No;
	private String Staff_ID;
	private String Statue;
	private String word;
	
	private String Num;
	private String Name;
	private String Tel;
	private String Cell;
	private String Fax;
	private String Address;
	private String Remark;
	private String SupplierKey;
	/*分页*/
	private Page page=new Page();
	private String to_PageNum="";
	private String pageMsg;
	/*返回值*/
	private List<PositionType> Position;
	private List<Product> Product;
	private List<Object> Staff;
	private List<ProductSupplier> Supplier;
	private Station Station;
	private String autoComplete;
	private String Msg;
	private String error;
	private String ProductSupplier;
	
	/**
	 * 查询产品信息
	 * @return
	 */
	public String selProduct(){
		Product=productService.selProductDetail();
		//System.out.print(Product.get(0).getProduct_Name());
		return "success";
	}
	/**
	 * 查询员工信息
	 * @return
	 */
	public String selStaff(){
		/*设定页数大小*/
		if(null==this.getSelType()){
			/*设定页数*/
			Integer pages=0;
			if((staffService.selAllStaffsDetail().size()%page.getPageSize())==0){
				pages=Math.round(staffService.selAllStaffsDetail().size()/page.getPageSize());
			}else{
				pages=Math.round(staffService.selAllStaffsDetail().size()/page.getPageSize())+1;
			}
			page.setPages(pages);
			/*跳转第几页*/
			if(!(this.getTo_PageNum().trim()=="")){
				page.setPageNum(Integer.valueOf(this.getTo_PageNum().trim()));
			}
			/*查询数据*/
			Staff=staffService.selAllStaffsDetail(page);
		}else{
			int SelType = Integer.valueOf(this.getSelType()) ;
			Integer pages=1;
			
			switch (SelType) {
			case -1:
				/*设定页数*/
				pages=0;
				if((staffService.selAllStaffsDetail().size()%page.getPageSize())==0){
					pages=Math.round(staffService.selAllStaffsDetail().size()/page.getPageSize());
				}else{
					pages=Math.round(staffService.selAllStaffsDetail().size()/page.getPageSize())+1;
				}
				
				page.setPages(pages);
				/*跳转第几页*/
				if(!(this.getTo_PageNum().trim()=="")){
					page.setPageNum(Integer.valueOf(this.getTo_PageNum().trim()));
				}
				/*查询数据*/
				Staff=staffService.selAllStaffsDetail(page);
				break;
			case 1:
				/*姓名*/
				Staff=staffService.selStaffName(this.getStaff_Name(),page);
				page.setPages(1);
				break;
			case 2:
				/*员工卡号*/
				Staff=staffService.selStaffNo(this.getStaff_No(),page);
				page.setPages(1);
				break;
			case 3:
				/*身份证号*/
				Staff=staffService.selStaffID(this.getStaff_ID(),page);
				page.setPages(1);
				break;
			case 4:
				/*状态*/
				/*设定页数*/
				pages=0;
				if((staffService.selStaffStatus(this.getStatue()).size()%page.getPageSize())==0){
					pages=Math.round(staffService.selStaffStatus(this.getStatue()).size()/page.getPageSize());
				}else{
					pages=Math.round(staffService.selStaffStatus(this.getStatue()).size()/page.getPageSize())+1;
				}
				page.setPages(pages);
				/*跳转第几页*/
				if(!(this.getTo_PageNum().trim()=="")){
					page.setPageNum(Integer.valueOf(this.getTo_PageNum().trim()));
				}
				/*查询数据*/
				Staff=staffService.selStaffStatus(this.getStatue(), page);
				//ActionContext.getContext().put(key, value);
				break;
			default:
				break;
			}
		}
		
		return "success";
	}
	
	
	/**
	 * 转向容积管理页面
	 * @return
	 */
	public String toTankCubageManage(){
		
		List<Tanksetting> tankList = changePetrolService.selTank();
		
		ServletActionContext.getRequest().setAttribute("tankList", tankList);
		
		return "success";
	}

	/**
	 * 查询 容积表
	 * @return
	 * @author edgar_chan
	 * @since 2015年4月3日
	 */
	
	public String getTankCubageList(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		
		String tanknum = request.getParameter("tanknum");
		String minHeight = request.getParameter("minHeight");
		String maxHeight = request.getParameter("maxHeight");
		String getTotalNum = request.getParameter("getTotalNum");
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		
		Page page = new Page();
		page.setPageNum(pageNo);
		page.setPageSize(pageSize);
		
		Map<String,Object> params = new HashMap<String, Object>();
		
		if(!"-1".equals(tanknum))
			params.put("tanknum", tanknum);
		if(StringUtils.isNotBlank(minHeight))
			params.put("minHeight", minHeight);
		if(StringUtils.isNotBlank(maxHeight))
			params.put("maxHeight", maxHeight);
		
		if("true".equals(getTotalNum)){
			int totalNum = referredCubageService.getTotalRowNum(params);
			ServletActionContext.getRequest().setAttribute("totalNum", totalNum);
			int totalPages = totalNum/15;
			totalPages += totalNum%15 > 0 ? 1 : 0;
			
			ServletActionContext.getRequest().setAttribute("totalPages", totalPages);
			ServletActionContext.getRequest().setAttribute("totalNum", totalNum);
			
			if(totalNum == 0){ //没有数据直接返回
				ServletActionContext.getRequest().setAttribute("referredCubageList", new ArrayList<ReferredCubage>());
				return "success";
			}
			
		}
		
		List<ReferredCubage> referredCubageList = referredCubageService.get(params, page);
		
		ServletActionContext.getRequest().setAttribute("referredCubageList", referredCubageList);
		
		return "success";
	}
	
	/**
	 * 新增或删除一条容积信息
	 * 
	 * @author edgar_chan
	 * @since 2015年4月3日
	 */
	public void addOrDelTankCubage(){
		
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
		
		Staff loginStaff = (Staff)request.getSession().getAttribute("LoginStaff");
		Station station = (Station)request.getSession().getAttribute("Station");
		
		
		
		if(loginStaff == null){
			try{
				
				PrintWriter writer = response.getWriter();
				writer.write("登录失效，请重新登录后再次操作！");
			}catch(Exception e){
				logger.error("回执信息失败！",e);
			}
			return ;
		}
		
		String delId = request.getParameter("exeId");
		String tankNum = request.getParameter("n_tankNum");
		String height = request.getParameter("n_tHeight");
		String cubage = request.getParameter("n_tCubage");
		
		if(StringUtils.isNotBlank(delId)){
			referredCubageService.fakeDelete(Long.valueOf(delId));
			try{
				PrintWriter writer = response.getWriter();
				writer.write("删除成功！");
			}catch(Exception e){
				logger.error("删除容积操作回执信息失败！",e);
			}
		}else{
			
			if(!NumberUtils.isNumber(height) || !NumberUtils.isNumber(cubage)){
				try{
					PrintWriter writer = response.getWriter();
					writer.write("输入数据格式有误，请检查！");
				}catch(Exception e){
					logger.error("保存容积操作回执信息失败！",e);
				}
				return ;
			}
			
			ReferredCubage referredCubage = new ReferredCubage();
			referredCubage.setTankSetting(new Tanksetting(station.getStation_No(),tankNum));
			referredCubage.setHeight(Integer.valueOf(height));
			referredCubage.setCubage(Double.valueOf(cubage));
			referredCubage.setCreatedBy(loginStaff);
			referredCubage.setCreatedTime(new Date());
			referredCubage.setEnable(true);
			
			try{
				String addResult = referredCubageService.add(referredCubage);
				
				PrintWriter writer = response.getWriter();
				
				if("unreasonableCubage".equals(addResult))
						writer.write("系统检测到您所设置的容积数值有误，请检查！");
					else
						writer.write("添加成功！");
			}catch(Exception e){
				try{
					PrintWriter writer = response.getWriter();
					writer.write("添加容积异常！");
					logger.error("添加容积失败！",e);
				}catch(Exception e1){
					logger.error("添加容积操作回执信息失败！",e);
				}
			}
		}
	}
	
	/**
	 * 根据高度获取容积
	 * 
	 * @author edgar_chan 
	 * @since 2015-4-6
	 */
	public void getTankCubageByHeight() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();

		String wHeight = request.getParameter("wHeight");
		wHeight = StringUtils.trim(wHeight);
		String oHeight = request.getParameter("oHeight");
		oHeight = StringUtils.trim(oHeight);
		String tankNum = request.getParameter("tankNum");
		tankNum = StringUtils.trim(tankNum);
		String ask = request.getParameter("ask");

		Double tcubage = 0.0;
		Double ocubage = 0.0;

		switch (ask) {
		case "t": //输入水高触发查询
			tcubage = referredCubageService.getCubage(tankNum,
					Integer.valueOf(wHeight));
			break;
		case "ot"://输入油高且水高有值触发查询
			Integer wHeight_int = Integer.valueOf(wHeight);
			Integer oHeight_int = Integer.valueOf(oHeight);
			Integer tHeight_int = wHeight_int + oHeight_int;

			tcubage = referredCubageService.getCubage(tankNum, tHeight_int);
			Double wcubage = 0.0;
			if(wHeight_int != 0)
				wcubage = referredCubageService.getCubage(tankNum,wHeight_int);
			ocubage = tcubage - wcubage;
			break;
		}

		try {
			PrintWriter writer = response.getWriter();
			ObjectMapper objectMapper = new ObjectMapper();
			Map<String, Object> valueMap = new HashMap<String, Object>();
			valueMap.put("tcubage", tcubage);
			valueMap.put("ocubage", ocubage);
			valueMap.put("flag", "success");
			objectMapper.writeValue(writer, valueMap);
		} catch (Exception e) {
			logger.error("容积回执失败！", e);
		}
	}
	
	
	/**
	 * 员工搜索信息自动补全
	 * @return
	 */
	public String staffSelAutoComplete(){
		int SelType = Integer.valueOf(this.getSelType()) ;
		
		Object jsonConvertSrc = null;
		
		switch (SelType) {
		case 1:
			/*姓名*/
			jsonConvertSrc=staffService.selStaffName(this.getWord());
			break;
		case 2:
			/*员工卡号*/
			jsonConvertSrc=staffService.selStaffNo(this.getWord());
			break;
		case 3:
			/*身份证号*/
			jsonConvertSrc=staffService.selStaffID(this.getWord());
			break;
		default:
			break;
		}
		
		ObjectMapper objectMapper = new ObjectMapper();  
		try{
			autoComplete = objectMapper.writeValueAsString(jsonConvertSrc);
		}catch(Exception e){
			logger.error("员工搜索信息自动补全异常！",e);
		}
		
		return "success";
	}
	
	/**
	 * 查询职位信息
	 * @return
	 */
	public String selPosition(){
		Position=staffService.selPositionType();
		return "success";
	}
	/**
	 * 查询油站信息
	 * @return
	 */
	public String selStation(){
		Station=stationService.selStationDetail().get(0);
		return "success";
	}
	/**
	 * 保存供应商信息
	 * @return
	 */
	public String saveProductSupplier(){
		ProductSupplier o=new ProductSupplier();
		o.setProductSupplier_Name(StringUtils.trim(this.getName()));
		o.setProductSupplier_Tel(this.getTel());
		o.setProductSupplier_Cell(this.getCell());
		o.setProductSupplier_Fax(this.getFax());
		o.setProductSupplier_Address(this.getAddress());
		o.setProductSupplier_Remark(this.getRemark());
		try {
			String rsFlag = productSupplierService.saveObject(o);
			if("repeatName".equals(rsFlag)){
				this.setError("系统存在相同供应商名称！");
				return "input";
			}
			this.setMsg("保存供应商信息成功！");
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			this.setError("保存供应商信息失败，请检查供应商编号是否唯一或网络是否正常。");
			return "input";
		}
	}
	/**
	 * 修改供应商信息
	 * @return
	 */
	public String updateProductSupplier(){
		ProductSupplier o=new ProductSupplier();
		o.setProductSupplier_key(Integer.valueOf(this.getSupplierKey()));
		o.setProductSupplier_Name(StringUtils.trim(this.getName()));
		o.setProductSupplier_Tel(this.getTel());
		o.setProductSupplier_Cell(this.getCell());
		o.setProductSupplier_Fax(this.getFax());
		o.setProductSupplier_Address(this.getAddress());
		o.setProductSupplier_Remark(this.getRemark());
		try {
			String rsFlag = productSupplierService.updateObject(o);
			if("repeatName".equals(rsFlag)){
				this.setError("系统存在相同供应商名称！");
				return "input";
			}
			this.setMsg("更新供应商信息成功！");
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			this.setError("更新供应商信息失败，请检查网络是否正常。");
			return "input";
		}
	}
	/**
	 * 查询供应商
	 * @return
	 */
	public String  selProductSupplier(){
		Integer totalCounts=0;//总条数
		if(Name==null||"".equals(Name.trim())){
			totalCounts=productSupplierService.selectObject("").size();
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
			Supplier=productSupplierService.selectObject("", page);
			return "success";
		}else{
			totalCounts=productSupplierService.selectObject(Name).size();
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
			Supplier=productSupplierService.selectObject(Name, page);
			return "success";
		}
	}
	public String ProductSupplierJSON(){
		ObjectMapper objectMapper = new ObjectMapper();  
		try{
			ProductSupplier = objectMapper.writeValueAsString(productSupplierService.selectObject(""));
		}catch(Exception e){
			logger.error("查询油品异常！",e);
		}
		
		System.out.print(ProductSupplier);
		return "success";
	}
	
	public List<PositionType> getPosition() {
		return Position;
	}
	public void setPosition(List<PositionType> position) {
		Position = position;
	}
	public List<Product> getProduct() {
		return Product;
	}
	public void setProduct(List<Product> product) {
		Product = product;
	}
	
	public Station getStation() {
		return Station;
	}
	public void setStation(Station station) {
		Station = station;
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
	public List<Object> getStaff() {
		return Staff;
	}
	public void setStaff(List<Object> staff) {
		Staff = staff;
	}
	public String getStaff_Name() {
		return Staff_Name;
	}
	public void setStaff_Name(String staffName) {
		Staff_Name = staffName;
	}
	public String getStaff_No() {
		return Staff_No;
	}
	public void setStaff_No(String staffNo) {
		Staff_No = staffNo;
	}
	public String getStaff_ID() {
		return Staff_ID;
	}
	public void setStaff_ID(String staffID) {
		Staff_ID = staffID;
	}
	public String getStatue() {
		return Statue;
	}
	public void setStatue(String statue) {
		Statue = statue;
	}
	public String getSelType() {
		return selType;
	}
	public void setSelType(String selType) {
		this.selType = selType;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getAutoComplete() {
		return autoComplete;
	}
	public void setAutoComplete(String autoComplete) {
		this.autoComplete = autoComplete;
	}
	public ProductSupplierService getProductSupplierService() {
		return productSupplierService;
	}
	public void setProductSupplierService(
			ProductSupplierService productSupplierService) {
		this.productSupplierService = productSupplierService;
	}
	public String getNum() {
		return Num;
	}
	public void setNum(String num) {
		Num = num;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getTel() {
		return Tel;
	}
	public void setTel(String tel) {
		Tel = tel;
	}
	public String getCell() {
		return Cell;
	}
	public void setCell(String cell) {
		Cell = cell;
	}
	public String getFax() {
		return Fax;
	}
	public void setFax(String fax) {
		Fax = fax;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
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
	public List<ProductSupplier> getSupplier() {
		return Supplier;
	}
	public void setSupplier(List<ProductSupplier> supplier) {
		Supplier = supplier;
	}
	public String getSupplierKey() {
		return SupplierKey;
	}
	public void setSupplierKey(String supplierKey) {
		SupplierKey = supplierKey;
	}
	public String getProductSupplier() {
		return ProductSupplier;
	}
	public void setProductSupplier(String productSupplier) {
		ProductSupplier = productSupplier;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
