package com.eshel.chess.chess.game;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * createBy Eshel
 * createTime: 2018/8/25 10:16
 * desc: 棋子位置类
 */
public class Location {
	private final int BOUNDARY = 28;
	private final int WIDTH = 377;
	private final int HEIGHT = 417;
	private final int SIZE = 40;

	private static final int MIN_X = 1;
	private static final int MIN_Y = 1;
	private static final int MAX_X = 9;
	private static final int MAX_Y = 10;

	private int size;
	private int boundary;

	private int width;
	private int height;
	private Point mPoint;
	private Rect mRect;
	private int piecesSize;

	public Location(int width) {
		this.width = width;

		height = width * HEIGHT / WIDTH;
		size = SIZE * width / WIDTH;
		boundary = BOUNDARY * width / WIDTH;
		this.piecesSize = size;

		mPoint = new Point();
		mRect = new Rect();
	}

	public Location(int width, int piecesSize) {
		this(width);
		this.piecesSize = piecesSize;
	}

	/**
	 * 根据象棋坐标获取真实位置
	 * @param x 左下为1 右下为9
	 * @param y 左下为1 左上为10
	 */
	private Point getLocation(int x, int y) {
		checkXY(x, y);

		x -= 1;
		y -= 1;
		y = MAX_Y - y;
		y -= 1;

		mPoint.x = size * x +boundary;
		mPoint.y = size * y + boundary;
		return mPoint;
	}

	public void getLocaton(Point point, int x, int y){
		Point location = getLocation(x, y);
		point.x = location.x - piecesSize/2;
		point.y = location.y - piecesSize/2;
	}

	public Point getUnLocation(float x, float y){
		return getUnLocation((int)x,(int)y);
	}

	/**
	 * 根据真实坐标获取象棋位置
	 */
	public Point getUnLocation(int x, int y){
		int width = x - boundary;
		if(width <= 0){
			if(Math.abs(width) <= piecesSize/2)
				mPoint.x = 1;
			else {
				mPoint.x = -1;
			}
		}else {
			int excessX = width % piecesSize;
			mPoint.x = width / piecesSize + 1 + (excessX > piecesSize / 2 ? 1 : 0);
		}

		int height = this.height - y - boundary;
		if(height <= 0){
			if(Math.abs(height) <= piecesSize/2)
				mPoint.y = 1;
			else
				mPoint.y = -1;
		}else {
			int excessY = height % piecesSize;
			mPoint.y = height / piecesSize + 1 + (excessY > piecesSize/2 ? 1 : 0);
		}
		return mPoint;
	}

	private Rect getPiecesLocation(int x, int y){
		try{
		    checkXY(x,y);
		} catch (Exception e){
			return null;
		}
		Point location = getLocation(x, y);

		mRect.left = location.x - piecesSize/2;
		mRect.right = location.x + piecesSize/2;
		mRect.top = location.y - piecesSize/2;
		mRect.bottom = location.y + piecesSize/2;

		return mRect;
	}

	public Rect getPiecesL(Pieces pieces) {
		return getPiecesLocation(pieces.x, pieces.y);
	}

	public Rect getPiecesBitSrc(Bitmap bitmap) {
		mRect.left = 0;
		mRect.top = 0;
		mRect.right = bitmap.getWidth();
		mRect.bottom = bitmap.getHeight();
		return mRect;
	}

	private void checkXY(int x, int y) {
		if (x < MIN_X || x > MAX_X || y < MIN_Y || y > MAX_Y) {
			throw new ChessException("棋子位置错误");
		}
	}

	public int getSize() {
		return size;
	}

	public int getBoundary() {
		return boundary;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
