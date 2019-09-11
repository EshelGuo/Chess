package com.eshel.chess.chess.socket.tasks;

import android.util.Log;

import com.eshel.chess.chess.Util;
import com.eshel.chess.chess.socket.Command;
import com.eshel.chess.chess.socket.Config;
import com.eshel.chess.chess.socket.ScanDeviceTool;
import com.eshel.chess.chess.utils.Task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * createBy Eshel
 * createTime: 2018/8/26 21:53
 */
public class ServerTask extends Task {

	private static final String TAG = ServerTask.class.getSimpleName();
	private ServerSocket mServerSocket;
	private String name;
	private GameTask.GameView mView;

	public ServerTask(String name, GameTask.GameView gameView) {
		this.name = name;
		mView = new GameViewWrap(gameView);
		mView.initServerIP(ScanDeviceTool.getHostIP());
	}

	@Override
	public void run() {
		try {
			mServerSocket = new ServerSocket(Config.PORT);
			while (true) {
				final Socket socket = mServerSocket.accept();
				Log.d(TAG, "连接中");
				Log.d(TAG, "target IP: " + socket.getInetAddress().toString());
				try {
					InputStream is = socket.getInputStream();
					OutputStream os = socket.getOutputStream();

					BufferedReader br = new BufferedReader(new InputStreamReader(is));
					BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os));

					String command;

					while ((command = br.readLine()) != null){
						Log.d(TAG, "command: " + command);
						disposeCommand(bw, command);
					}

					is.close();
					os.close();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void disposeCommand(BufferedWriter bw, String command) throws IOException {
		if(command == null)
			return;

		if (command.equals(Command.REQUEST_NAME)) {
			bw.write(name);
			bw.newLine();
			bw.flush();
			mView.initRoomName(name);
			mView.initGame(true);
		}
	}
}
