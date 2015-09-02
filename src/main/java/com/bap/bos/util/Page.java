package com.bap.bos.util;
/**
 * 
 * @author zhulong
 *
 */
public class Page {
	private int pageSize = 15;		//页面大小
	private int pageNum = 1;		//第几页
	private int pages;				//总页数
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
}
