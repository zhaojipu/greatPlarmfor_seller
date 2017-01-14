package com.wangku.library.utils;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;


import com.wangku.library.Config;
import com.wangku.library.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.UUID;

/**
 *
 * 内容摘要：图片工具类
 */
public class PhotoUtils {

	/**
	 * 选择相册的图片
	 */
	public static void doPickPhotoFromGallery(Activity activity) {
		try {
			Intent intent = new Intent(
					Intent.ACTION_GET_CONTENT,
					android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			intent.setType("image/*");
			activity.startActivityForResult(
					Intent.createChooser(intent, "选择相册的图片"),
					Config.PICKDATA_FROM_GALLERY);
		} catch (Exception e1) {
			e1.printStackTrace();
			try {
				Intent intent = new Intent();
				intent.setType("image/*");
				intent.putExtra("return-data", true);
				intent.setAction(Intent.ACTION_GET_CONTENT);
				activity.startActivityForResult(intent,
						Config.PICKDATA_FROM_GALLERY);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}

	}

	/**
	 * 现在拍摄一张
	 */
	public static Uri doTakePhonto(Activity activity, Uri imageFileUri) {
		imageFileUri = activity.getContentResolver().insert(
				MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new ContentValues());
		if (imageFileUri != null) {
			Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			i.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageFileUri);
			if (IntentUtils.isIntentSafe(activity, i)) {
				activity.startActivityForResult(i, Config.PICKDATA_FROM_CAMERA);
			} else {
				ToastUtils.show(activity, R.string.dont_have_camera_app);
			}
		} else {
			ToastUtils.show(activity, R.string.cant_insert_album);
		}
		return imageFileUri;
	}


	/**
	 * 现在拍摄一张
	 */
	public static Uri doTakePhonto(Fragment fragment, Activity activity, Uri imageFileUri) {
		imageFileUri = activity.getContentResolver().insert(
				MediaStore.Images.Media.EXTERNAL_CONTENT_URI, new ContentValues());
		if (imageFileUri != null) {
			Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			i.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, imageFileUri);
			if (IntentUtils.isIntentSafe(activity, i)) {
				fragment.startActivityForResult(i, Config.PICKDATA_FROM_CAMERA);
			} else {
				ToastUtils.show(activity, R.string.dont_have_camera_app);
			}
		} else {
			ToastUtils.show(activity, R.string.cant_insert_album);
		}
		return imageFileUri;
	}

	public static Bitmap processImage(Context context, String path) {
		// 获取图片旋转角度
		int angle = BitmapUtils.readPictureDegree(path);
		Bitmap bmp = BitmapUtils.getSmallBitmap(context, path);
		// 如果图片产生旋转
		if (angle != 0) {
			Matrix m = new Matrix();
			int width = bmp.getWidth();
			int height = bmp.getHeight();
			m.setRotate(angle);
			bmp = Bitmap.createBitmap(bmp, 0, 0, width, height, m, true);
		}
		return bmp;
	}

	public static String writeStreamToFile(Bitmap bitmap) {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 60, stream);// (0-100)压缩文件
		InputStream photoInputStream = new ByteArrayInputStream(
				stream.toByteArray());
		String photoPath = IoUtils.IMAGE_FILE_ROOT
				+ UUID.randomUUID().toString() + ".jpeg";
		File uploadFile = new File(photoPath);
		IoUtils.writeStreamToFile(photoInputStream, uploadFile);
		return photoPath;
	}

	public static void saveBitmap2File(Bitmap bitmap, String fileName) {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 60, stream);// (0-100)压缩文件
		InputStream photoInputStream = new ByteArrayInputStream(
				stream.toByteArray());
		File uploadFile = new File(fileName);
		IoUtils.writeStreamToFile(photoInputStream, uploadFile);
	}

	public static String getProfilePath(Context context) {
		SharedPreferencesUtils preferences = SharedPreferencesUtils.getInstance(context).getInstance(
				context);
		String profilePath = IoUtils.IMAGE_FILE_ROOT + "profile_"
				+ preferences.getStringValue("") + ".jpeg";
		return profilePath;
	}
	public static String getPhonePath(Context context, String name) {
		String profilePath = IoUtils.IMAGE_FILE_ROOT + name
				 + ".jpeg";
		return profilePath;
	}
}
