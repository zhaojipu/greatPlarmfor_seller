package com.wangku.library.executor;

/**
 *
 * 内容摘要：线程提供者
 */
public class ExecutorProvider {

	public static Executor provideExecutor() {
		return ThreadExecutor.getInstance();
	}

	public static MainThread provideMainThread() {
		return MainThreadImpl.getInstance();
	}
}
