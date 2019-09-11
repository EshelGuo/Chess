package com.eshel.chess.chess.utils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * createBy guoshiwen
 * <br>createTime: 2019/9/11 09:51
 * <br>desc: 执行后台任务
 */
public class TaskHelper {

	private static TaskHelper INSTANCE;
	private static TaskHelper getInstance(){
	    if(INSTANCE == null){
	        synchronized (TaskHelper.class){
	            if(INSTANCE == null)
	                INSTANCE = new TaskHelper();
	        }
	    }
	    return INSTANCE;
	}

	private int corePoolSize = 64;
	/** 线程池最大线程数 **/
	private static final int MAX_IMUM_POOL_SIZE = 255;

	private ThreadPoolExecutor mExecutor;

	private TaskHelper() {
		mExecutor = new ThreadPoolExecutor(corePoolSize, MAX_IMUM_POOL_SIZE, 60000
				, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(corePoolSize));
	}

	public void _execute(Runnable task){
		try {
			mExecutor.execute(task);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public static void execute(Runnable task){
		getInstance()._execute(task);
	}

	public static void destory() {
		TaskHelper instance = INSTANCE;
		if(instance == null)
			return;

		if (instance.mExecutor != null) {
			instance.mExecutor.shutdownNow();
		}

		INSTANCE = null;
	}
}
