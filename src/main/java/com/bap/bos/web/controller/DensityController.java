package com.bap.bos.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bap.bos.domain.Density;
import com.bap.bos.domain.Product;
import com.bap.bos.service.DensityService;
import com.bap.bos.service.ProductService;
import com.bap.bos.util.Page;

/**
 * 密度管理控制类
 * 
 * @author edgar_chan lineshow7@gmail.com
 * @since 2015年4月7日
 */
@Controller
@RequestMapping("density/*")
public class DensityController{

	Logger logger = LoggerFactory.getLogger(DensityController.class);

	@Resource
	private DensityService densityService;
	
	@Resource
	private ProductService productService;

    @Autowired  
    private  HttpServletRequest request;
	
	/**
	 * 转向密度管理页面
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, value = "tomanage")
	public ModelAndView toDensityManage(ModelAndView mv){
		List<Product> productList = productService.selProductDetail();
		mv.addObject("productList", productList);
		mv.setViewName("density/manage");
		return mv;
	}

	/**
	 * 查询 密度表
	 * @return
	 * @author edgar_chan
	 * @since 2015年4月30日
	 */
	@RequestMapping(method = RequestMethod.POST, value = "getlist")
	public String getDensityList(){
		
		String productId = request.getParameter("productId");
		String yearMonth = request.getParameter("yearMonth");
		String getTotalNum = request.getParameter("getTotalNum");
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		
		Page page = new Page();
		page.setPageNum(pageNo);
		page.setPageSize(pageSize);
		
		Map<String,Object> params = new HashMap<String, Object>();
		
		if(!"-1".equals(productId))
			params.put("productId", productId);
		if(StringUtils.isNotBlank(yearMonth))
			params.put("yearMonth", yearMonth);
		
		if("true".equals(getTotalNum)){
			int totalNum = densityService.getTotalRowNum(params);
			request.setAttribute("totalNum", totalNum);
			int totalPages = totalNum/15;
			totalPages += totalNum%15 > 0 ? 1 : 0;
			
			request.setAttribute("totalPages", totalPages);
			request.setAttribute("totalNum", totalNum);
			
			if(totalNum == 0){ //没有数据直接返回
				request.setAttribute("cellList", new ArrayList<Density>());
				return "density/densityList";
			}
			
		}
		
		List<Density> cellList = densityService.get(params, page);
		
		request.setAttribute("cellList", cellList);
		
		return "density/densityList";
	}
	
	/**
	 * 更新密度
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, value = "update")
	public @ResponseBody String update(Density density){
		return densityService.updateDensity(density);
	}
	

}
