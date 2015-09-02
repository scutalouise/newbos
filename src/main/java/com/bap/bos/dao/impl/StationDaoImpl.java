package com.bap.bos.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.bap.bos.dao.StationDao;
import com.bap.bos.domain.Station;
import com.bap.bos.util.DaoTemplate;


/**
 * 气站操作实现类
 * @author zhulong
 *
 */
@Repository("stationDao")
public class StationDaoImpl extends DaoTemplate<Station,String> implements StationDao{
	
	/* (non-Javadoc)
	 * @see com.bos.dao.impl.StationDao#selStationDetail(java.lang.String)
	 */
	
	public List<Station> selStationDetail(){
		String hql="from Station";
		return this.selectObject(hql);
	}


}
