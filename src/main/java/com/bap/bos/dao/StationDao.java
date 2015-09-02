package com.bap.bos.dao;

import java.util.List;

import com.bap.bos.domain.Station;

public interface StationDao {
	
	/**
	 * 查询气站详细信息
	 * @param hql
	 * @return
	 */
	
	public List<Station> selStationDetail();

}