package com.eshel.chess.chess.game;

/**
 * createBy Eshel
 * createTime: 2018/8/25 12:48
 * desc: 游戏规则
 */
public class Rule {
	public static final int JU = 1;
	public static final int MA = 2;
	public static final int XIANG = 3;
	public static final int SHI = 4;
	public static final int SHUAI = 5;
	public static final int PAO = 6;
	public static final int BING = 7;

	/**
	 * 自己的阵营
	 */
	private Color selfCamp;
	/**
	 * 当前的阵营
	 */
	private Color currentCamp;

	/**
	 * 能否移动所有阵营
	 */
	private boolean canMoveAllColor;

	public boolean canMoveAllColor() {
		return canMoveAllColor;
	}

	private Rule() {}

	public Color getCurrentCamp() {
		return currentCamp;
	}

	public Color getSelfCamp() {
		return selfCamp;
	}

	public void setCurrentCamp(Color currentCamp) {
		this.currentCamp = currentCamp;
	}

	Game mGame;

	public boolean canSelect(Pieces pieces){
		if(pieces != null && pieces.type != null){
			if(pieces.type.color == currentCamp)
				return true;
		}
		return false;
	}

//	Pieces copySrc;
//	Pieces copyDest;
	public boolean canMove(Pieces src, Pieces dest){

		if(src != null && dest != null){
			//点击空位不可移动
			if(src.type == null)
				return false;
			//对方阵营不可移动
			if(src.type.color != currentCamp)
				return false;
			//都是自己棋子不可吃子
			if(dest.type != null){
				if(dest.type.color == src.type.color){
					return false;
				}
			}

			if(currentCamp != selfCamp){
				mGame.switchY();
//				copySrc = src.copy(copySrc);
//				copyDest = dest.copy(copyDest);
//				copySrc.y = 11 - copySrc.y;
//				copyDest.y = 11 - copyDest.y;
//				src = copySrc;
//				dest = copyDest;
			}
			boolean canMove = false;
			switch (src.type.type){
				case JU:
					canMove = canMoveJU(src,dest);
					break;
				case MA:
					canMove = canMoveMa(src,dest);
					break;
				case XIANG:
					canMove = canMoveXiang(src,dest);
					break;
				case SHI:
					canMove = canMoveShi(src,dest);
					break;
				case SHUAI:
					canMove = canMoveShuai(src, dest);
					break;
				case PAO:
					canMove = canMovePao(src,dest);
					break;
				case BING:
					canMove = canMoveBing(src,dest);
					break;
			}

			if(currentCamp != selfCamp){
				mGame.switchY();
			}
			return canMove;
		}
		return false;
	}

	private boolean canMoveShuai(Pieces src, Pieces dest) {
		if(src.x == dest.x && Math.abs(src.y - dest.y) == 1 && dest.y <= 3)
			return true;
		if(src.y == dest.y && Math.abs(src.x - dest.x) == 1 && dest.x >= 4 && dest.x <=6)
			return true;
		return false;
	}

	private boolean canMoveShi(Pieces src, Pieces dest) {
		if(Math.abs(src.x - dest.x) == 1 && Math.abs(src.y - dest.y) == 1 && dest.y <= 3 && dest.x >= 4 && dest.x <= 6)
			return true;
		return false;
	}

	private boolean canMoveXiang(Pieces src, Pieces dest) {
		if(dest.y <= 5 && src.x - 2 == dest.x && src.y - 2 == dest.y && mGame.queryPiecesByXY(src.x - 1, src.y - 1) == null)
			return true;
		if(dest.y <= 5 && src.x + 2 == dest.x && src.y - 2 == dest.y && mGame.queryPiecesByXY(src.x + 1, src.y - 1) == null)
			return true;
		if(dest.y <= 5 && src.x + 2 == dest.x && src.y + 2 == dest.y && mGame.queryPiecesByXY(src.x + 1, src.y + 1) == null)
			return true;
		if(dest.y <= 5 && src.x - 2 == dest.x && src.y + 2 == dest.y && mGame.queryPiecesByXY(src.x - 1, src.y + 1) == null)
			return true;
		return false;
	}

