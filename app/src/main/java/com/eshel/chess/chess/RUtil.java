package com.eshel.chess.chess;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * createBy Eshel
 * createTime: 2018/8/26 05:32
 * desc: TODO
 */
public class RUtil {

	public static int getMipmapByName(String name){
		return getStaticInt(R.mipmap.class,name);
	}

	public static int getStaticInt(Class clazz, String name){
		try {
			Field field = clazz.getField(name);
			if(!Modifier.isPublic(field.getModifiers()))
				field.setAccessible(true);
			return (int) field.get(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
