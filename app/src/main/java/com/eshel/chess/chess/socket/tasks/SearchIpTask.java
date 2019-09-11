package com.eshel.chess.chess.socket.tasks;

import android.support.annotation.NonNull;
import android.util.Log;

import com.eshel.chess.chess.socket.ScanDeviceTool;
import com.eshel.chess.chess.utils.Result;
import com.eshel.chess.chess.utils.Task;

import java.util.List;

/**
 * createBy guoshiwen
 * <br>createTime: 2019/9/11 10:01
 * <br>desc: 扫描IP地址
 */
public class SearchIpTask extends Task{

	private static final String TAG = "SearchIpTask";
	private final Result<List<String>> result;
	public SearchIpTask(@NonNull Result<List<String>> result) {
		this.result = result;
	}

	@Override
	public void run() {
		ScanDeviceTool scanDeviceTool = new ScanDeviceTool();
		final List<String> result = scanDeviceTool.scan();
		Log.i(TAG, result.toString());
		runOnUIThread(new Runnable() {
			@Override
			public void run() {
				SearchIpTask.this.result.onResult(result);
			}
		});
	}
}
