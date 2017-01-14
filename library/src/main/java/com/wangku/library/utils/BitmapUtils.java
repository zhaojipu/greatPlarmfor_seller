package com.wangku.library.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;

import java.io.IOException;

/**
 *
 * 内容摘要：
 */
public class BitmapUtils {

	private static final int COMMON_WIDTH = 720;

	/**
	 * Calculate an inSampleSize for use in a {@link BitmapFactory.Options}
	 * object when decoding bitmaps using the decode* methods from
	 * {@link BitmapFactory}. This implementation calculates the closest
	 * inSampleSize that will result in the final decoded bitmap having a width
	 * and height equal to or larger than the requested width and height. This
	 * implementation does not ensure a power of 2 is returned for inSampleSize
	 * which can be faster when decoding but results in a larger bitmap which
	 * isn't as useful for caching purposes.
	 *
	 * @param options
	 *            An options object with out* params already populated (run
	 *            through a decode* method with inJustDecodeBounds==true
	 * @param reqWidth
	 *            The requested width of the resulting bitmap
	 * @param reqHeight
	 *            The requested height of the resulting bitmap
	 * @return The value to be used for inSampleSize
	 */
	public static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		// Raw height and width of image
		reqWidth = COMMON_WIDTH;
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (width > reqWidth) {
			inSampleSize = (int) Math.ceil((float) height / (float) reqWidth);
		}
		return inSampleSize;
	}

	/**
	 * @Description: TODO(读取照片exif信息中的旋转角度)
	 * @author: 许业滨
	 * @param: path(照片路径)
	 * @return: int (角度)
	 */
	public static int readPictureDegree(String path) {
		int degree = 0;
		try {
			ExifInterface exifInterface = new ExifInterface(path);
			int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
					ExifInterface.ORIENTATION_NORMAL);
			switch (orientation) {
				case ExifInterface.ORIENTATION_ROTATE_90:
					degree = 90;
					break;
				case ExifInterface.ORIENTATION_ROTATE_180:
					degree = 180;
					break;
				case ExifInterface.ORIENTATION_ROTATE_270:
					degree = 270;
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return degree;
	}

	/**
	 * @Title: getSmallBitmap
	 * @Description: TODO(根据路径获得图片并压缩，返回bitmap用于显示)
	 * @author: 许业滨
	 * @param: @param filePath
	 * @return: Bitmap
	 */
	public static Bitmap getSmallBitmap(Context context, String filePath) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filePath, options);

		// 获取屏幕宽高
		SharedPreferencesUtils preferences = SharedPreferencesUtils.getInstance(context);
		int screenWidth = preferences.getWidth();
		int screenHeight = preferences.getHeight();

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, screenWidth, screenHeight);
		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(filePath, options);
	}
}
