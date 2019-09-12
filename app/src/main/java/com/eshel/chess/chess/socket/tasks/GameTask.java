package com.eshel.chess.chess.socket.tasks;

import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import com.eshel.chess.chess.socket.Command;
import com.eshel.chess.chess.socket.Config;
import com.eshel.chess.chess.socket.WaitMoveCallback;
import com.eshel.chess.chess.utils.Task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * createBy guoshiwen
 * <br>createTime: 2019/9/11 10:39
 * <br>desc: TODO
 */
public class GameTask extends Task{

	public static final String TAG = "GameTask";

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
			mView.initGame(false);

			//服务器先手, 等待服务器走棋
			String command;
			while ((command = br.readLine()) != null){
				disposeCommand(command, bw);
			}

//			os.close();
//			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void disposeCommand(String command, BufferedWriter bw) {
		//过滤无效命令
		if(command == null || command.length() == 0)
			return;
		try {
			Log.d(TAG, "from server command: " + command);
			disposeCommandInternal(command, bw);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	private void disposeCommandInternal(String command, BufferedWriter bw) throws IOException {
		//对方走棋命令
		if(command.startsWith(Command.MOVE)){
			command = command.substring(Command.MOVE.length());
			String[] temp = command.split(",");
			String from = temp[0];
			String to = temp[1];

			String[] from_ = from.split("-");
			String[] to_ = to.split("-");

			int fromX = Integer.valueOf(from_[0]);
			int fromY = Integer.valueOf(from_[1]);

			int toX = Integer.valueOf(to_[0]);
			int toY = Integer.valueOf(to_[1]);

			mView.movePieces(fromX, fromY, toX, toY);
			command = ServerTask.waitMove(mView);
			bw.write(command);
			bw.newLine();
			bw.flush();
		}
	}

	public interface GameView{
		void connectionFailed(String msg);
		void initRoomName(String name);
		void initServerIP(String ip);
		void initGame(boolean first);
		void waitMove(WaitMoveCallback callback);
		void movePieces(int fromX, int fromY, int toX, int toY);
	}
}
