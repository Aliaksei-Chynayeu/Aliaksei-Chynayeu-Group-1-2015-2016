package com.epam.minsk.exception;

public class DAOException extends Exception {
	private static final long serialVersionUID = -2620597233038322137L;

	public DAOException() {

	}

	public DAOException(String message) {
		super(message);
	}

	public DAOException(Throwable cause) {
		super(cause);
	}

	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

}
