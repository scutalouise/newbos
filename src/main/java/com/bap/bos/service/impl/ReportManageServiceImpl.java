package com.bap.bos.service.impl;

import java.text.ParseException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bap.bos.dao.ReportManageDao;
import com.bap.bos.domain.Shift;
import com.bap.bos.service.ReportManageService;
import com.bap.bos.util.SellDetails;

@Service
public class ReportManageServiceImpl implements ReportManageService {
	@Resource
	private ReportManageDao reportManageDao;

	@Override
	@Transactional(readOnly=true)
	public List<Shift> selCurrentShift(String Shift_StationNo,
			String Shift_ShiftDate) {
		return reportManageDao.selCurrentShift(Shift_StationNo, Shift_ShiftDate);
	}

	@Override
	@Transactional(readOnly=true)
	public List<SellDetails> sellDetails(String startDate, String endDate,
			String staffNo, String cardNo, String nozzleNo)
			throws ParseException {
		return reportManageDao.sellDetails(startDate, endDate, staffNo, cardNo, nozzleNo);
	}

}
