package org.akhil.messenger.exception;

public class DataNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1725371166729392644L;

	public DataNotFoundException(String message){
		super(message);
	}
}
