package com.eshel.chess.chess.game;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.SparseArray;

/**
 * createBy Eshel
 * createTime: 2018/8/25 13:12
 * desc: TODO
 */
public class ResCache {

	private static SparseArray<Bitmap> res = new SparseArray<>();

	public static Bitmap getBitmap(Context context, int id){
		Bitmap bitmap = res.get(id);
		if(bitmap == null){
			bitmap = BitmapFactory.decodeResource(context.getResources(), id);
			res.put(id,bitmap);
		}
		return bitmap;
	}
}
