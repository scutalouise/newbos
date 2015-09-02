package com.bap.bos.components;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.bap.bos.service.DensityService;

/**
 * 库存改变事件监听器
 * 
 * @author edgar_chan     lineshow7@gmail.com
 * @since 2015年5月6日
 */
@Component
public class RepositoryChangedEventListener implements ApplicationListener<RepositoryChangedEvent>{

	@Resource
	private DensityService densityService;
	
	Logger loger = LoggerFactory.getLogger(RepositoryChangedEventListener.class);
	
	@Override
	@Async
	public void onApplicationEvent(RepositoryChangedEvent event) {
		try{
			densityService.generateMonthAreaDensity(event.getSource().toString());
		}catch(Exception e){
			loger.warn("初始平均密度生成过程异常!",e);
		}
	}
	
	
}
