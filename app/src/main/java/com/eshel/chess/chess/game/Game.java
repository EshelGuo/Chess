package com.eshel.chess.chess.game;

import android.graphics.Canvas;

import java.util.List;

/**
 * createBy Eshel
 * createTime: 2018/8/25 12:47
 * desc: TODO
 */
public class Game implements LoopHandler<Canvas> {
	ChessView mChessView;
	private Rule mRule;
	private List<Pieces> mAllPiecess;
	private Pieces emptySel;
	private Pieces emptySel2;
	private Pieces emptySel1;
	public Game(Rule rule) {
		mRule = rule;
		mRule.mGame = this;
		mAllPiecess = PiecesFactory.createAllPiecess(rule.getSelfCamp() == Color.RED);
		emptySel = PiecesFactory.createSignPieces(null, -1, -1);
		emptySel.isSelected = true;
		emptySel.resetStyle();

		emptySel2 = PiecesFactory.createSignPieces(null,-1, -1);
		emptySel2.isSelected = true;
		emptySel2.resetStyle();

		emptySel1 = PiecesFactory.createSignPieces(null,-1, -1);
		emptySel1.isSelected = true;
		emptySel1.resetStyle();
	}

	public void loopAllPiecess(Canvas canvas, LoopHandler<Canvas> loopHandler){
		if(mAllPiecess != null){
			for (Pieces piecess : mAllPiecess) {
				if(loopHandler != null)
					loopHandler.loop(piecess,canvas);
			}
		}
		if(loopHandler != null){
			loopHandler.loop(emptySel, canvas);
			loopHandler.loop(emptySel1, canvas);
			loopHandler.loop(emptySel2, canvas);
		}
	}

	public Pieces queryByXY(int x, int y){
		if(x == -1 || y == -1)
			return null;
		for (Pieces pieces : mAllPiecess) {
			if(pieces.x == x && pieces.y == y)
				return pieces;
		}
		getMoveEmpty().x = x;
		getMoveEmpty().y = y;
		return getMoveEmpty();
	}

	public Pieces queryPiecesByXY(int x, int y){
		if(x == -1 || y == -1)
			return null;
		for (Pieces pieces : mAllPiecess) {
			if(pieces.x == x && pieces.y == y)
				return pieces;
		}
		return null;
	}

	public void removePieces(Pieces current) {
		if(current == getMoveEmpty()){
			current.x = -1;
			current.y = -1;
		}
		mAllPiecess.remove(current);
	}

	public boolean isEmpty(Pieces pieces){
		if(pieces == getMoveEmpty()){
			return true;
		}
		return false;
	}

	public Rule getRule() {
		return mRule;
	}

	/**
	 * 切换为对方走子
	 */
	public void switchCamp(){
		if(mRule.canMoveAllColor()){
			mRule.setCurrentCamp(mRule.getCurrentCamp().switchColor());
		}
	}

	public void switchY(){
		for (Pieces pieces : mAllPiecess) {
			pieces.y = 11 - pieces.y;
		}
		if(getMoveEmpty() != null)
			getMoveEmpty().y = 11 - getMoveEmpty().y;
	}

	public Pieces getCurrentEmpty(){
		if(mRule.getCurrentCamp() == mRule.getSelfCamp()){
			return emptySel1;
		}else {
			return emptySel2;
		}
	}

	public void resetUnCurrentEmpty(){
		if(mRule.getCurrentCamp() == mRule.getSelfCamp()){
			emptySel2.x = -1;
			emptySel2.y = -1;
		}else {
			emptySel1.x = -1;
			emptySel1.y = -1;
		}
	}

	public Pieces getMoveEmpty(){
		return emptySel;
	}

	public void reSetStyle(){
		emptySel.resetStyle();
		emptySel1.resetStyle();
		emptySel2.resetStyle();
		loopAllPiecess(null,this);
	}

	public Game newGame(){
		Game game = new Game(new Rule.Builder()
				.setCamp(Color.RED)
				.canMoveAllColor(mRule.canMoveAllColor())
				.setStyle(Style.getDefaultStyle())
				.build());
		mChessView.reStart(game);
		mChessView.invalidate();
		return game;
	}

	@Override
	public void loop(Pieces pieces, Canvas canvas) {
		pieces.resetStyle();
	}
}
