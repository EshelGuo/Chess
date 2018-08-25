package com.eshel.chess.chess.game;

import com.eshel.chess.chess.R;

/**
 * createBy Eshel
 * createTime: 2018/8/25 11:07
 * desc: 棋盘棋子Style
 */
public class Style {
	private static Style mStyle;

	public static final int STYLE_WOOD = 1;
	public static final int STYLE_CANVAS = 2;
	public static final int STYLE_CLOUDS = 3;
	public static final int STYLE_DROPS = 4;
	public static final int STYLE_GREEN = 5;
	public static final int STYLE_MOVESKY = 6;
	public static final int STYLE_MRSJ = 7;
	public static final int STYLE_QIANHONG = 8;
	public static final int STYLE_SHEET = 9;
	public static final int STYLE_SKELETON = 10;
	public static final int STYLE_WHITE = 11;
	public static final int STYLE_XQSTUDIO = 12;
	public static final int STYLE_ZMBL = 13;
	// 1 - 13 棋盘 Style
	// 14 15 以及下边注释是棋子 Style
	public static final int STYLE_DELICATE = 14;
	public static final int STYLE_POLISH = 15;
/*	public static final int STYLE_MOVESKY = 1;
	public static final int STYLE_MRSJ = 1;
	public static final int STYLE_WOOD = 1;
	public static final int STYLE_XQSTUDIO = 1;
	public static final int STYLE_ZMBL = 1;*/

	/**
	 * 棋盘样式
	 * @see #getCheckerboardResByStyle()
	 */
	private int checkerboardStyle;
	/**
	 * 棋子样式
	 * @see #getOOSRes()
	 * @see #getBingRes(Color, boolean)
	 * @see #getJiangRes(Color, boolean)
	 * @see #getPaoRes(Color, boolean)
	 * @see #getJURes(Color, boolean)
	 * @see #getMARes(Color, boolean)
	 * @see #getXiangRes(Color, boolean)
	 * @see #getShiRes(Color, boolean)
	 */
	private int piecesStyle;

	public Style(int checkerboardStyle, int piecesStyle) {
		this.checkerboardStyle = checkerboardStyle;
		this.piecesStyle = piecesStyle;
	}

	public int getCheckerboardStyle() {
		return checkerboardStyle;
	}

	public int getPiecesStyle() {
		return piecesStyle;
	}

	public void setCheckerboardStyle(int checkerboardStyle) {
		this.checkerboardStyle = checkerboardStyle;
	}

	public void setPiecesStyle(int piecesStyle) {
		this.piecesStyle = piecesStyle;
	}

	public static Style getDefaultStyle() {
		if(mStyle == null)
			mStyle = new Style(STYLE_WOOD,STYLE_WOOD);
		return mStyle;
	}

	public static void setStyle(Style style) {
		mStyle = style;
	}

	public static int getCheckerboardResByStyle(){
		int style = getDefaultStyle().checkerboardStyle;
		switch (style){
			case STYLE_WOOD:
				return R.mipmap.wood;
			case STYLE_CANVAS:
				return R.mipmap.canvas;
			case STYLE_CLOUDS:
				return R.mipmap.clouds;
			case STYLE_DROPS:
				return R.mipmap.drops;
			case STYLE_GREEN:
				return R.mipmap.green;
			case STYLE_MOVESKY:
				return R.mipmap.movesky;
			case STYLE_MRSJ:
				return R.mipmap.mrsj;
			case STYLE_QIANHONG:
				return R.mipmap.qianhong;
			case STYLE_SHEET:
				return R.mipmap.sheet;
			case STYLE_SKELETON:
				return R.mipmap.skeleton;
			case STYLE_WHITE:
				return R.mipmap.white;
			case STYLE_XQSTUDIO:
				return R.mipmap.xqstudio;
			case STYLE_ZMBL:
				return R.mipmap.zmbl;
		}
		return 0;
	}

	public static int getPiecesResByStyle(Pieces pieces){
		if(Pieces.isNull(pieces))
			return getOORes();
		if(pieces.type == null && pieces.isSelected){
			return getOOSRes();
		}
		switch (pieces.type){
			case RED_PAO:
			case BLACK_PAO:
				return getPaoRes(pieces.type.color,pieces.isSelected);
			case RED_BING:
			case BLACK_ZU:
				return getBingRes(pieces.type.color,pieces.isSelected);

			case BLACK_JU:
			case RED_JU:
				return getJURes(pieces.type.color,pieces.isSelected);
			case RED_MA:
			case BLACK_MA:
				return getMARes(pieces.type.color,pieces.isSelected);
			case RED_XIANG:
			case BLACK_XIANG:
				return getXiangRes(pieces.type.color,pieces.isSelected);
			case RED_SHI:
			case BLACK_SHI:
				return getShiRes(pieces.type.color,pieces.isSelected);
			case RED_SHUAI:
			case BLACK_JIANG:
				return getJiangRes(pieces.type.color,pieces.isSelected);
		}
		return 0;
	}

