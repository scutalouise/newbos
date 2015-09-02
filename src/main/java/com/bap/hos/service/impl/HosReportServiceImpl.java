package com.bap.hos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bap.bos.domain.Shift;
import com.bap.bos.domain.Station;
import com.bap.hos.dao.HosReportDao;
import com.bap.hos.service.HosReportService;
/**
 * 
 * 
 * @author edgar_chan     lineshow7@gmail.com
 * @since 2015年3月18日
 */
@Service
public class HosReportServiceImpl implements HosReportService{

	@Resource
	private HosReportDao hosReportDao;
	
	@Override
	@Transactional(value="transactionManagerHos",readOnly=true)
	public List<Station> selStationDetail() {
		
		return hosReportDao.selStationDetail();
	}

	@Override
	@Transactional(value="transactionManagerHos",readOnly=true)
	public List<Shift> selCurrentShift(String Shift_StationNo,
			String Shift_ShiftDate) {
		
		return hosReportDao.selCurrentShift(Shift_StationNo, Shift_ShiftDate);
	}

}
