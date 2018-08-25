package com.eshel.chess.chess.game;

/**
 * createBy Eshel
 * createTime: 2018/8/25 11:26
 * desc: TODO
 */
public enum  Type{

	RED_JU(Color.RED,Rule.JU),
	RED_MA(Color.RED,Rule.MA),
	RED_XIANG(Color.RED,Rule.XIANG),
	RED_SHI(Color.RED,Rule.SHI),
	RED_SHUAI(Color.RED,Rule.SHUAI),
	RED_PAO(Color.RED,Rule.PAO),
	RED_BING(Color.RED,Rule.BING),

	BLACK_JU(Color.BLACK, Rule.JU),
	BLACK_MA(Color.BLACK, Rule.MA),
	BLACK_XIANG(Color.BLACK, Rule.XIANG),
	BLACK_SHI(Color.BLACK, Rule.SHI),
	BLACK_JIANG(Color.BLACK, Rule.SHUAI),
	BLACK_PAO(Color.BLACK, Rule.PAO),
	BLACK_ZU(Color.BLACK, Rule.BING);

	public Color color;
	public int type;

	Type(Color color, int type) {
		this.type = type;
		this.color = color;
	}
}
