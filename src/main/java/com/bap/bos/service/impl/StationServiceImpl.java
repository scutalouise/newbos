package com.bap.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bap.bos.dao.StationDao;
import com.bap.bos.domain.Station;
import com.bap.bos.service.StationService;

@Service("stationService")
public class StationServiceImpl implements StationService{
	
	@Resource
	private StationDao stationDao;
	
	@Transactional(readOnly=true)
	public List<Station> selStationDetail(){
		return stationDao.selStationDetail();
	}
	
}
