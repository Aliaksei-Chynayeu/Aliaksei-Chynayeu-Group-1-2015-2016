package com.epam.minsk.creators;

/**
 * @author Dzina_Andreyeva
 * Runs thread which creates list with objects
 */
public class ThreadCreator implements Runnable {

	public void run() {
		ObjectCreator.create();		
	}

}
