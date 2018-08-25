package com.eshel.chess.chess.game;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import com.eshel.chess.chess.Util;

/**
 * createBy Eshel
 * createTime: 2018/8/25 09:07
 * desc: 中国象棋棋盘
 */
public class ChessView extends View implements LoopHandler<Canvas>, ValueAnimator.AnimatorUpdateListener {

	private Bitmap checkerboard;
	private Rect cbSrc;
	private Rect cbDest;
	private Paint paint;
	private Location mLocation;

	private Pieces current;
	private Pieces last;

	public ChessView(Context context) {
		this(context,null);
	}

	public ChessView(Context context, @Nullable AttributeSet attrs) {
		this(context,attrs,0);
	}

	public ChessView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initAll();
		setClickable(true);
	}

	private void initAll() {
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.BLACK);

		height = width = Util.getScreenWidth();
		mLocation = new Location(width);
		height = mLocation.getHeight();
		width = mLocation.getWidth();
	}
	private Game game;
	public void bindToGame(Game game){
		this.game = game;
		game.mChessView = this;
	}

	/**
	 * 开始新游戏
	 * @param game
	 */
	public void reStart(Game game){
		initAll();

		mLastPoint = new Point();
		mCurrentPoint = new Point();
		mMovePoint = new Point();
		animIsShowing = false;
		va = null;
		current = null;
		last = null;

		bindToGame(game);
	}

	int width,height;
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec,heightMeasureSpec);
		setMeasuredDimension(width, height);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		initCheckerboard();

		drawCheckerboard(canvas);
		drawGame(canvas);
		drawAnim(last, canvas);
	}

	private void drawAnim(Pieces last, Canvas canvas) {
		if(last != null)
			loop(last, canvas);
	}

	private void drawGame(Canvas canvas) {
		if(game != null){
			game.loopAllPiecess(canvas,this);
		}
	}

	private void drawCheckerboard(Canvas canvas) {
		canvas.drawBitmap(checkerboard,cbSrc,cbDest,paint);
	}

	private int checkerboardRes;
	private void initCheckerboard(){
		int checkerboardRes = Style.getCheckerboardResByStyle();
		if(this.checkerboardRes == checkerboardRes)
			return;

		this.checkerboardRes = checkerboardRes;
		checkerboard = BitmapFactory.decodeResource(getResources(), checkerboardRes);

		cbSrc = new Rect(0,0,checkerboard.getWidth(),checkerboard.getHeight());
		cbDest = new Rect(0,0,width,height);
	}

	@Override
	public void loop(Pieces pieces, Canvas canvas) {
		if(pieces == game.getCurrentEmpty() && pieces.x != -1 && pieces.y != -1){
			Log.d("","");
		}
		Bitmap bitmap = ResCache.getBitmap(getContext(), pieces.resId);
		Rect rect = mLocation.getPiecesL(pieces);
		if(rect != null) {
			if(mMovePoint.x != 0 && mMovePoint.y != 0 && last == pieces){
				rect.left = mMovePoint.x;
				rect.top = mMovePoint.y;
				rect.right = rect.left + mLocation.getSize();
				rect.bottom = rect.top + mLocation.getSize();
			}
			canvas.drawBitmap(bitmap, null, rect, paint);
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if(animIsShowing)
			return false;
		float x = event.getX();
		float y = event.getY();
		Point unLocation = mLocation.getUnLocation(x, y);
		Pieces pieces = game.queryByXY(unLocation.x, unLocation.y);
		switch (event.getAction()){
			case MotionEvent.ACTION_DOWN:
				if(pieces != null) {
					current = pieces;
					current.setSelect(true);
				} else
					return false;
				invalidate();
				break;
			case MotionEvent.ACTION_MOVE:
				if(current != null && current == pieces){
					return true;
				}else {
					if(current != null) {
						current.setSelect(false);
						current = null;
					}
					invalidate();
					return false;
				}
			case MotionEvent.ACTION_UP:
				if(current == null)
					return false;
				if(last == null) {
					if (!game.getRule().canSelect(current)) {
						current.setSelect(false);
						current = null;
					} else {
						last = current;
						current = null;
					}
				} else {
					if (current != null) {
						if(last == current){
							current.setSelect(false);
							last = null;
							current = null;
						}else {
							if (game.getRule().canMove(last, current))
								moveLastToCurrent();
							else {
								if (game.isEmpty(current)) {
									current.setSelect(false);
									last.setSelect(false);
									last = null;
									current = null;
								} else {
									last.setSelect(false);
									if(!game.getRule().canSelect(current)) {
										current.setSelect(false);
									}else {
										last = current;
									}
									current = null;
								}
							}
						}
					}
				}
				invalidate();
				break;
		}
		return true;
	}

	Point mLastPoint = new Point();
	Point mCurrentPoint = new Point();
	Point mMovePoint = new Point();
	private void moveLastToCurrent() {
		mLocation.getLocaton(mLastPoint, last.x, last.y);
		mLocation.getLocaton(mCurrentPoint, current.x, current.y);
		game.getCurrentEmpty().x = last.x;
		game.getCurrentEmpty().y = last.y;
		game.resetUnCurrentEmpty();
		invalidate();
		startAnim();
	}

	ValueAnimator va;
	boolean animIsShowing;
	private void startAnim(){
		animIsShowing = true;
		if(va == null) {
			va = ValueAnimator.ofFloat(0, 1);
			va.setDuration(300);
			va.addUpdateListener(this);
			va.setInterpolator(new LinearInterpolator());
			va.addListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					mMovePoint.x = 0;
					mMovePoint.y = 0;

					last.isSelected = true;
					last.x = current.x;
					last.y = current.y;

					game.removePieces(current);
					current = null;
					invalidate();
					animIsShowing = false;
					game.switchCamp();
				}
			});
		}
		va.start();
	}

	@Override
	public void onAnimationUpdate(ValueAnimator animation) {
		float scale = (float) animation.getAnimatedValue();
		mMovePoint.x = (int) ((mCurrentPoint.x - mLastPoint.x) * scale + mLastPoint.x);
		mMovePoint.y = (int) ((mCurrentPoint.y - mLastPoint.y) * scale + mLastPoint.y);
		invalidate();
	}

}
