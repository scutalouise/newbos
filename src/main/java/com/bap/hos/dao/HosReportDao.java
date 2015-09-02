package com.bap.hos.dao;

import java.util.List;

import com.bap.bos.domain.Shift;
import com.bap.bos.domain.Station;

public interface HosReportDao {

	public abstract List<Station> selStationDetail();
	/**
	 * 查询开班记录
	 * @param Shift_StationNo 开班日期
	 * @return
	 */
	public List<Shift> selCurrentShift(String Shift_StationNo,String Shift_ShiftDate);

}