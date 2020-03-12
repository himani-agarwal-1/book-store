package com.nagarro.bookstore.exception;

import com.nagarro.bookstore.model.ResponseMessage;

/**
 * @author himaniagarwal BookStoreException : Custom exception class for book
 *         store API.
 */
public class BookStoreException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7849658330608644805L;
	private ResponseMessage responseMessage;

	/**
	 * Instantiates a new bookStoreException.
	 *
	 * @param responseMessage
	 *            the ResponseMessage
	 */
	public BookStoreException(ResponseMessage responseMessage) {
		super(responseMessage.getMessage());
		this.responseMessage = responseMessage;

	}

	public ResponseMessage getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(ResponseMessage responseMessage) {
		this.responseMessage = responseMessage;
	}

}
