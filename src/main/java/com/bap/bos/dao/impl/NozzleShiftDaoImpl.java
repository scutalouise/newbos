package com.bap.bos.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.bap.bos.dao.NozzleShiftDao;
import com.bap.bos.util.DaoTemplate;

/**
 * 
 * 
 * @author edgar_chan     lineshow7@gmail.com
 * @since 2015年4月8日
 */
@Repository
public class NozzleShiftDaoImpl extends DaoTemplate<Object, String> implements NozzleShiftDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> getGroupedNozzleVal(String shiftdate,
			String shiftNo) {
		
		StringBuilder hql = new StringBuilder();
		hql.append("select NozzleShift_ProductNum as t_oilcode,sum(NozzleShift_StartVol) as t_startVol ,sum(NozzleShift_EndVol) as t_endvol from NozzleShift ");
		hql.append("where nozzleshift_shiftdate = ?  and nozzleshift_shiftNo = ? ");
		hql.append("group by NozzleShift_ProductNum");
		
		Query query = this.getSession().createQuery(hql.toString());
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		
		query.setString(0, shiftdate);
		query.setString(1, shiftNo);
		
		return query.list();
	}
	
}
