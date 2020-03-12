package com.nagarro.bookstore.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * @author himaniagarwal The Class BookQuantity.
 */
@Entity(name = "book_quantity")
public class BookQuantity {

	@Id
	private String isbn;

	@NotNull
	private Integer quantity;

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
