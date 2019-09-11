package com.eshel.chess.chess;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by EshelGuo on 2017/6/12.
 */

public class SteamUtils {
	/**
	 * 将流转换为字符串
	 * @param is
	 * @return
	 */
	public static String streamToString(InputStream is){
		ByteArrayOutputStream bos = null;
		String result = null;
		try {
			bos = new ByteArrayOutputStream();
			int len;
			byte[] buf = new byte[1024<<3];
			while((len = is.read(buf)) != -1){
				bos.write(buf,0,len);
			}
			result = bos.toString();//"UTF-8"
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeStream(bos);
		}
		return result;
	}

	/**
	 * 关流工具方法
	 * @param closeables 所有可以关闭的 Closeable
	 */
	public static void closeStream(Closeable ... closeables){
		if(closeables != null)
			for (Closeable closeable : closeables) {
				if(closeable != null){
					try {
						closeable.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
	}
}