	private static int getOORes(){
		switch (getDefaultStyle().piecesStyle) {
			case STYLE_DELICATE:
				return R.mipmap.delicate_oo;
			case STYLE_POLISH:
				return R.mipmap.polish_oo;
			case STYLE_MOVESKY:
				return R.mipmap.movesky_oo;
			case STYLE_MRSJ:
				return R.mipmap.mrsj_oo;
			case STYLE_WOOD:
				return R.mipmap.wood_oo;
			case STYLE_XQSTUDIO:
				return R.mipmap.xqstudio_oo;
			case STYLE_ZMBL:
				return R.mipmap.zmbl_oo;
		}
		return 0;
	}

	private static int getOOSRes(){
		switch (getDefaultStyle().piecesStyle) {
			case STYLE_DELICATE:
				return R.mipmap.delicate_oos;
			case STYLE_POLISH:
				return R.mipmap.polish_oos;
			case STYLE_MOVESKY:
				return R.mipmap.movesky_oos;
			case STYLE_MRSJ:
				return R.mipmap.mrsj_oos;
			case STYLE_WOOD:
				return R.mipmap.wood_oos;
			case STYLE_XQSTUDIO:
				return R.mipmap.xqstudio_oos;
			case STYLE_ZMBL:
				return R.mipmap.zmbl_oos;
		}
		return 0;
	}

	private static int getJURes(Color color, boolean selected){
		switch (getDefaultStyle().piecesStyle) {
			case STYLE_DELICATE:
				return color == Color.RED ? (selected?R.mipmap.delicate_rrs:R.mipmap.delicate_rr) : (selected?R.mipmap.delicate_brs:R.mipmap.delicate_br);
			case STYLE_POLISH:
				return color == Color.RED ? (selected ? R.mipmap.polish_rrs : R.mipmap.polish_rr) : (selected ? R.mipmap.polish_brs : R.mipmap.polish_br) ;
			case STYLE_MOVESKY:
				return color == Color.RED ? (selected ? R.mipmap.movesky_rrs : R.mipmap.movesky_rr) : (selected ? R.mipmap.movesky_brs : R.mipmap.movesky_br) ;
			case STYLE_MRSJ:
				return color == Color.RED ? (selected ? R.mipmap.mrsj_rrs : R.mipmap.mrsj_rr) : (selected ? R.mipmap.mrsj_brs : R.mipmap.mrsj_br) ;
			case STYLE_WOOD:
				return color == Color.RED ? (selected ? R.mipmap.wood_rrs : R.mipmap.wood_rr) : (selected ? R.mipmap.wood_brs : R.mipmap.wood_br) ;
			case STYLE_XQSTUDIO:
				return color == Color.RED ? (selected ? R.mipmap.xqstudio_rrs : R.mipmap.xqstudio_rr) : (selected ? R.mipmap.xqstudio_brs : R.mipmap.xqstudio_br) ;
			case STYLE_ZMBL:
				return color == Color.RED ? (selected ? R.mipmap.zmbl_rrs : R.mipmap.zmbl_rr) : (selected ? R.mipmap.zmbl_brs : R.mipmap.zmbl_br) ;
		}
		return 0;
	}

	private static int getMARes(Color color, boolean selected){
		switch (getDefaultStyle().piecesStyle) {
			case STYLE_DELICATE:
				return color == Color.RED ? (selected?R.mipmap.delicate_rns:R.mipmap.delicate_rn) : (selected?R.mipmap.delicate_bns:R.mipmap.delicate_bn);
			case STYLE_POLISH:
				return color == Color.RED ? (selected ? R.mipmap.polish_rns : R.mipmap.polish_rn) : (selected ? R.mipmap.polish_bns : R.mipmap.polish_bn) ;
			case STYLE_MOVESKY:
				return color == Color.RED ? (selected ? R.mipmap.movesky_rns : R.mipmap.movesky_rn) : (selected ? R.mipmap.movesky_bns : R.mipmap.movesky_bn) ;
			case STYLE_MRSJ:
				return color == Color.RED ? (selected ? R.mipmap.mrsj_rns : R.mipmap.mrsj_rn) : (selected ? R.mipmap.mrsj_bns : R.mipmap.mrsj_bn) ;
			case STYLE_WOOD:
				return color == Color.RED ? (selected ? R.mipmap.wood_rns : R.mipmap.wood_rn) : (selected ? R.mipmap.wood_bns : R.mipmap.wood_bn) ;
			case STYLE_XQSTUDIO:
				return color == Color.RED ? (selected ? R.mipmap.xqstudio_rns : R.mipmap.xqstudio_rn) : (selected ? R.mipmap.xqstudio_bns : R.mipmap.xqstudio_bn) ;
			case STYLE_ZMBL:
				return color == Color.RED ? (selected ? R.mipmap.zmbl_rns : R.mipmap.zmbl_rn) : (selected ? R.mipmap.zmbl_bns : R.mipmap.zmbl_bn) ;
		}
		return 0;
	}


