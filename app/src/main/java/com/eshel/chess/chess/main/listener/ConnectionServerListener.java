package com.eshel.chess.chess.main.listener;

import android.view.View;

import com.eshel.chess.chess.socket.tasks.ClientTask;
import com.eshel.chess.chess.utils.TaskHelper;

/**
 * createBy guoshiwen
 * <br>createTime: 2019/9/11 10:23
 * <br>desc: TODO
 */
public class ConnectionServerListener implements View.OnClickListener{
	@Override
	public void onClick(View v) {
		TaskHelper.execute(new ClientTask());
	}
}
