package com.eshel.chess.chess.socket;

/**
 * createBy guoshiwen
 * <br>createTime: 2019/9/11 20:49
 * <br>desc: TODO
 */
public interface WaitMoveCallback {
	void move(int fromX, int fromY, int toX, int toY);
}
