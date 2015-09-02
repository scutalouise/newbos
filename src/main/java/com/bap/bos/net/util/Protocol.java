package com.bap.bos.net.util;

import java.io.Serializable;

/**
 * 通讯协议实体类
 * @author zhulong
 *
 */
public class Protocol implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private byte[] PHeader;
	private	Integer Size_PHeader=2;
	private byte PSecretKey;
	private	Integer Size_PSecretKey=1;
	private byte[] PSize;
	private	Integer Size_PSize=2;
	private byte[] PNo;
	private	Integer Size_PNo=4;
	private byte PCommand;
	private	Integer Size_PCommand=1;
	private byte[] PData;
	private	Integer Size_PData;
	private byte PCheckCode;
	private	Integer Size_PCheckCode=1;
	public byte[] getPHeader() {
		return PHeader;
	}
	public void setPHeader(byte[] pHeader) {
		PHeader = pHeader;
	}
	public Integer getSize_PHeader() {
		return Size_PHeader;
	}
	public void setSize_PHeader(Integer sizePHeader) {
		Size_PHeader = sizePHeader;
	}
	public byte getPSecretKey() {
		return PSecretKey;
	}
	public void setPSecretKey(byte pSecretKey) {
		PSecretKey = pSecretKey;
	}
	public Integer getSize_PSecretKey() {
		return Size_PSecretKey;
	}
	public void setSize_PSecretKey(Integer sizePSecretKey) {
		Size_PSecretKey = sizePSecretKey;
	}
	public byte[] getPSize() {
		return PSize;
	}
	public void setPSize(byte[] pSize) {
		PSize = pSize;
	}
	public Integer getSize_PSize() {
		return Size_PSize;
	}
	public void setSize_PSize(Integer sizePSize) {
		Size_PSize = sizePSize;
	}
	public byte[] getPNo() {
		return PNo;
	}
	public void setPNo(byte[] pNo) {
		PNo = pNo;
	}
	public Integer getSize_PNo() {
		return Size_PNo;
	}
	public void setSize_PNo(Integer sizePNo) {
		Size_PNo = sizePNo;
	}
	public byte getPCommand() {
		return PCommand;
	}
	public void setPCommand(byte pCommand) {
		PCommand = pCommand;
	}
	public Integer getSize_PCommand() {
		return Size_PCommand;
	}
	public void setSize_PCommand(Integer sizePCommand) {
		Size_PCommand = sizePCommand;
	}
	
	public byte getPCheckCode() {
		return PCheckCode;
	}
	public void setPCheckCode(byte pCheckCode) {
		PCheckCode = pCheckCode;
	}
	public Integer getSize_PCheckCode() {
		return Size_PCheckCode;
	}
	public void setSize_PCheckCode(Integer sizePCheckCode) {
		Size_PCheckCode = sizePCheckCode;
	}
	public byte[] getPData() {
		return PData;
	}
	public void setPData(byte[] pData) {
		PData = pData;
	}
	public Integer getSize_PData() {
		return Size_PData;
	}
	public void setSize_PData(Integer sizePData) {
		Size_PData = sizePData;
	}
	
	
	
	
}