	private static int getXiangRes(Color color, boolean selected){
		switch (getDefaultStyle().piecesStyle) {
			case STYLE_DELICATE:
				return color == Color.RED ? (selected?R.mipmap.delicate_rbs:R.mipmap.delicate_rb) : (selected?R.mipmap.delicate_bbs:R.mipmap.delicate_bb);
			case STYLE_POLISH:
				return color == Color.RED ? (selected ? R.mipmap.polish_rbs : R.mipmap.polish_rb) : (selected ? R.mipmap.polish_bbs : R.mipmap.polish_bb) ;
			case STYLE_MOVESKY:
				return color == Color.RED ? (selected ? R.mipmap.movesky_rbs : R.mipmap.movesky_rb) : (selected ? R.mipmap.movesky_bbs : R.mipmap.movesky_bb) ;
			case STYLE_MRSJ:
				return color == Color.RED ? (selected ? R.mipmap.mrsj_rbs : R.mipmap.mrsj_rb) : (selected ? R.mipmap.mrsj_bbs : R.mipmap.mrsj_bb) ;
			case STYLE_WOOD:
				return color == Color.RED ? (selected ? R.mipmap.wood_rbs : R.mipmap.wood_rb) : (selected ? R.mipmap.wood_bbs : R.mipmap.wood_bb) ;
			case STYLE_XQSTUDIO:
				return color == Color.RED ? (selected ? R.mipmap.xqstudio_rbs : R.mipmap.xqstudio_rb) : (selected ? R.mipmap.xqstudio_bbs : R.mipmap.xqstudio_bb) ;
			case STYLE_ZMBL:
				return color == Color.RED ? (selected ? R.mipmap.zmbl_rbs : R.mipmap.zmbl_rb) : (selected ? R.mipmap.zmbl_bbs : R.mipmap.zmbl_bb) ;
		}
		return 0;
	}

	private static int getShiRes(Color color, boolean selected){
		switch (getDefaultStyle().piecesStyle) {
			case STYLE_DELICATE:
				return color == Color.RED ? (selected?R.mipmap.delicate_ras:R.mipmap.delicate_ra) : (selected?R.mipmap.delicate_bas:R.mipmap.delicate_ba);
			case STYLE_POLISH:
				return color == Color.RED ? (selected ? R.mipmap.polish_ras : R.mipmap.polish_ra) : (selected ? R.mipmap.polish_bas : R.mipmap.polish_ba) ;
			case STYLE_MOVESKY:
				return color == Color.RED ? (selected ? R.mipmap.movesky_ras : R.mipmap.movesky_ra) : (selected ? R.mipmap.movesky_bas : R.mipmap.movesky_ba) ;
			case STYLE_MRSJ:
				return color == Color.RED ? (selected ? R.mipmap.mrsj_ras : R.mipmap.mrsj_ra) : (selected ? R.mipmap.mrsj_bas : R.mipmap.mrsj_ba) ;
			case STYLE_WOOD:
				return color == Color.RED ? (selected ? R.mipmap.wood_ras : R.mipmap.wood_ra) : (selected ? R.mipmap.wood_bas : R.mipmap.wood_ba) ;
			case STYLE_XQSTUDIO:
				return color == Color.RED ? (selected ? R.mipmap.xqstudio_ras : R.mipmap.xqstudio_ra) : (selected ? R.mipmap.xqstudio_bas : R.mipmap.xqstudio_ba) ;
			case STYLE_ZMBL:
				return color == Color.RED ? (selected ? R.mipmap.zmbl_ras : R.mipmap.zmbl_ra) : (selected ? R.mipmap.zmbl_bas : R.mipmap.zmbl_ba) ;
		}
		return 0;
	}

