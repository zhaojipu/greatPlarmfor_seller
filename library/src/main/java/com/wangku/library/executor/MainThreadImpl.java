package com.wangku.library.executor;

import android.os.Handler;
import android.os.Looper;


/**
 *
 * 内容摘要：主线程的实现类
 */
class MainThreadImpl implements MainThread {

	private Handler handler;

	private volatile static MainThreadImpl sInstance;

	private MainThreadImpl() {
		this.handler = new Handler(Looper.getMainLooper());
	}

	public static MainThreadImpl getInstance() {
		if (sInstance == null) {
			synchronized (MainThread.class) {
				if (sInstance == null) {
					sInstance = new MainThreadImpl();
				}
			}
		}
		return sInstance;
	}

	public void post(Runnable runnable) {
		handler.post(runnable);
	}
}
