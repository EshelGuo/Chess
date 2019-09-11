package com.eshel.chess.chess.socket.tasks;

import android.support.annotation.NonNull;

import com.eshel.chess.chess.Util;
import com.eshel.chess.chess.socket.Config;
import com.eshel.chess.chess.utils.Result;
import com.eshel.chess.chess.utils.Task;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;

/**
 * createBy guoshiwen
 * <br>createTime: 2019/9/11 10:28
 * <br>desc: TODO
 */
public class CheckServerTask extends Task{
	private List<String> ips;
	// key(ip): value(name)
	private HashMap<String, String> result;
	private Result<HashMap<String, String>> callback;

	public CheckServerTask(@NonNull List<String> ips, @NonNull Result<HashMap<String, String>> callback) {
		this.ips = ips;
		result = new HashMap<>(ips.size() /2 );
		this.callback = callback;
	}

	@Override
	public void run() {
		if(Util.isEmpty(ips)){
			callback.onResult(null);
			return;
		}

		for (String ip : ips) {

		}
	}
}
