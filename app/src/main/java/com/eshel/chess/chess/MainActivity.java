package com.eshel.chess.chess;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.eshel.chess.chess.game.ChessView;
import com.eshel.chess.chess.game.Color;
import com.eshel.chess.chess.game.Game;
import com.eshel.chess.chess.game.Rule;
import com.eshel.chess.chess.game.Style;

public class MainActivity extends AppCompatActivity {
	Game game;
	private ChessView mChessView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mChessView = findViewById(R.id.chess);
		game = new Game(
				new Rule.Builder()
					.setCamp(Color.RED)
					.canMoveAllColor(true)
					.setStyle(new Style(Style.STYLE_SKELETON, Style.STYLE_XQSTUDIO))
					.build());
		mChessView.bindToGame(game);
	}
}