	private boolean canMoveBing(Pieces src, Pieces dest) {
		if(src.y + 1 == dest.y && src.x == dest.x){
			return true;
		}

		if(src.y >= 6){
			if(src.y == dest.y && Math.abs(src.x - dest.x) == 1){
				return true;
			}
		}
		return false;
	}

	private boolean canMoveJU(Pieces src, Pieces dest){
		if(src.x == dest.x){
			Pieces hasPieces = getFirstPiecesByLineY(src, dest.y);
			if(hasPieces != null)
				return false;
			return true;
		}else if(src.y == dest.y){
			Pieces hasPieces = getFirstPiecesByLineX(src, dest.x);
			if(hasPieces != null)
				return false;
			return true;
		}
		return false;
	}

	private boolean canMovePao(Pieces src, Pieces dest){
		if(src.x == dest.x){
			Pieces frist = getFirstPiecesByLineY(src, dest.y);
			if(frist == null && dest.type == null)
				return true;
			else if(frist == null && dest.type != null)
				return false;
			Pieces second = getFirstPiecesByLineY(dest, src.y);
			if(frist == second && dest.type != null)
				return true;
		}else if(src.y == dest.y){
			Pieces frist = getFirstPiecesByLineX(src, dest.x);
			if(frist == null && dest.type == null)
				return true;
			else if(frist == null && dest.type != null)
				return false;
			Pieces second = getFirstPiecesByLineX(dest, src.x);
			if(second == frist && dest.type != null)
				return true;
		}
		return false;
	}

	private boolean canMoveMa(Pieces src, Pieces dest){
		Pieces obstacle;
		if((dest.x == src.x - 1 || dest.x == src.x + 1) && dest.y == src.y - 2){
			obstacle = mGame.queryPiecesByXY(src.x, src.y - 1);
			if(obstacle == null)
				return true;
		}

		if((dest.x == src.x - 1 || dest.x == src.x + 1) && dest.y == src.y + 2){
			obstacle = mGame.queryPiecesByXY(src.x, src.y + 1);
			if(obstacle == null)
				return true;
		}

		if(dest.x == src.x - 2 && (dest.y == src.y - 1 || dest.y == src.y + 1)){
			obstacle = mGame.queryPiecesByXY(src.x - 1, src.y);
			if(obstacle == null)
				return true;
		}

		if(dest.x == src.x + 2 && (dest.y == src.y - 1 || dest.y == src.y + 1)){
			obstacle = mGame.queryPiecesByXY(src.x + 1, src.y);
			if(obstacle == null)
				return true;
		}
		return false;
	}

	/**
	 * 获取直线上第一个棋子
	 */
	private Pieces getFirstPiecesByLineX(Pieces from, int toX){
		int dx = toX - from.x;
		if(dx == 0)
			return null;
		for (int i = 1; i < Math.abs(dx); i++) {
			int x;
			if(dx < 0){
				x = from.x - i;
			}else {
				x = from.x + i;
			}
			Pieces pieces = mGame.queryPiecesByXY(x, from.y);
			if(pieces != null)
				return pieces;
		}
		return null;
	}
	/**
	 * 获取直线上第一个棋子
	 */
	private Pieces getFirstPiecesByLineY(Pieces from, int toY){
		int dy = toY - from.y;
		if(dy == 0)
			return null;
		for (int i = 1; i < Math.abs(dy); i++) {
			int y;
			if(dy < 0){
				y = from.y - i;
			}else {
				y = from.y + i;
			}
			Pieces pieces = mGame.queryPiecesByXY(from.x, y);
			if(pieces != null)
				return pieces;
		}
		return null;
	}

	public static class Builder{
		private Rule rule;

		private Rule getRule(){
			if(rule == null)
				rule = new Rule();
			return rule;
		}

		public Builder setCamp(Color camp){
			getRule().selfCamp = camp;
			getRule().currentCamp = Color.RED;
			return this;
		}

		public Builder canMoveAllColor(boolean canMoveAllColor){
			getRule().canMoveAllColor = canMoveAllColor;
			return this;
		}

		public Builder setStyle(Style style){
			Style.setStyle(style);
			return this;
		}

		public Rule build(){
			return getRule();
		}
	}
}
