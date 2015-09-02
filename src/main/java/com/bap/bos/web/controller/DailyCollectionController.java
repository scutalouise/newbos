package com.bap.bos.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bap.bos.service.DailyCollectionService;
import com.bap.bos.web.pojo.DailyCollection;
import com.bap.components.BapJasperReportsMultiFormatView;

/**
 * 日结控制类
 * 
 * @author edgar_chan lineshow7@gmail.com
 * @since 2015年4月7日
 */
@Controller
@RequestMapping("dailycollection/*")
public class DailyCollectionController{

	Logger logger = LoggerFactory.getLogger(DailyCollectionController.class);

	@Resource
	private DailyCollectionService dailyCollectionService;
	
	
	@RequestMapping(method = RequestMethod.GET, value = "report/{suffix}")
	public ModelAndView dailyCollectionReport(HttpServletRequest request,@PathVariable String suffix) {
		logger.debug("--------------generate variable file report----------");

		String transItem_ShiftDate = request.getParameter("transItem_ShiftDate");
		String shiftNo = request.getParameter("shiftNo");
		
		String[] ymds = StringUtils.split(transItem_ShiftDate,"-");
		String ymd = ymds[0]+ymds[1]+ymds[2];
		
		String[] shiftNoArr = StringUtils.split(shiftNo,"-");
		shiftNo = shiftNoArr[0];
		
		List<DailyCollection>  dailyCollectionList = dailyCollectionService.getDataForShow(ymd, shiftNo);
		
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		
		JRDataSource JRdataSource = new JRBeanCollectionDataSource(dailyCollectionList);
		parameterMap.put("datasource", JRdataSource);
		parameterMap.put("ymd", ymd.substring(0,4)+"年"+ymd.substring(4,6)+"月"+ymd.substring(6)+"日");
		parameterMap.put("shiftNo", shiftNo);
		
		if(StringUtils.indexOf(suffix, '_') > 0){
			String[] suffix_arr = StringUtils.split(suffix,'_');
			suffix = suffix_arr[0];
			parameterMap.put(BapJasperReportsMultiFormatView.DOWNLOAD_OR_NOT,suffix_arr[1]);
		}
		parameterMap.put("format", suffix);

		parameterMap.put(
				BapJasperReportsMultiFormatView.ATTACHEMT_FILE_NAME_KEY, "daily_report_"+transItem_ShiftDate+"_"+shiftNo);

		return new ModelAndView("dailyCollection-report",parameterMap);
	}

}
