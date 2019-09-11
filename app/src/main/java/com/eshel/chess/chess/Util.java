package com.eshel.chess.chess;

import android.os.Looper;
import android.widget.Toast;

import java.util.Collection;

/**
 * createBy Eshel
 * createTime: 2018/8/25 09:45
 */
public class Util {

	public static boolean isEmpty(Collection collection){
		return collection == null || collection.isEmpty();
	}

	public static boolean isNotEmpty(Collection collection){
		return !isEmpty(collection);
	}
	public static int getScreenHeight(){
		return App.Context.getResources().getDisplayMetrics().heightPixels;
	}
	public static int getScreenWidth(){
		return App.Context.getResources().getDisplayMetrics().widthPixels;
	}
	public static void toast(final CharSequence text){
		if(Looper.myLooper() == Looper.getMainLooper())
			showToast(1,text);
		else
			App.Context.getHandler().post(new Runnable() {
				@Override
				public void run() {
					showToast(1,text);
				}
			});
	}
	private static Toast mToast;
	private static void showToast(int longorshort,CharSequence text){
		if(text == null)
			return;
		if(mToast == null) {
			synchronized (Toast.class) {
				if(mToast == null) {
					mToast = Toast.makeText(App.Context, text,
							longorshort == 0 ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG);
				}
			}
		}else {
			mToast.setDuration(longorshort == 0 ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG);
			mToast.setText(text);
		}
		mToast.show();
	}

	public static boolean isEmpty(Object[] array) {
		return array == null || array.length == 0;
	}

	public static boolean isNotEmpty(Object[] array){
		return !isEmpty(array);
	}
}
