package com.bap.bos.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bap.bos.dao.TranItemDao;
import com.bap.bos.domain.TransItem;
import com.bap.bos.service.TranItemService;

/**
 * 
 * 
 * @author edgar_chan lineshow7@gmail.com
 * @since 2015年3月16日
 */
@Service
public class TranItemServiceImpl implements TranItemService {
	@Resource
	private TranItemDao tranItemDao;

	@Override
	@Transactional(readOnly=true)
	public List<TransItem> selTransItem(String transItem_ShiftDate,
			String transItem_ShiftNo) {
		return tranItemDao.selTransItem(transItem_ShiftDate,transItem_ShiftNo);
	}

	@Override
	@Transactional
	public void saveTransItem(TransItem TransItem) {
		
		tranItemDao.saveTransItem(TransItem);
	}

	@Override
	@Transactional(readOnly=true)
	public List<TransItem> selTransItemDate(Date startDate, Date endDate) {
		
		return tranItemDao.selTransItemDate(startDate, endDate);
	}
	
	
	
	
}
