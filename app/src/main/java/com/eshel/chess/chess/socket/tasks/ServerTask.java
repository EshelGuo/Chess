package com.eshel.chess.chess.socket.tasks;

import android.graphics.Point;
import android.util.Log;

import com.eshel.chess.chess.socket.Command;
import com.eshel.chess.chess.socket.Config;
import com.eshel.chess.chess.socket.ScanDeviceTool;
import com.eshel.chess.chess.socket.WaitMoveCallback;
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

	private static final String TAG = "GameTask_";
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

					while ((command = br.readLine()) != null) {
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
		if (command == null)
			return;

		if (command.equals(Command.REQUEST_NAME)) {
			bw.write(name);
			bw.newLine();
			bw.flush();
			mView.initRoomName(name);
			mView.initGame(true);

			//等待用户走棋
			command = waitMove(mView);
			bw.write(command);
			bw.newLine();
			bw.flush();
		} else if(command.startsWith(Command.MOVE)){
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
			command = waitMove(mView);
			bw.write(command);
			bw.newLine();
			bw.flush();
		}
	}

	public static String waitMove(GameTask.GameView view) {
		Log.d(TAG, "wait user moving");
		final Point fromPoint = new Point(-1, -1);
		final Point toPoint = new Point(-1, -1);
		final WaitMoveCallback callback = new WaitMoveCallback() {

			@Override
			public void move(int fromX, int fromY, int toX, int toY) {
				fromPoint.set(fromX, fromY);
				toPoint.set(toX, toY);
			}
		};
		view.waitMove(callback);
		while (true) {
			if (fromPoint.x == -1 || toPoint.x == -1 || fromPoint.y == -1 || toPoint.y == -1) {
				Thread.yield();
			}else {
				break;
			}
		}
		Log.d(TAG, "user moved");
		return Command.MOVE + fromPoint.x + "-" + fromPoint.y + "," + toPoint.x + "-" +toPoint.y;
	}
}
