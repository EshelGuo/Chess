package com.eshel.chess.chess;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.eshel.chess.chess.game.ChessView;
import com.eshel.chess.chess.game.Color;
import com.eshel.chess.chess.game.Game;
import com.eshel.chess.chess.game.Rule;
import com.eshel.chess.chess.game.Style;

public class MainActivity extends AppCompatActivity {
	Game game;
	private ChessView mChessView;

    private String[] piecesStyles = new String[]{"STYLE_DELICATE", "STYLE_POLISH", "STYLE_MRSJ", "STYLE_MOVESKY", "STYLE_WOOD", "STYLE_XQSTUDIO", "STYLE_ZMBL"};
    private String[] Checkerboard = new String[]{"STYLE_WOOD", "STYLE_CANVAS", "STYLE_CLOUDS", "STYLE_DROPS", "STYLE_GREEN", "STYLE_MOVESKY", "STYLE_MRSJ", "STYLE_QIANHONG", "STYLE_SHEET", "STYLE_SKELETON", "STYLE_WHITE", "STYLE_XQSTUDIO", "STYLE_ZMBL"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mChessView = findViewById(R.id.chess);
		game = new Game(
				new Rule.Builder()
					.setCamp(Color.RED)
					.canMoveAllColor(true)
					.setStyle(new Style(Style.STYLE_GREEN, Style.STYLE_XQSTUDIO))
					.build());
		mChessView.bindToGame(game);
		findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new AlertDialog.Builder(v.getContext())
					.setItems(piecesStyles, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Style.getDefaultStyle().setPiecesStyle(RUtil.getStaticInt(Style.class, piecesStyles[which]));
							game.reSetStyle();
							mChessView.invalidate();
						}
					}).show();
			}
		});

		findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				new AlertDialog.Builder(v.getContext())
					.setItems(Checkerboard, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Style.getDefaultStyle().setCheckerboardStyle(RUtil.getStaticInt(Style.class, Checkerboard[which]));
							game.reSetStyle();
							mChessView.invalidate();
						}
					}).show();
			}
		});

		findViewById(R.id.newGame).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				game = game.newGame();
			}
		});
	}
}
