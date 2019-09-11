package com.eshel.chess.chess.socket.tasks;

import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.eshel.chess.chess.Util;
import com.eshel.chess.chess.socket.Command;
import com.eshel.chess.chess.socket.Config;
import com.eshel.chess.chess.utils.Result;
import com.eshel.chess.chess.utils.Task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.HashMap;

/**
 * createBy guoshiwen
 * <br>createTime: 2019/9/11 10:39
 * <br>desc: TODO
 */
public class GameTask extends Task{

	private GameView mView;
	private String ip;

	public GameTask(@NonNull GameView view, String ip){
		this.ip = ip;
		this.mView = new GameViewWrap(view);
	}

	@Override
	public void run() {
		if(TextUtils.isEmpty(ip)){
			mView.connectionFailed("IP 地址为空");
			return;
		}

		try {
			Socket socket = new Socket(ip, Config.PORT);

			OutputStream os = socket.getOutputStream();
			InputStream is = socket.getInputStream();

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));
			BufferedReader br = new BufferedReader(new InputStreamReader(is));

			SystemClock.sleep(500);
			bw.write(Command.REQUEST_NAME);
			bw.newLine();
			bw.flush();

			String name = br.readLine();
			mView.initRoomName(name);
//			os.close();
//			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public interface GameView{
		void connectionFailed(String msg);
		void initRoomName(String name);
		void initServerIP(String ip);
	}
}
