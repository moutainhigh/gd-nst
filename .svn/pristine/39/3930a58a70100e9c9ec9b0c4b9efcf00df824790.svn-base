package cn.gdeng.nst.util.web.api;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 单列线程池
 * @author xiaojun
 */
public class ThreadPoolSingleton {
	
	private volatile static ExecutorService singletonThreadPoolInstance;

	public static ExecutorService getSingletonThreadPoolInstance() {
		if (singletonThreadPoolInstance == null) {
			synchronized (ThreadPoolSingleton.class) {
				if (singletonThreadPoolInstance == null) {
					singletonThreadPoolInstance = Executors.newCachedThreadPool();
				}
			}
		}
		return singletonThreadPoolInstance;
	}

}
