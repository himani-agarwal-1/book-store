package com.nagarro.bookstore.model;

import javax.validation.constraints.NotNull;

/**
 * @author himaniagarwal The Class BookRequest. Models Input JSON for add book
 *         request.
 * 
 */
public class BookRequest {

	@NotNull
	private String author;
	@NotNull
	private String title;
	private String description;
	@NotNull
	private String publishedBy;

	private Float price = 0f;
	@NotNull
	private Integer quantity;
	private String language;
	private String bindingType;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPublishedBy() {
		return publishedBy;
	}

	public void setPublishedBy(String publishedBy) {
		this.publishedBy = publishedBy;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getBindingType() {
		return bindingType;
	}

	public void setBindingType(String bindingType) {
		this.bindingType = bindingType;
	}

}
