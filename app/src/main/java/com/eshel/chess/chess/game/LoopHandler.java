package com.eshel.chess.chess.game;

/**
 * createBy Eshel
 * createTime: 2018/8/25 12:59
 */
public interface LoopHandler<T> {
	void loop(Pieces pieces, T t);
}
