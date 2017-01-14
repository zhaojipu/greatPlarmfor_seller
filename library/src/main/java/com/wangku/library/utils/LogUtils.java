package com.wangku.library.utils;

import android.util.Log;

/**
 *
 * 内容摘要：日志打印工具类
 */
public class LogUtils {

	private LogUtils() {}

	private static boolean isDebug = true;

	public static void d(Class<?> classes, Object msg) {
		if (isDebug) {
			Log.d(classes.getSimpleName(), msg.toString());
		}
	}

	public static void d(Object msg) {
		if (isDebug) {
			Log.d("", msg.toString());
		}
	}

	public static void i(Class<?> classes, Object msg) {
		if (isDebug) {
			Log.i(classes.getSimpleName(), msg.toString());
		}
	}

	public static void v(Class<?> classes, Object msg) {
		if (isDebug) {
			Log.v(classes.getSimpleName(), msg.toString());
		}
	}

	public static void e(Class<?> classes, Object msg) {
		if (isDebug) {
			Log.e(classes.getSimpleName(), msg.toString());
		}
	}

	public static void e(String tag, Object msg) {
		if (isDebug) {
			Log.e(tag, msg.toString());
		}
	}
}
