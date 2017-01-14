package com.wangku.library.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * 内容摘要：屏幕工具类
 */
public class ScreenUtils {

	private ScreenUtils() {}

	private static DisplayMetrics displayMetrics;

	/**
	 * 获取当前屏幕高度和宽度
	 */
	public static int[] getSceenInfo(Context context) {
		int[] screen = new int[2];
		if (!NotNull.isNotNull(displayMetrics)) {
			displayMetrics = context.getResources().getDisplayMetrics();
		}
		screen[0] = displayMetrics.widthPixels;
		screen[1] = displayMetrics.heightPixels;
		return screen;
	}

	public static int getScreenWidth(Context context) {
		return getSceenInfo(context)[0];
	}

	public static int getScreenHeight(Context context) {
		return getSceenInfo(context)[1];
	}

	public static int getDensityDpi(Context context) {
		if (!NotNull.isNotNull(displayMetrics)) {
			displayMetrics = context.getResources().getDisplayMetrics();
		}
		int densityDpi = displayMetrics.densityDpi;
		return densityDpi;
	}

	public static int dipToPixels(Context context, int dip) {
		final float SCALE = context.getResources().getDisplayMetrics().density;
		float valueDips = dip;
		int valuePixels = (int) (valueDips * SCALE + 0.5f);
		return valuePixels;
	}

	public static float pixelsToDip(Context context, int Pixels) {
		final float SCALE = context.getResources().getDisplayMetrics().density;
		float dips = Pixels / SCALE;
		return dips;
	}
}
