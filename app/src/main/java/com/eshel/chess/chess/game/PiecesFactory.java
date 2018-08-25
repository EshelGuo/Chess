package com.eshel.chess.chess.game;

import java.util.ArrayList;
import java.util.List;

/**
 * createBy Eshel
 * createTime: 2018/8/25 10:59
 * desc: TODO
 */
public class PiecesFactory {
	public static int id;
	/**
	 * 创建单个棋子
	 */
	public static Pieces createSignPieces(Type type, int x, int y){
		Pieces pieces = new Pieces(id++);
		pieces.type = type;
		pieces.x = x;
		pieces.y = y;
		pieces.resetStyle();
		return pieces;
	}

	public static List<Pieces> createAllPiecess(boolean selfIsRed){
		List<Pieces> piecesList = new ArrayList<>(32);

		int baseY = selfIsRed ? 0 : 11;
		piecesList.add(createSignPieces(Type.RED_JU,1,Math.abs(baseY - 1)));
		piecesList.add(createSignPieces(Type.RED_JU,9,Math.abs(baseY - 1)));
		piecesList.add(createSignPieces(Type.BLACK_JU,1,Math.abs(baseY - 10)));
		piecesList.add(createSignPieces(Type.BLACK_JU,9,Math.abs(baseY - 10)));

		piecesList.add(createSignPieces(Type.RED_MA,2,Math.abs(baseY - 1)));
		piecesList.add(createSignPieces(Type.RED_MA,8,Math.abs(baseY - 1)));
		piecesList.add(createSignPieces(Type.BLACK_MA,2,Math.abs(baseY - 10)));
		piecesList.add(createSignPieces(Type.BLACK_MA,8,Math.abs(baseY - 10)));

		piecesList.add(createSignPieces(Type.RED_XIANG,3,Math.abs(baseY - 1)));
		piecesList.add(createSignPieces(Type.RED_XIANG,7,Math.abs(baseY - 1)));
		piecesList.add(createSignPieces(Type.BLACK_XIANG,3,Math.abs(baseY - 10)));
		piecesList.add(createSignPieces(Type.BLACK_XIANG,7,Math.abs(baseY - 10)));

		piecesList.add(createSignPieces(Type.RED_SHI,4,Math.abs(baseY - 1)));
		piecesList.add(createSignPieces(Type.RED_SHI,6,Math.abs(baseY - 1)));
		piecesList.add(createSignPieces(Type.BLACK_SHI,4,Math.abs(baseY - 10)));
		piecesList.add(createSignPieces(Type.BLACK_SHI,6,Math.abs(baseY - 10)));

		piecesList.add(createSignPieces(Type.RED_SHUAI,5,Math.abs(baseY - 1)));
		piecesList.add(createSignPieces(Type.BLACK_JIANG,5,Math.abs(baseY - 10)));

		piecesList.add(createSignPieces(Type.RED_PAO,2,Math.abs(baseY - 3)));
		piecesList.add(createSignPieces(Type.RED_PAO,8,Math.abs(baseY - 3)));
		piecesList.add(createSignPieces(Type.BLACK_PAO,2,Math.abs(baseY - 8)));
		piecesList.add(createSignPieces(Type.BLACK_PAO,8,Math.abs(baseY - 8)));

		piecesList.add(createSignPieces(Type.RED_BING,1,Math.abs(baseY - 4)));
		piecesList.add(createSignPieces(Type.RED_BING,3,Math.abs(baseY - 4)));
		piecesList.add(createSignPieces(Type.RED_BING,5,Math.abs(baseY - 4)));
		piecesList.add(createSignPieces(Type.RED_BING,7,Math.abs(baseY - 4)));
		piecesList.add(createSignPieces(Type.RED_BING,9,Math.abs(baseY - 4)));

		piecesList.add(createSignPieces(Type.BLACK_ZU,1,Math.abs(baseY - 7)));
		piecesList.add(createSignPieces(Type.BLACK_ZU,3,Math.abs(baseY - 7)));
		piecesList.add(createSignPieces(Type.BLACK_ZU,5,Math.abs(baseY - 7)));
		piecesList.add(createSignPieces(Type.BLACK_ZU,7,Math.abs(baseY - 7)));
		piecesList.add(createSignPieces(Type.BLACK_ZU,9,Math.abs(baseY - 7)));

		return piecesList;
	}
}
