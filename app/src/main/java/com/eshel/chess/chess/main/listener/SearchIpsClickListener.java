package com.eshel.chess.chess.main.listener;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.eshel.chess.chess.App;
import com.eshel.chess.chess.socket.tasks.GameTask;
import com.eshel.chess.chess.utils.Result;
import com.eshel.chess.chess.utils.TaskHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * createBy guoshiwen
 * <br>createTime: 2019/9/11 09:39
 * <br>desc: TODO
 */
public class SearchIpsClickListener implements View.OnClickListener {

	public static final String TAG = "SearchIpsClickListener";
	private static final int MAX = 3;

	private Context mContext;
	private GameTask.GameView mGameView;

	public SearchIpsClickListener(GameTask.GameView gameView) {
		mGameView = gameView;
	}

	@Override
	public void onClick(final View v) {
		mContext = v.getContext();
		final EditText editText = new EditText(mContext);
		editText.setText("192.168.4.191");
		new AlertDialog.Builder(mContext)
				.setTitle("请输入对方IP地址")
				.setView(editText)
				.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						String ip = editText.getText().toString();
						TaskHelper.execute(new GameTask(mGameView, ip));
					}
				})
				.show();
	}

	private void showSelectGameDialog(HashMap<String, String> result) {
		List<String> ips = new ArrayList<>(result.size());
		List<String> names = new ArrayList<>(result.size());

		for (Map.Entry<String, String> entry : result.entrySet()) {
			ips.add(entry.getKey());
			names.add(entry.getValue());
		}
		new AlertDialog.Builder(mContext)
				.setTitle("请选择一个游戏")
				.setItems(names.toArray(new String[names.size()]), new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(mContext, "" + which, Toast.LENGTH_LONG).show();
					}
				})
				.show();
	}

	private class Callback implements Result<HashMap<String, String>> {

		@Override
		public void onResult(HashMap<String, String> result) {
			if (result == null)
				return;

			if (result.size() == 0) {
				Toast.makeText(App.Context, "未能找到游戏", Toast.LENGTH_LONG).show();
				return;
			}

			showSelectGameDialog(result);
		}
	}
}
