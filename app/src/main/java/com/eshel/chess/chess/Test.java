package com.eshel.chess.chess;

import java.io.File;

/**
 * createBy Eshel
 * createTime: 2018/8/25 09:20
 * desc: TODO
 */
public class Test {

	public static void main(String args[]){
		String path = "C:\\Users\\Administrator\\Desktop\\象棋编程素材\\IMAGES_S";
		File assets = new File(path);
		for (File parent : assets.listFiles()) {
			String name = parent.getName();
			if(!parent.isFile()){
				System.out.println(name);
			}
		}
	}

	/*private static int demo(Color color, boolean selected){
		switch (getDefaultStyle().piecesStyle) {
			case STYLE_DELICATE:
				return color == Color.RED ? (selected?R.mipmap.delicate_rdemos:R.mipmap.delicate_rdemo) : (selected?R.mipmap.delicate_bdemos:R.mipmap.delicate_bdemo);
			case STYLE_POLISH:
				return color == Color.RED ? (selected ? R.mipmap.polish_rdemos : R.mipmap.polish_rdemo) : (selected ? R.mipmap.polish_bdemos : R.mipmap.polish_bdemo) ;
			case STYLE_MOVESKY:
				return color == Color.RED ? (selected ? R.mipmap.movesky_rdemos : R.mipmap.movesky_rdemo) : (selected ? R.mipmap.movesky_bdemos : R.mipmap.movesky_bdemo) ;
			case STYLE_MRSJ:
				return color == Color.RED ? (selected ? R.mipmap.mrsj_rdemos : R.mipmap.mrsj_rdemo) : (selected ? R.mipmap.mrsj_bdemos : R.mipmap.mrsj_bdemo) ;
			case STYLE_WOOD:
				return color == Color.RED ? (selected ? R.mipmap.wood_rdemos : R.mipmap.wood_rdemo) : (selected ? R.mipmap.wood_bdemos : R.mipmap.wood_bdemo) ;
			case STYLE_XQSTUDIO:
				return color == Color.RED ? (selected ? R.mipmap.xqstudio_rdemos : R.mipmap.xqstudio_rdemo) : (selected ? R.mipmap.xqstudio_bdemos : R.mipmap.xqstudio_bdemo) ;
			case STYLE_ZMBL:
				return color == Color.RED ? (selected ? R.mipmap.zmbl_rdemos : R.mipmap.zmbl_rdemo) : (selected ? R.mipmap.zmbl_bdemos : R.mipmap.zmbl_bdemo) ;
		}
		return 0;
	}*/
}
