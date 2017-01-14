package com.wangku.library.utils;

import android.database.Cursor;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 内容摘要：IO输入输出工具类
 */
public class IoUtils {

	public static final String SDCARD_ROOT = Environment
			.getExternalStorageDirectory().getAbsolutePath() + "/";
	public static final String CCB_FILE_ROOT = SDCARD_ROOT + "GreatPlatform/";
	public static final String IMAGE_FILE_ROOT = CCB_FILE_ROOT + "images/";

	private static final int BUFFER_SIZE = 32 * 1024; // 32 KB

	private IoUtils() {}

	public static void copyStream(InputStream is, OutputStream os)
			throws IOException {
		byte[] bytes = new byte[BUFFER_SIZE];
		while (true) {
			int count = is.read(bytes, 0, BUFFER_SIZE);
			if (count == -1) {
				break;
			}
			os.write(bytes, 0, count);
		}
	}

	public static void readFile2Stream(String fileName, InputStream is) {
		OutputStream os;
		try {
			os = new FileOutputStream(new File(fileName));
			copyStream(is, os);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void closeSilently(Closeable closeable) {
		try {
			closeable.close();
		} catch (Exception e) {
			// Do nothing
		}
	}

	public static byte[] stream2Bytes(InputStream is) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		byte[] bytes = new byte[BUFFER_SIZE];
		int len = 0;
		while ((len = is.read(bytes)) != -1) {
			bos.write(bytes, 0, len);
		}
		bos.close();
		is.close();
		return bos.toByteArray();
	}

	public static String stream2String(InputStream is) throws IOException {
		byte[] bytes = stream2Bytes(is);
		return new String(bytes);
	}

	public static void closeQuietly(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (Throwable e) {
			}
		}
	}

	public static void closeQuietly(Cursor cursor) {
		if (cursor != null) {
			try {
				cursor.close();
			} catch (Throwable e) {
			}
		}
	}

	public static void writeStreamToFile(InputStream is, File file) {
		try {
			imageFileMkdir();
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			copyStream(is, fileOutputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void imageFileMkdir() throws IOException {
		File ccnfFile = new File(CCB_FILE_ROOT);
		if (!ccnfFile.exists() || !ccnfFile.isDirectory())
			ccnfFile.mkdir();
		File imageFile = new File(IMAGE_FILE_ROOT);
		if (!imageFile.exists() || !imageFile.isDirectory())
			imageFile.mkdir();
	}
}