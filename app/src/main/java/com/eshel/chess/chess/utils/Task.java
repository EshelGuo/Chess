package com.eshel.chess.chess.utils;

import android.os.Handler;
import android.os.Looper;

/**
 * createBy guoshiwen
 * <br>createTime: 2019/9/11 10:04
 * <br>desc: TODO
 */
public class Task implements Runnable{
	private static Handler mHandler = new Handler(Looper.getMainLooper());

	@Override
	public void run() {

	}

	public static void runOnUIThread(Runnable runnable){
		mHandler.post(runnable);
	}
}
