package com.nagarro.bookstore.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.validation.annotation.Validated;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author himaniagarwal Book entity
 */
@Validated
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-03-05T04:48:53.265Z")
@Entity
public class Book {
	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@Column(length = 50)
	private String isbn = null;

	private String title = null;

	private String author = null;
	@Column(length = 1000)
	private String description = null;

	private String publishedBy = null;

	private float price = 0;
	private String language;
	private String bindingType;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
	@PrimaryKeyJoinColumn
	
	private BookQuantity bookQuantity;

	@Version
	private Integer version;

	@ApiModelProperty(value = "")
	@JsonProperty("isbn")

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	@ApiModelProperty(required = true, value = "")
	@NotNull
	@JsonProperty("title")

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@ApiModelProperty(required = true, value = "")
	@NotNull
	@JsonProperty("author")

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@ApiModelProperty(required = false, value = "")
	@JsonProperty("description")

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ApiModelProperty(required = true, value = "")
	@JsonProperty("publishedBy")

	public String getPublishedBy() {
		return publishedBy;
	}

	public void setPublishedBy(String publishedBy) {
		this.publishedBy = publishedBy;
	}

	@ApiModelProperty(required = true, value = "")
	@JsonProperty("price")
	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@JsonProperty("language")
	@ApiModelProperty(required = false, value = "")
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@JsonProperty("bindingType")
	@ApiModelProperty(required = false, value = "")
	public String getBindingType() {
		return bindingType;
	}

	public void setBindingType(String bindingType) {
		this.bindingType = bindingType;
	}

	public BookQuantity getBookQuantity() {
		return bookQuantity;
	}

	public void setBookQuantity(BookQuantity bookQuantity) {
		this.bookQuantity = bookQuantity;
	}

	@JsonIgnore
	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

}
