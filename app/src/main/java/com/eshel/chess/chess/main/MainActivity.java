package com.eshel.chess.chess.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.eshel.chess.chess.R;
import com.eshel.chess.chess.game.ChessView;
import com.eshel.chess.chess.game.Color;
import com.eshel.chess.chess.game.Game;
import com.eshel.chess.chess.game.Pieces;
import com.eshel.chess.chess.game.Rule;
import com.eshel.chess.chess.game.Style;
import com.eshel.chess.chess.main.listener.ChangeChessboardStyleListener;
import com.eshel.chess.chess.main.listener.ChangePiecesStyleListener;
import com.eshel.chess.chess.main.listener.ConnectionServerListener;
import com.eshel.chess.chess.main.listener.CreateServerListener;
import com.eshel.chess.chess.main.listener.SearchIpsClickListener;
import com.eshel.chess.chess.socket.WaitMoveCallback;
import com.eshel.chess.chess.socket.tasks.ClientTask;
import com.eshel.chess.chess.socket.tasks.GameTask;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity implements GameTask.GameView {
	private ChessView mChessView;

	private String[] piecesStyles = new String[]{"STYLE_DELICATE", "STYLE_POLISH", "STYLE_MRSJ", "STYLE_MOVESKY", "STYLE_WOOD", "STYLE_XQSTUDIO", "STYLE_ZMBL"};
	private String[] Checkerboard = new String[]{"STYLE_WOOD", "STYLE_CANVAS", "STYLE_CLOUDS", "STYLE_DROPS", "STYLE_GREEN", "STYLE_MOVESKY", "STYLE_MRSJ", "STYLE_QIANHONG", "STYLE_SHEET", "STYLE_SKELETON", "STYLE_WHITE", "STYLE_XQSTUDIO", "STYLE_ZMBL"};
	private int i = 1;
	private View mBtnChangePiecesStyle;
	private View mBtnChangeChessboardStyle;
	private View mNewGame;
	private Button mBtnSearchIps;
	private Button mBtnCreateServer;
	private Button mBtnConnectionServer;
	private TextView mTvIp;
	private TextView mTvRoomName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		findId();

		initGame();

		initEvent();

	}

	private void initEvent() {
		mBtnChangePiecesStyle.setOnClickListener(new ChangePiecesStyleListener(piecesStyles, mChessView.getGame(), mChessView));
		mBtnChangeChessboardStyle.setOnClickListener(new ChangeChessboardStyleListener(Checkerboard, mChessView.getGame(), mChessView));
		mNewGame.setOnClickListener(new NewGameListener());
		mBtnSearchIps.setOnClickListener(new SearchIpsClickListener(this));
		mBtnCreateServer.setOnClickListener(new CreateServerListener(this));
		mBtnConnectionServer.setOnClickListener(new ConnectionServerListener());

		findViewById(R.id.btn5).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ClientTask.msg = "发送消息 -- " + i++;
			}
		});
	}

	private void findId() {
		mNewGame = findViewById(R.id.newGame);
		mChessView = findViewById(R.id.chess);
		mBtnChangePiecesStyle = findViewById(R.id.btn_change_pieces_style);
		mBtnChangeChessboardStyle = findViewById(R.id.btn_change_chessboard_style);
		mBtnSearchIps = findViewById(R.id.btn_search_ips);
		mBtnCreateServer = findViewById(R.id.btn_create_server);
		mBtnConnectionServer = findViewById(R.id.btn_connection_server);
		mTvIp = findViewById(R.id.tv_ip);
		mTvRoomName = findViewById(R.id.tv_room_name);
	}

	private void initGame() {
		Rule rule = new Rule.Builder()
				.setCamp(Color.RED)
				.canMoveAllColor(true)
				.setStyle(new Style(Style.STYLE_GREEN, Style.STYLE_XQSTUDIO))
				.build();
		mChessView.bindToGame(new Game(rule));
//		mChessView.setVisibility(View.INVISIBLE);
	}

	@Override
	public void connectionFailed(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
	}

	@Override
	public void initRoomName(String name) {
		mTvRoomName.setText(name);
	}

	@Override
	public void initServerIP(String ip) {
		mTvIp.setText(String.format("游戏创建成功: %s", ip));
	}

	@Override
	public void initGame(boolean first) {
		Rule rule = new Rule.Builder()
				.setCamp(first ? Color.RED : Color.BLACK)
				.canMoveAllColor(true)
				.canMoveOtherColor(false)
				.setStyle(new Style(Style.STYLE_GREEN, Style.STYLE_XQSTUDIO))
				.build();
		mChessView.bindToGame(new Game(rule));
		mChessView.getGame().newGame();
	}

	@Override
	public void waitMove(WaitMoveCallback callback) {
		mChessView.moveCallback(callback);
	}

	@Override
	public void movePieces(int fromX, int fromY, int toX, int toY) {
		fromX = 10 - fromX;
		fromY = 11 - fromY;

		toX = 10 - toX;
		toY = 11 - toY;

		Pieces pieces = mChessView.getGame().queryPiecesByXY(fromX, fromY);
		mChessView.movePieces(pieces, toX, toY);
	}

	private class NewGameListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			mChessView.getGame().newGame();
		}
	}
}
