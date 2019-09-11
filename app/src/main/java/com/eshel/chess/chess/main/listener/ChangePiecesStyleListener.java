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
 * <br>createTime: 2019/9/10 18:55
 * <br>desc: 更换棋子样式点击事件
 */
public class ChangePiecesStyleListener implements View.OnClickListener{

	private final String[] piecesStyles;
	private final Game game;
	private final ChessView mChessView;

	public ChangePiecesStyleListener(String[] piecesStyles, Game game, ChessView chessView) {
		this.piecesStyles = piecesStyles;
		this.game = game;
		mChessView = chessView;
	}

	@Override
	public void onClick(View v) {
		new AlertDialog.Builder(v.getContext())
				.setItems(piecesStyles, new OnItemClickListener())
				.show();
	}

	private class OnItemClickListener implements DialogInterface.OnClickListener{

		@Override
		public void onClick(DialogInterface dialog, int which) {
			Style.getDefaultStyle().setPiecesStyle(RUtil.getStaticInt(Style.class, piecesStyles[which]));
			game.reSetStyle();
			mChessView.invalidate();
		}
	}
}
