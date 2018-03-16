package br.com.poupex.teste.selenium.utils;

public abstract class ThreadUtil {
	
	public static void waitFor(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
