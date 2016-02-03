package com.epam.minsk.runner;




public class Runner {

	public static void main(String[] args) {
		Object obj = new Object();
		Thread thread1 = new ThreadForRun(obj);
		Thread thread2 = new ThreadForRun(obj);
		thread1.start();
		thread2.start();
	}
}
