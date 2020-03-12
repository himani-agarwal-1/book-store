package com.nagarro.bookstore.model;

/**
 * @author himaniagarwal The Class ResponseMessage. It is the response returned
 *         by APIs when an exception occurs
 */
public class ResponseMessage {

	private String message = null;
	private String status = null;

	public ResponseMessage(String message, String status) {
		super();
		this.message = message;
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
