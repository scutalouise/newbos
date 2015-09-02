package com.bap.bos.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bap.bos.dao.StaffDao;
import com.bap.bos.domain.PositionType;
import com.bap.bos.domain.Staff;
import com.bap.bos.service.StaffService;
import com.bap.bos.util.Page;

/**
 * 
 * 
 * @author edgar_chan lineshow7@gmail.com
 * @since 2015年3月16日
 */
@Service
public class StaffServiceImpl implements StaffService {
	@Resource
	private StaffDao staffDao;

	@Override
	@Transactional(readOnly=true)
	public List<PositionType> selPositionType() {
		return staffDao.selPositionType();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Staff> selStaffDetail(String StaffNo, String StaffPW) {
		
		return staffDao.selStaffDetail(StaffNo,StaffPW);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Object> selAllStaffsDetail(Page page) {
		
		return staffDao.selAllStaffsDetail(page);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Staff> selAllStaffsDetail() {
		
		return staffDao.selAllStaffsDetail();
	}

	@Override
	@Transactional(readOnly=true)
	public List<Staff> selStaffName(String FirstName) {
		
		return staffDao.selStaffName(FirstName);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Object> selStaffName(String name, Page page) {
		
		return staffDao.selStaffName(name,page);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Staff> selStaffNo(String firstNo) {
		
		return staffDao.selStaffNo(firstNo);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Object> selStaffNo(String no, Page page) {
		
		return staffDao.selStaffNo(no,page);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Staff> selStaffID(String firstID) {
		
		return staffDao.selStaffID(firstID);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Object> selStaffID(String id, Page page) {
		
		return staffDao.selStaffID(id,page);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Object> selStaffStatus(String status, Page page) {
		
		return staffDao.selStaffStatus(status,page);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Object> selStaffStatus(String status) {
		
		return staffDao.selStaffStatus(status);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Object> selAllStaffGroupsInfo(String start, String end,
			String selType, String selWord) {
		
		return staffDao.selAllStaffGroupsInfo(start, end,
				selType, selWord);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Object> selAllStaffGroupsInfo(String start, String end,
			String selType, String selWord, Page page) {
		
		return staffDao.selAllStaffGroupsInfo(start, end, selType, selWord, page);
	}

	
	
}
