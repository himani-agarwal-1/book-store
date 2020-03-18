package com.nagarro.bookstore.model;

/**
 * @author himaniagarwal
 * The Class OrderResponse
 * response for the book order service.
 * 
 */
public class OrderResponse {

	private String title;
	private String message;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
