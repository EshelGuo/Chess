package com.eshel.chess.chess.main.listener;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;

import com.eshel.chess.chess.RUtil;
import com.eshel.chess.chess.game.ChessView;
import com.eshel.chess.chess.game.Game;
import com.eshel.chess.chess.game.Style;

/**
 * createBy guoshiwen
 * <br>createTime: 2019/9/10 19:01
 * <br>desc: 更换棋盘样式点击事件
 */
public class ChangeChessboardStyleListener implements View.OnClickListener{

	private final String[] Checkerboard;
	private final Game game;
	private final ChessView mChessView;

	public ChangeChessboardStyleListener(String[] checkerboard, Game game, ChessView chessView) {
		Checkerboard = checkerboard;
		this.game = game;
		mChessView = chessView;
	}

	@Override
	public void onClick(View v) {
		new AlertDialog.Builder(v.getContext())
				.setItems(Checkerboard, new OnItemClickListener())
				.show();
	}

	private class OnItemClickListener implements DialogInterface.OnClickListener{

		@Override
		public void onClick(DialogInterface dialog, int which) {
			Style.getDefaultStyle().setCheckerboardStyle(RUtil.getStaticInt(Style.class, Checkerboard[which]));
			game.reSetStyle();
			mChessView.invalidate();
		}
	}
}
