package com.bap.components;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ScheduledExecutorFactoryBean;
import org.springframework.scheduling.concurrent.ScheduledExecutorTask;
import org.springframework.web.servlet.view.jasperreports.JasperReportsViewResolver;

@Configuration
public class BeansEden {
	@Bean
	public JasperReportsViewResolver jasperReportResolver(DataSource dataSource){
		JasperReportsViewResolver jasperReportsViewResolver = new JasperReportsViewResolver();
		jasperReportsViewResolver.setOrder(0);
		jasperReportsViewResolver.setViewClass(BapJasperReportsMultiFormatView.class);
		jasperReportsViewResolver.setPrefix("/WEB-INF/classes/report/");
		jasperReportsViewResolver.setSuffix(".jasper");
		jasperReportsViewResolver.setViewNames(new String[]{"*-report"});
		jasperReportsViewResolver.setCache(false); // debug-> false
		jasperReportsViewResolver.setJdbcDataSource(dataSource);
	//	Map<String,Object> exporterParameters = new HashMap<String, Object>();
	//	exporterParameters.put(JRHtmlExporterParameter.IMAGES_URI, "images");
	//	exporterParameters.put(JRHtmlExporterParameter.CHARACTER_ENCODING.toString(), "utf-8");
	//	jasperReportsViewResolver.setExporterParameters(exporterParameters);
		
		return jasperReportsViewResolver;
	}
	
	@Bean
	public ScheduledExecutorFactoryBean scheduledExecutorFactoryBean() throws Exception {
		
		
		ScheduledExecutorTask scheduledExecutorTask = new ScheduledExecutorTask(new Runnable() {
			
			@Override
			public void run() {
				test();
			}
		},5000);
		
		
		ScheduledExecutorFactoryBean scheduledExecutorFactoryBean = new ScheduledExecutorFactoryBean();
		
		scheduledExecutorFactoryBean.setScheduledExecutorTasks(scheduledExecutorTask);
			
		return scheduledExecutorFactoryBean;
		
	}
	
	public void test(){
	//	System.out.println("test.....");
	}
	
}
