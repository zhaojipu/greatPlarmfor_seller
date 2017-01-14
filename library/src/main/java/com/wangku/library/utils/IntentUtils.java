package com.wangku.library.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;

import java.util.List;


public class IntentUtils {

	private IntentUtils() {}
	
	/**
	 * 跳转到对应的Activity
	 */
	public static <T> void showActivity(Activity activity, Class<T> activityCls) {
		showActivity(activity, activityCls, null);
	}

	public static <T> void showActivity(Activity activity, Class<T> activityCls, Bundle extras) {
		Intent intent = new Intent(activity, activityCls);
		if (null == extras) {
			activity.startActivity(intent);
		} else {
			intent.putExtras(extras);
			activity.startActivity(intent);
		}
	}

	/**
	 * 跳转到对应的Activity返回的时候可以接受到结果
	 * 
	 * @param activityCls 对应的Activity.class
	 * @param requestCode 请求码
	 */
	public static <T> void showActivityForResult(Activity activity, Class<T> activityCls,
												 int requestCode) {
		showActivityForResult(activity, activityCls, null, requestCode);
	}

	public static <T> void showActivityForResult(Activity activity, Class<T> activityCls,
												 Bundle extras, int requestCode) {
		Intent intent = new Intent(activity, activityCls);
		if (null == extras) {
			activity.startActivityForResult(intent, requestCode);
		} else {
			intent.putExtras(extras);
			activity.startActivityForResult(intent, requestCode);
		}
	}
	
	/**
	 * 跳转到对应的Activity by Flags的过滤
	 * 
	 * @param activityCls
	 * @param flags
	 */
	public static <T> void showActivityByFlags(Activity activity, Class<T> activityCls, int flags) {
		Intent intent = new Intent(activity, activityCls);
		intent.setFlags(flags);
		activity.startActivity(intent);
	}
	
	/**
	 * 跳转到网络设置界面
	 * 
	 * @param context 上下文对象
	 */
	public static void showNetworkSetting(Context context) {
		Intent intent = new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS);
		context.startActivity(intent);
	}

	/**
	 * 跳转到拨打电话界面
	 *
	 * @param context
	 * @param phoneNumber 传入的电话号码
	 */
	public static void intent2Call(Context context, String phoneNumber) {
		Intent callIntent = new Intent(Intent.ACTION_DIAL,
				Uri.parse("tel:" + phoneNumber));
		context.startActivity(callIntent);
	}
	/**
	 * 跳转到浏览器界面
	 *
	 * @param context
	 * @param url
	 */
	public static void intent2Browser(Context context, String url) {
		Uri uri = Uri.parse(url);
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		context.startActivity(intent);
	}
	public static boolean isIntentSafe(Activity activity, Intent intent) {
		PackageManager packageManager = activity.getPackageManager();
		List<ResolveInfo> activities = packageManager.queryIntentActivities(intent, 0);
		return activities.size() > 0;
	}

//    public static void intentToWaitPay(Context context) {
//        Intent intent = new Intent(context, OrderManageActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//        Bundle extras = new Bundle();
//        extras.putString(ShopConstants.ORDER_STATUS, OrderStatus.WAIT_PAY.getValue());
//        intent.putExtras(extras);
//        context.startActivity(intent);
//    }
}
