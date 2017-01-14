package com.wangku.library.utils;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

/**
 *
 * 内容摘要：存储设备工具类
 */
public class StorageUtils {
	public static final String SDCARD_ROOT = Environment
			.getExternalStorageDirectory().getAbsolutePath() + "/";
	public static final String FILE_ROOT = SDCARD_ROOT + "com.berchina.mobile/";
	public static final String ERROR_LOG = FILE_ROOT + "log";

	private static final long LOW_STORAGE_THRESHOLD = SizeUtils.MB_2_BYTE * 10;

	/**
	 * 当前的存储设备是否可写
	 *
	 * @return 可写
	 */
	public static boolean isSdCardWrittenable() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			return true;
		}
		return false;
	}

    /**
     * 得到sdcard可用内存
     *
     * @return
     */
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public static long getUsableSpace(File path) {
        if (path == null) {
            return -1;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            return path.getUsableSpace();
        } else {
            if (!path.exists()) {
                return 0;
            } else {
                final StatFs stats = new StatFs(path.getPath());
                return (long) stats.getBlockSize() * (long) stats.getAvailableBlocks();
            }
        }
    }

	/**
	 * 得到sdcard可用内存
	 *
	 * @return
	 */
	public static long getAvailableStorage() {
		String storageDirectory = null;
		storageDirectory = Environment.getExternalStorageDirectory().toString();

		try {
			StatFs stat = new StatFs(storageDirectory);
			long avaliableSize = ((long) stat.getAvailableBlocks() * (long) stat
					.getBlockSize());
			return avaliableSize;
		} catch (RuntimeException ex) {
			return 0;
		}
	}

	/**
	 * 检查当前是否可写入
	 *
	 * @return
	 */
	public static boolean checkAvailableStorage() {
		if (getAvailableStorage() < LOW_STORAGE_THRESHOLD) {
			return false;
		}
		return true;
	}

	/**
	 * sdcard是否准备就绪
	 *
	 * @return
	 */
	public static boolean isSDCardPresent() {
		return Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED);
	}

	/**
	 * 创建目录
	 *
	 * @throws IOException
	 */
	public static void mkdir() throws IOException {

		File file = new File(FILE_ROOT);
		if (!file.exists() || !file.isDirectory())
			file.mkdir();
	}

	/**
	 * 得到保存的路径
	 *
	 * @return
	 */
	public static String getSaveAbsolutePath() {
		try {
			mkdir();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return FILE_ROOT;
	}

	/**
	 * 传入字节数，返回具体的容量值
	 *
	 * @param size 传入的大小
	 * @return 具体的容量数
	 */
	public static String size(long size) {

		if (size / (1024 * 1024) > 0) {
			float tmpSize = (float) (size) / (float) (1024 * 1024);
			DecimalFormat df = new DecimalFormat("#.##");
			return "" + df.format(tmpSize) + "MB";
		} else if (size / 1024 > 0) {
			return "" + (size / (1024)) + "KB";
		} else
			return "" + size + "B";
	}

	/**
	 * 删除文件
	 *
	 * @param path
	 * @return
	 */
	public static boolean delete(File path) {

		boolean result = true;
		if (path.exists()) {
			if (path.isDirectory()) {
				for (File child : path.listFiles()) {
					result &= delete(child);
				}
				result &= path.delete(); // Delete empty directory.
			}
			if (path.isFile()) {
				result &= path.delete();
			}
			if (!result) {
				LogUtils.e("", "Delete failed;");
			}
			return result;
		} else {
			LogUtils.e("", "File does not exist.");
			return false;
		}
	}

	/**
	 * 文件是否存在
	 *
	 * @param path
	 * @return
	 */
	public static boolean isExistsFile(File path) {
		boolean result = false;
		if (path.exists()) {
			result = true;
		}
		return result;
	}



}
