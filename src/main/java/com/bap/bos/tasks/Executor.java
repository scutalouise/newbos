package com.bap.bos.tasks;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;

import com.bap.bos.service.DayAndMouthStatisticsService;
import com.bap.bos.service.DayTransVerifyService;

/**
 * 任务执行器
 * 
 * @author edgar_chan lineshow7@gmail.com
 * @since 2015年3月23日
 */
@Configuration
@EnableAsync
public class Executor implements ApplicationListener<ContextRefreshedEvent>{

	private Logger logger = LoggerFactory.getLogger(Executor.class);

	@Resource
	private DayTransVerifyService dayTransVerifyService;

	@Resource
	private DayAndMouthStatisticsService dayAndMouthStatisticsService;
	
	@Value("#{sysconfigs['quartz.cron.savedaytransverify']}")
	private String savedaytransverifyTimePoint;
	
	@Value("#{sysconfigs['quartz.cron.dayStatistics']}")
	private String dayStatisticsTimePoint;
	
	@Value("#{sysconfigs['quartz.cron.monthStatistics']}")
	private String monthStatisticsTimePoint;
	@Autowired
	private ApplicationContext applicationContext;
	
	
	/**
	 * 日结
	 * @author edgar_chan
	 * @since 2015年3月23日
	 */
	@Scheduled(cron = "${quartz.cron.savedaytransverify}")
	public void saveDayTransVerify() {
		try{
		logger.info("**开始执行 日结任务**"+"corn:"+savedaytransverifyTimePoint);
		dayTransVerifyService.saveDayTransVerify();
		logger.info("**结束执行 日结任务**"+"corn:"+savedaytransverifyTimePoint);
		}catch(Exception e){
			logger.error("日结异常！", e);
		}
		}
		

	
	/**
	 * 每日统计
	 * 
	 * @author edgar_chan
	 * @since 2015年3月23日
	 */
	@Scheduled(cron = "${quartz.cron.dayStatistics}")
	public void dayStatistics() {
		try {
		logger.info("**开始执行 日统计任务**"+"corn:"+dayStatisticsTimePoint);
		dayAndMouthStatisticsService.dayStatistics();
		logger.info("**结束执行  日统计任务**"+"corn:"+dayStatisticsTimePoint);
		}catch(Exception e){
			logger.error("日统计异常！", e);
		}
		}

	
	/**
	 * 每月统计
	 * 
	 * @author edgar_chan
	 * @since 2015年3月23日
	 */
	@Scheduled(cron = "${quartz.cron.monthStatistics}")
	public void monthStatistics() {
		try {
			logger.info("**开始执行 月统计任务**"+"corn:"+monthStatisticsTimePoint);
			dayAndMouthStatisticsService.monthStatistics();
			logger.info("**结束执行  月统计任务**"+"corn:"+monthStatisticsTimePoint);
		} catch (Exception e) {
			logger.error("每月统计异常！", e);
		}
	}


	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if(event.getApplicationContext().getDisplayName().equals("Root WebApplicationContext")){
			return;
		}
		
		ScheduledExecutorService threadPool = Executors.newSingleThreadScheduledExecutor();
		
		threadPool.schedule(new Runnable(){
			@Override
			public void run() {
				logger.info("**BOS开始执行补救维护任务**");
				try {
				/*logger.info("**开始执行 日结任务**"+"corn:"+savedaytransverifyTimePoint);
				dayTransVerifyService.saveDayTransVerify();
				logger.info("**结束执行 日结任务**"+"corn:"+savedaytransverifyTimePoint);
				logger.info("**开始执行 日统计任务**"+"corn:"+dayStatisticsTimePoint);
				dayAndMouthStatisticsService.dayStatistics();
				logger.info("**结束执行  日统计任务**"+"corn:"+dayStatisticsTimePoint);
				logger.info("**开始执行 月统计任务**"+"corn:"+monthStatisticsTimePoint);
				dayAndMouthStatisticsService.monthStatistics();
				logger.info("**结束执行 月统计任务**"+"corn:"+monthStatisticsTimePoint);*/
				saveDayTransVerify();
				dayStatistics();
				monthStatistics();
				dayAndMouthStatisticsService.initDensity();
				}catch(Exception e){
					logger.warn("**BOS补救维护任务异常**",e);
				}
				logger.info("**BOS补救维护任务执行完成**");
			}
		}, 5, TimeUnit.SECONDS);
		
		threadPool.shutdown();
	}

	
}
