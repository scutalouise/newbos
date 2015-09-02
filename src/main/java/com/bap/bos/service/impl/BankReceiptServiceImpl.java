package com.bap.bos.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bap.bos.dao.BankReceiptDao;
import com.bap.bos.domain.SaveToBank;
import com.bap.bos.service.BankReceiptService;
import com.bap.bos.util.Page;
/**
 * 银行存款作业Service
 * @author zhulong
 *
 */

@Service
public class BankReceiptServiceImpl implements BankReceiptService {
	
	@Resource
	private BankReceiptDao bankReceiptDao;
	
	/**
	 * 保存银行存款记录
	 * @param sb
	 */
	@Transactional
	public void saveBankReceipt(SaveToBank sb){
		bankReceiptDao.saveSaveToBank(sb);
	} 
	/**
	 * 更新存款记录
	 * @param sb
	 * @param date 格式：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	@Transactional
	public String updateBankReceipt(SaveToBank sb) throws Exception{
		List<SaveToBank> updateSaveToBank=bankReceiptDao.selOneSaveToBank(sb.getSaveToBank_StationNo(),sb.getSaveToBank_Receipt());
		if("2".equals(updateSaveToBank.get(0).getSaveToBank_IsSync())){
			/*未签核，可以进行修改*/
			SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String SaveToBank_SaveTime=formatDate.format(sb.getSaveToBank_SaveTime());
			bankReceiptDao.updateSaveToBank("update tb_SaveToBank set SaveToBank_ActSaved="+sb.getSaveToBank_ActSaved()+"," +
					"SaveToBank_Bank='"+sb.getSaveToBank_Bank()+"'," +
					"SaveToBank_SaveTime='"+SaveToBank_SaveTime+"'," +
					"SaveToBank_Remark='"+sb.getSaveToBank_Remark()+"'," +
					"SaveToBank_StaffNo='"+sb.getSaveToBank_StaffNo()+"'," +
					"SaveToBank_OperateTime='"+formatDate.format(new Date())+"'," +
					"SaveToBank_SyncDate='"+formatDate.format(new Date())+"' " +
					"where SaveToBank_Receipt='"+sb.getSaveToBank_Receipt()+"' and SaveToBank_StationNo='"+sb.getSaveToBank_StationNo()+"'");
			return "success";
		}else{
			return "UnUpdate";
		}
		
	}
	/**
	 * 删除银行存款记录
	 * @param sb
	 * @param date 格式：yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	@Transactional
	public String delBankReceipt(SaveToBank sb){
		List<SaveToBank> updateSaveToBank=bankReceiptDao.selOneSaveToBank(sb.getSaveToBank_StationNo(),sb.getSaveToBank_Receipt());
		if("2".equals(updateSaveToBank.get(0).getSaveToBank_IsSync())){
			/*未签核，可以进行删除*/
			bankReceiptDao.delSaveToBank(sb);
			return "success";
		}else{
			return "UnDel";
		}
		
	}
	/**
	 * 查询银行存款记录
	 * @param start 开始时间
	 * @param end	结束时间	
	 * @param page	分页类
	 * @return
	 */
	@Transactional(readOnly=true)
	public List<Object> selSaveToBank(String start,String end,Page page){
		return bankReceiptDao.selSaveToBank(start, end, page);
	}
	@Transactional(readOnly=true)
	public List<Object> selSaveToBank(String start,String end){
		return bankReceiptDao.selSaveToBank(start, end);
	}
	/**
	 * 存款记录签核
	 * @param StationNo
	 * @param SaveToBank_Receipt
	 * @return
	 */
	@Transactional
	public boolean signBankReceipt(String StationNo,String SaveToBank_Receipt){
		try {
			SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String SyncDate=formatDate.format(new Date());
			String sql="update tb_SaveToBank set SaveToBank_IsSync='1',SaveToBank_SyncDate='"+SyncDate+"' " +
					" where SaveToBank_StationNo='"+StationNo+"' and " +
						"SaveToBank_Receipt='"+SaveToBank_Receipt+"'";
			//System.out.print(sql);
			bankReceiptDao.updateSaveToBank(sql);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	
	
	public BankReceiptDao getBankReceiptDao() {
		return bankReceiptDao;
	}
	public void setBankReceiptDao(BankReceiptDao bankReceiptDao) {
		this.bankReceiptDao = bankReceiptDao;
	}
	
	

}
