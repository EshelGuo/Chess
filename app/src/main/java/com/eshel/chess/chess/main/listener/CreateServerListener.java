package com.eshel.chess.chess.main.listener;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eshel.chess.chess.App;
import com.eshel.chess.chess.socket.ScanDeviceTool;
import com.eshel.chess.chess.socket.tasks.GameTask;
import com.eshel.chess.chess.socket.tasks.ServerTask;
import com.eshel.chess.chess.utils.TaskHelper;

/**
 * createBy guoshiwen
 * <br>createTime: 2019/9/11 10:21
 * <br>desc: TODO
 */
public class CreateServerListener implements View.OnClickListener{
	private GameTask.GameView mView;

	public CreateServerListener(GameTask.GameView gameView) {
		mView = gameView;
	}

	@Override
	public void onClick(View v) {
		final EditText editText = new EditText(v.getContext());
		new AlertDialog.Builder(v.getContext())
				.setView(editText)
				.setTitle("请输入房间名")
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						String text = editText.getText().toString();
						if(TextUtils.isEmpty(text)){
							Toast.makeText(App.Context,"服务器名不能为空",Toast.LENGTH_LONG).show();
							return;
						}
						TaskHelper.execute(new ServerTask(text, mView));
					}
				})
				.show();
	}
}
