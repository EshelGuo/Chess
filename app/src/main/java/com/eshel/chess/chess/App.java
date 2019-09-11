package com.eshel.chess.chess;

import android.app.Application;
import android.os.Handler;


/**
 * createBy Eshel
 * createTime: 2018/8/25 09:45
 */
public class App extends Application{
	public static App Context;

	public App() {
		Context = this;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		mHandler = new Handler();
	}

	public Handler mHandler;

	public Handler getHandler() {
		return mHandler;
	}
}
