package com.nagarro.bookstore.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.client.RestClientException;

import com.nagarro.bookstore.exception.BookStoreException;
import com.nagarro.bookstore.model.ResponseMessage;

/**
 * @author himaniagarwal Exception handler advice handles Exception and maps
 *         response of API in Response message
 */
@ControllerAdvice
public class ExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

	/**
	 * Handles MethodArgumentNotValidException
	 *
	 * @param request
	 *            the HttpServletRequest
	 * @param ex
	 *            the Exception
	 * @return the ResponseEntity containing error message with HTTP status 400
	 */
	@org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseMessage> handleMethodArgumentNotValidException(HttpServletRequest request,
			MethodArgumentNotValidException ex) {
		logger.error("MethodArgumentNotValidException exception occured " + request.getRequestURI(), ex);
		ResponseMessage responseMessage = new ResponseMessage(ex.getMessage(), HttpStatus.BAD_REQUEST.name());
		return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Handles RestClientException
	 *
	 * @param request
	 *            the HttpServletRequest
	 * @param ex
	 *            the RestClientException
	 * @return the ResponseEntity containing error message with HTTP status 500
	 */
	@org.springframework.web.bind.annotation.ExceptionHandler(RestClientException.class)
	public ResponseEntity<ResponseMessage> handleRestClientException(HttpServletRequest request,
			RestClientException ex) {
		logger.error("RestClientException exception occured " + request.getRequestURI(), ex);
		ResponseMessage responseMessage = new ResponseMessage(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.name());
		return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	/**
	 * Handles all Exception
	 *
	 * @param request
	 *            the HttpServletRequest
	 * @param ex
	 *            the Exception
	 * @return the ResponseEntity containing error message and HTTP status 500
	 */
	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseMessage> handleException(HttpServletRequest request, Exception ex) {
		logger.error("exception occured " + request.getRequestURI(), ex);
		ResponseMessage responseMessage = null;

		if (ex instanceof BookStoreException) {
			responseMessage = ((BookStoreException) ex).getResponseMessage();
		} else {
			responseMessage = new ResponseMessage(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.name());
		}
		return new ResponseEntity<ResponseMessage>(responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}