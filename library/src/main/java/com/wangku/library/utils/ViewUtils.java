package com.wangku.library.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.text.InputType;
import android.text.Selection;
import android.text.Spannable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 *
 * 内容摘要：View工具类
 */
public class ViewUtils {

	private ViewUtils() {}


	/**
	 * 设置输入密码的可见性
	 * @param isVisible--true：可见；false：不可见
	 * @param etPassword--密码输入文本框
     */
	public static void isPswVisiable(boolean isVisible, EditText etPassword) {

		if (isVisible) {
			//设置密码不可见
			etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
		} else {
			//设置密码可见，如果只设置TYPE_TEXT_VARIATION_PASSWORD则无效
			etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
		}
		// 切换后将光标置于末尾
		CharSequence password = etPassword.getText();
		if (password instanceof Spannable) {
			Spannable spanText = (Spannable) password;
			Selection.setSelection(spanText, password.length());
		}
	}
	/**
	 * 隐藏软键盘方法
	 *
	 * @param context
	 * @param activity
	 */
	public static void hiddenKeyboard(Context context, Activity activity) {
		try {
			InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);

			if (activity.getCurrentFocus() != null) {
				if (activity.getCurrentFocus().getWindowToken() != null) {
					imm.hideSoftInputFromWindow(activity
									.getCurrentFocus().getWindowToken(),
							InputMethodManager.HIDE_NOT_ALWAYS);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 点击关闭软键盘
	 *
	 * @param context
	 * @param activity
	 * @param motionEvent Judge by motion event
	 */
	public static void hiddenKeyBoardByClick(Context context, Activity activity, MotionEvent motionEvent) {
		if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
			hiddenKeyboard(context, activity);
		}
	}

	/**
	 * 转换 dp to pixels
	 *
	 * @param context
	 * @param dpValue
	 * @return pixels
	 */
	public static int dip2px(Context context, float dpValue) {
		return (int) Math.ceil(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue,
				context.getResources().getDisplayMetrics()));
	}

	/**
	 * 转换 pixels to dp
	 *
	 * @param context
	 * @param pxValue
	 * @return dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 转换 dp to pixels
	 *
	 * @param context
	 * @param dpValue
	 * @return pixels
	 */
	public static float dp2px(Context context, float dpValue) {
		float scale = context.getResources().getDisplayMetrics().density;
		return (int) Math.ceil(dpValue * scale);
	}

	/**
	 * Expand a view which has already collapsed
	 *
	 * @param v
	 */
	public static void expandViews(final View v) {
		v.measure(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		final int targtetHeight = v.getMeasuredHeight();

		v.getLayoutParams().height = 0;
		v.setVisibility(View.VISIBLE);
		Animation a = new Animation() {
			@Override
			protected void applyTransformation(float interpolatedTime, Transformation t) {
				v.getLayoutParams().height = interpolatedTime == 1
						? RelativeLayout.LayoutParams.WRAP_CONTENT
						: (int) (targtetHeight * interpolatedTime);
				v.requestLayout();
			}

			@Override
			public boolean willChangeBounds() {
				return true;
			}
		};

		// 1dp/ms
		a.setDuration((int) (targtetHeight / v.getContext().getResources().getDisplayMetrics().density));
		v.startAnimation(a);
	}

	/**
	 * Collapse a view which has already expanded
	 *
	 * @param v
	 */
	public static void collapseViews(final View v) {
		final int initialHeight = v.getMeasuredHeight();

		Animation a = new Animation() {
			@Override
			protected void applyTransformation(float interpolatedTime, Transformation t) {
				if (interpolatedTime == 1) {
					v.setVisibility(View.GONE);
				} else {
					v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
					v.requestLayout();
				}
			}

			@Override
			public boolean willChangeBounds() {
				return true;
			}
		};

		// 1dp/ms
		a.setDuration((int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density) * 1);
		v.startAnimation(a);
	}

	/**
	 * Set the activity to be full screen
	 *
	 * @param activity
	 * @param isFullScreen
	 */
	public static void setActivityFullScreen(Activity activity,
			boolean isFullScreen) {
		if (isFullScreen) {
			activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
			activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
		} else {
			activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
			activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		}
	}

	/**
	 * Set activity to be portrait
	 * @param activity
	 * @param isPortrait
	 */
	public static void setActivityPortraitOrientation(Activity activity,
			boolean isPortrait) {
		if (isPortrait) {
			activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		} else {
			activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
		}
	}

	/**
	 * Lock the screen orientation as the current state
	 * @param activity
	 */
	public static void lockScreenOrientation(Activity activity) {
		if (activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		}
		if (activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
			activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		}
	}

	/**
	 * Unlock the screen orientation
	 * @param activity
	 */
	public static void unlockScreenOrientation(Activity activity) {
		activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
	}

	/**
	 * Get height of the device
	 * @param context
	 * @return
	 */
	public static int getDeviceHeight(Context context) {
		WindowManager manager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		return manager.getDefaultDisplay().getHeight();
	}

	/**
	 * Get height of status bar
	 * @param activity
	 * @return
	 */
	public static int getStatusBarHeight(Activity activity) {
		Rect frame = new Rect();
		activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
		return frame.top;
	}

	/**
	 * Get height of the top
	 * @param activity
	 * @return
	 */
	public static int getTopBarHeight(Activity activity) {
		return activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
	}

	public static void addViewToLayout(LayoutInflater inflater,
									   int resId, ViewGroup targetView) {
		View mContentView = inflater.inflate(resId, null);
		RelativeLayout.LayoutParams mLayoutParams = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		mContentView.setLayoutParams(mLayoutParams);
		if (null != targetView) {
			targetView.removeAllViews();
			targetView.addView(mContentView);
		}
	}
}
