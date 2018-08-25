package com.eshel.chess.chess.game;

/**
 * createBy Eshel
 * createTime: 2018/8/25 11:27
 * desc: 阵营, 红方 黑方
 */
public enum Color {
	RED,BLACK;

	/**
	 * 切换阵营
	 */
	public Color switchColor(){
		if(RED == this)
			return BLACK;
		return RED;
	}
}
