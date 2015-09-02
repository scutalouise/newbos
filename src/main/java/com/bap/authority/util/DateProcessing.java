package com.bap.authority.util;
/**
 * 数据处理
 * 2014/02/18
 * @author long
 *
 */
public class DateProcessing {
	/**
	 * 取消字符串中间和两端所有空格
	 * @param S - 有空格字符串
	 * @return	- 无空格字符串
	 */
	public String StringCancelSpace(String S){
		return S.replaceAll(" ", "");
	}
	
}
