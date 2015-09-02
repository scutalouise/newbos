package com.bap.bos.components;

import org.springframework.context.ApplicationEvent;

/**
 * 库存改变事件
 * 
 * @author edgar_chan     lineshow7@gmail.com
 * @since 2015年5月5日
 */
public class RepositoryChangedEvent extends ApplicationEvent{
	private static final long serialVersionUID = -4926375778840758976L;

	public RepositoryChangedEvent(Object source) {
		super(source);
	}
	
}
