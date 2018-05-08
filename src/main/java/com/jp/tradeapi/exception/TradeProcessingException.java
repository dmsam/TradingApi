package com.jp.tradeapi.exception;

/**
 * Custom exception class for trade processing
 * 
 * @author doma.samson
 *
 */
public class TradeProcessingException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @param message
	 */
	public TradeProcessingException(String message) {
		super(message);
	}

	/**
	 * 
	 * @param message
	 * @param throwable
	 */
	public TradeProcessingException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
