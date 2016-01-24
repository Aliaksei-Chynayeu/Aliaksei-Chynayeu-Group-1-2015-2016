package com.epam.minsk.runner;

import com.epam.minsk.creators.ThreadCreator;

public class Runner {

	public static void main(String[] args) {
		Thread thread1 = new Thread(new ThreadCreator());
		thread1.start();
		
		Thread thread2 = new Thread(new ThreadCreator());
		thread2.start();

	}

}
