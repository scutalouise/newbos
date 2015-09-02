package com.bap.bos.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bap.bos.domain.MonthDateAera;
import com.bap.bos.service.MonthCheckingService;
import com.bap.bos.web.pojo.MonthChecking;
import com.bap.bos.web.pojo.MonthCheckingDetail;
import com.bap.components.BapJasperReportsMultiFormatView;
import com.bap.utils.DateTimeAddition;

/**
 * 月盘点控制类
 * 
 * @author edgar_chan lineshow7@gmail.com
 * @since 2015年4月7日
 */
@Controller
@RequestMapping("monthchecking/*")
public class MonthCheckingController {

	Logger logger = LoggerFactory.getLogger(MonthCheckingController.class);

	@Resource
	private MonthCheckingService monthCheckingService;

	@RequestMapping(method = RequestMethod.GET, value = "report/{suffix}")
	public ModelAndView monthCheckingReport(HttpServletRequest request,
			@PathVariable String suffix) {
		logger.debug("--------------generate variable file report----------");

		String checkingMonth = request.getParameter("TransItem_ShiftDate");
		String stationName = request.getParameter("StationName");
		String type = request.getParameter("Type");
		
		DateTime[] dateSpan = new DateTime[2];
		
		if(StringUtils.isBlank(checkingMonth)){
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			checkingMonth = request.getParameter("selectMonth");
			
			DateTime startDateTime = DateTime.parse(startDate,DateTimeFormat.forPattern(DateTimeAddition.FORMAT_DATE_2)); 
			DateTime endDateTime = DateTime.parse(endDate,DateTimeFormat.forPattern(DateTimeAddition.FORMAT_DATE_2)); 
			dateSpan[0] = startDateTime;
			dateSpan[1] = endDateTime;
		}

		switch (type) {
			case "1":
				return preareForMonthChecking(stationName, checkingMonth, suffix,dateSpan);
			case "3":
				return preareForMonthCheckingDetail(stationName, checkingMonth,
						suffix,dateSpan);
			default:
		}
		return preareForMonthChecking(stationName, checkingMonth, suffix,dateSpan);

	}

	/**
	 * 月盘点
	 * 
	 * @param stationName
	 * @param checkingMonth
	 * @param suffix
	 * @return
	 * @author edgar_chan
	 * @since 2015年5月8日
	 */
	private ModelAndView preareForMonthChecking(String stationName,
			String checkingMonth, String suffix,DateTime[] dateSpan) {
		String checkingMonthYM = checkingMonth.replace("-", "");

		List<MonthChecking> monthCheckingList = new ArrayList<MonthChecking>();
		if(dateSpan != null && (dateSpan[0] != null && dateSpan[1] != null)){
			monthCheckingList = monthCheckingService.integrateMonthCheckingData(dateSpan);
		}else{
			monthCheckingList = monthCheckingService.integrateMonthCheckingData(checkingMonthYM);
		}

		Map<String, Object> parameterMap = new HashMap<String, Object>();

		JRDataSource JRdataSource = new JRBeanCollectionDataSource(
				monthCheckingList);
		parameterMap.put("datasource", JRdataSource);
		parameterMap.put("yearMonth", checkingMonth.replace("-", "年") + "月");
		parameterMap.put("stationName", stationName);

		if (StringUtils.indexOf(suffix, '_') > 0) {
			String[] suffix_arr = StringUtils.split(suffix, '_');
			suffix = suffix_arr[0];
			parameterMap.put(BapJasperReportsMultiFormatView.DOWNLOAD_OR_NOT,
					suffix_arr[1]);
		}
		parameterMap.put("format", suffix);

		parameterMap.put(
				BapJasperReportsMultiFormatView.ATTACHEMT_FILE_NAME_KEY,
				"monthchecking_report_" + checkingMonth);

		return new ModelAndView("monthchecking-report", parameterMap);
	}

	/**
	 * 月盘点 销量细化
	 * 
	 * @param stationName
	 * @param checkingMonth
	 * @param suffix
	 * @return
	 * @author edgar_chan
	 * @since 2015年5月8日
	 */
	private ModelAndView preareForMonthCheckingDetail(String stationName,
			String checkingMonth, String suffix,DateTime[] dateSpan) {
		String checkingMonthYM = checkingMonth.replace("-", "");


		List<MonthCheckingDetail> monthCheckingDetailList = new ArrayList<MonthCheckingDetail>();
		if(dateSpan != null && (dateSpan[0] != null && dateSpan[1] != null)){
			monthCheckingDetailList = monthCheckingService.integrateMonthCheckingDetailData(dateSpan);
		}else{
			monthCheckingDetailList = monthCheckingService.integrateMonthCheckingDetailData(checkingMonthYM);
		}

		Map<String, Object> parameterMap = new HashMap<String, Object>();

		JRDataSource JRdataSource = new JRBeanCollectionDataSource(
				monthCheckingDetailList);
		parameterMap.put("datasource", JRdataSource);
		parameterMap.put("YearMonth", checkingMonth.replace("-", "年") + "月");
		parameterMap.put("stationName", stationName);

		if (StringUtils.indexOf(suffix, '_') > 0) {
			String[] suffix_arr = StringUtils.split(suffix, '_');
			suffix = suffix_arr[0];
			parameterMap.put(BapJasperReportsMultiFormatView.DOWNLOAD_OR_NOT,
					suffix_arr[1]);
		}
		parameterMap.put("format", suffix);

		parameterMap.put(
				BapJasperReportsMultiFormatView.ATTACHEMT_FILE_NAME_KEY,
				"monthcheckingdetail_report_" + checkingMonth);

		return new ModelAndView("monthcheckingdetail-report", parameterMap);
	}

	/**
	 * 获取月周期
	 * @return
	 * @author edgar_chan
	 * @since 2015年5月8日
	 */
	@RequestMapping(method = RequestMethod.GET, value = "fetch/montharea")
	public @ResponseBody MonthDateAera getMonthArea(){
		return monthCheckingService.getMonthAreaSpan();
	}
	

}
