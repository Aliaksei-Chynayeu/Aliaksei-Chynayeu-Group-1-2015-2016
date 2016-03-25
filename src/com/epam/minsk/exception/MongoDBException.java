package com.epam.minsk.exception;

/**
 * Custom exception for MongoDB
 * @author Dzina_Andreyeva
 *
 */
public class MongoDBException extends Exception {
	  
	public MongoDBException() { 
		super(); 
	}
	
	public MongoDBException(String message) { 
		super(message); 
	}
	  
	public MongoDBException(String message, Throwable cause) { 
		super(message, cause); 
	}
	
	public MongoDBException(Throwable cause) { 
		super(cause); 
	}

}