	private static int getJiangRes(Color color, boolean selected){
		switch (getDefaultStyle().piecesStyle) {
			case STYLE_DELICATE:
				return color == Color.RED ? (selected?R.mipmap.delicate_rks:R.mipmap.delicate_rk) : (selected?R.mipmap.delicate_bks:R.mipmap.delicate_bk);
			case STYLE_POLISH:
				return color == Color.RED ? (selected ? R.mipmap.polish_rks : R.mipmap.polish_rk) : (selected ? R.mipmap.polish_bks : R.mipmap.polish_bk) ;
			case STYLE_MOVESKY:
				return color == Color.RED ? (selected ? R.mipmap.movesky_rks : R.mipmap.movesky_rk) : (selected ? R.mipmap.movesky_bks : R.mipmap.movesky_bk) ;
			case STYLE_MRSJ:
				return color == Color.RED ? (selected ? R.mipmap.mrsj_rks : R.mipmap.mrsj_rk) : (selected ? R.mipmap.mrsj_bks : R.mipmap.mrsj_bk) ;
			case STYLE_WOOD:
				return color == Color.RED ? (selected ? R.mipmap.wood_rks : R.mipmap.wood_rk) : (selected ? R.mipmap.wood_bks : R.mipmap.wood_bk) ;
			case STYLE_XQSTUDIO:
				return color == Color.RED ? (selected ? R.mipmap.xqstudio_rks : R.mipmap.xqstudio_rk) : (selected ? R.mipmap.xqstudio_bks : R.mipmap.xqstudio_bk) ;
			case STYLE_ZMBL:
				return color == Color.RED ? (selected ? R.mipmap.zmbl_rks : R.mipmap.zmbl_rk) : (selected ? R.mipmap.zmbl_bks : R.mipmap.zmbl_bk) ;
		}
		return 0;
	}

	private static int getPaoRes(Color color, boolean selected){
		switch (getDefaultStyle().piecesStyle) {
			case STYLE_DELICATE:
				return color == Color.RED ? (selected?R.mipmap.delicate_rcs:R.mipmap.delicate_rc) : (selected?R.mipmap.delicate_bcs:R.mipmap.delicate_bc);
			case STYLE_POLISH:
				return color == Color.RED ? (selected ? R.mipmap.polish_rcs : R.mipmap.polish_rc) : (selected ? R.mipmap.polish_bcs : R.mipmap.polish_bc) ;
			case STYLE_MOVESKY:
				return color == Color.RED ? (selected ? R.mipmap.movesky_rcs : R.mipmap.movesky_rc) : (selected ? R.mipmap.movesky_bcs : R.mipmap.movesky_bc) ;
			case STYLE_MRSJ:
				return color == Color.RED ? (selected ? R.mipmap.mrsj_rcs : R.mipmap.mrsj_rc) : (selected ? R.mipmap.mrsj_bcs : R.mipmap.mrsj_bc) ;
			case STYLE_WOOD:
				return color == Color.RED ? (selected ? R.mipmap.wood_rcs : R.mipmap.wood_rc) : (selected ? R.mipmap.wood_bcs : R.mipmap.wood_bc) ;
			case STYLE_XQSTUDIO:
				return color == Color.RED ? (selected ? R.mipmap.xqstudio_rcs : R.mipmap.xqstudio_rc) : (selected ? R.mipmap.xqstudio_bcs : R.mipmap.xqstudio_bc) ;
			case STYLE_ZMBL:
				return color == Color.RED ? (selected ? R.mipmap.zmbl_rcs : R.mipmap.zmbl_rc) : (selected ? R.mipmap.zmbl_bcs : R.mipmap.zmbl_bc) ;
		}
		return 0;
	}

	private static int getBingRes(Color color, boolean selected){
		switch (getDefaultStyle().piecesStyle) {
			case STYLE_DELICATE:
				return color == Color.RED ? (selected?R.mipmap.delicate_rps:R.mipmap.delicate_rp) : (selected?R.mipmap.delicate_bps:R.mipmap.delicate_bp);
			case STYLE_POLISH:
				return color == Color.RED ? (selected ? R.mipmap.polish_rps : R.mipmap.polish_rp) : (selected ? R.mipmap.polish_bps : R.mipmap.polish_bp) ;
			case STYLE_MOVESKY:
				return color == Color.RED ? (selected ? R.mipmap.movesky_rps : R.mipmap.movesky_rp) : (selected ? R.mipmap.movesky_bps : R.mipmap.movesky_bp) ;
			case STYLE_MRSJ:
				return color == Color.RED ? (selected ? R.mipmap.mrsj_rps : R.mipmap.mrsj_rp) : (selected ? R.mipmap.mrsj_bps : R.mipmap.mrsj_bp) ;
			case STYLE_WOOD:
				return color == Color.RED ? (selected ? R.mipmap.wood_rps : R.mipmap.wood_rp) : (selected ? R.mipmap.wood_bps : R.mipmap.wood_bp) ;
			case STYLE_XQSTUDIO:
				return color == Color.RED ? (selected ? R.mipmap.xqstudio_rps : R.mipmap.xqstudio_rp) : (selected ? R.mipmap.xqstudio_bps : R.mipmap.xqstudio_bp) ;
			case STYLE_ZMBL:
				return color == Color.RED ? (selected ? R.mipmap.zmbl_rps : R.mipmap.zmbl_rp) : (selected ? R.mipmap.zmbl_bps : R.mipmap.zmbl_bp) ;
		}
		return 0;
	}
}
