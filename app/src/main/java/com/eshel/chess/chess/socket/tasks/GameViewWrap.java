package com.eshel.chess.chess.socket.tasks;

import com.eshel.chess.chess.utils.Task;

/**
 * createBy guoshiwen
 * <br>createTime: 2019/9/11 14:43
 * <br>desc: TODO
 */
public class GameViewWrap extends Task implements GameTask.GameView{

	private GameTask.GameView mGameView;

	public GameViewWrap(GameTask.GameView gameView) {
		mGameView = gameView;
	}

	@Override
	public void connectionFailed(final String msg) {
		runOnUIThread(new Runnable() {
			@Override
			public void run() {
				mGameView.connectionFailed(msg);
			}
		});
	}

	@Override
	public void initRoomName(final String name) {
		runOnUIThread(new Runnable() {
			@Override
			public void run() {
				mGameView.initRoomName(name);
			}
		});
	}

	@Override
	public void initServerIP(final String ip) {
		runOnUIThread(new Runnable() {
			@Override
			public void run() {
				mGameView.initServerIP(ip);
			}
		});
	}
}
