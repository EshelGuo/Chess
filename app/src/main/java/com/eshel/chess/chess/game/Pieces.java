package com.eshel.chess.chess.game;

/**
 * createBy Eshel
 * createTime: 2018/8/25 10:52
 * desc: 棋子
 */
public class Pieces {
	public int id;

	public int x;
	public int y;

	public int resId;

	public boolean isSelected;
	public void setSelect(boolean isSelected){
		this.isSelected = isSelected;
		resetStyle();
	}

	public Type type;

	public Pieces(int id) {
		this.id = id;
	}

	public void resetStyle(){
		this.resId = Style.getPiecesResByStyle(this);
	}

	public static boolean isNull(Pieces pieces){
		if(pieces == null/* || pieces.x < 0 || pieces.y < 0 */|| (pieces.type == null && !pieces.isSelected))
			return true;
		return false;
	}

	public Pieces copy(){
		return copy(null);
	}

	public Pieces copy(Pieces pieces){
		if(pieces == null)
			pieces = new Pieces(PiecesFactory.id++);
		pieces.x = x;
		pieces.y = y;
		pieces.type = type;
		pieces.isSelected = isSelected;
		pieces.resId = resId;
		return pieces;
	}
}
