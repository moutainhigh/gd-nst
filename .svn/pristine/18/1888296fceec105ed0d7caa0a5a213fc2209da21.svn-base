package com.gudeng.commerce.bi.server;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppMain {


	private volatile static boolean running = true;
	public static final String SHUTDOWN_HOOK_KEY = "dubbo.shutdown.hook";
	private static ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-cache.xml");

	public static void main(String[] args) {
		try {
			System.out.println("=========="+System.getProperty(SHUTDOWN_HOOK_KEY));
			if ("true".equals(System.getProperty(SHUTDOWN_HOOK_KEY))) {
				Runtime.getRuntime().addShutdownHook(new Thread() {
					public void run() {
						try {
							if (context != null) {
								System.out.println("testtrset");
								context.stop();
								context.close();
								context = null;
							}
							System.out.println("dubbo " + AppMain.class.getSimpleName() + " stopped!");
						} catch (Throwable t) {
							System.out.println("dubbo error " + getClass().getSimpleName() + " stopped!");
						}
						synchronized (AppMain.class) {
							running = false;
							AppMain.class.notify();
						}
					}
				});
			}
			context.start();
			System.out.println("dubbo start.....");
		} catch (RuntimeException e) {
			e.printStackTrace();
			System.exit(1);
		}
		synchronized (AppMain.class) {
			while (running) {
				try {
					System.out.println("dubboo hold....");
					AppMain.class.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void contextStop() {
		try {
			if (context != null) {
				context.stop();
				context.close();
				context = null;
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
