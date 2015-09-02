package com.bap.hos.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bap.bos.domain.Shift;
import com.bap.bos.domain.Station;


public interface HosReportService {

	@Transactional
	public abstract List<Station> selStationDetail();
	/**
	 * 查询开班记录
	 * @param Shift_StationNo 开班日期
	 * @return
	 */
	@Transactional
	public List<Shift> selCurrentShift(String Shift_StationNo,String Shift_ShiftDate);

}
