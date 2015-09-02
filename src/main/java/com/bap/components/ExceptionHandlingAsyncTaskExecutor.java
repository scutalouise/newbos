package com.bap.components;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.AsyncTaskExecutor;

public class ExceptionHandlingAsyncTaskExecutor implements AsyncTaskExecutor {

	private AsyncTaskExecutor executor;
 
	Logger logger = LoggerFactory.getLogger(ExceptionHandlingAsyncTaskExecutor.class);
	
	public ExceptionHandlingAsyncTaskExecutor(AsyncTaskExecutor executor) {
		this.executor = executor;
	}

	// //用独立的线程来包装，@Async其本质就是如此
	public void execute(Runnable task) {
		executor.execute(createWrappedRunnable(task));
	}

	public void execute(Runnable task, long startTimeout) {  
	       executor.execute(createWrappedRunnable(task), startTimeout);           
	    }	public Future submit(Runnable task) {
		return executor.submit(createWrappedRunnable(task));
		// 用独立的线程来包装，@Async其本质就是如此。
	}

	public Future submit(final Callable task) {
		// 用独立的线程来包装，@Async其本质就是如此。
		return executor.submit(createCallable(task));
	}

	private Callable<T> createCallable(final Callable<T> task) {
		return new Callable<T>() {
			public T call() throws Exception {
				try {
					return task.call();
				} catch (Exception ex) {
					handle(ex);
					throw ex;
				}
			}
		};
	}

	private Runnable createWrappedRunnable(final Runnable task) {
		return new Runnable() {
			public void run() {
				try {
					task.run();
				} catch (Exception ex) {
					handle(ex);
				}
			}
		};
	}

	private void handle(Exception ex) {
		// 具体的异常逻辑处理的地方
		logger.error("Error during @Async execution: " + ex);
	}
}
