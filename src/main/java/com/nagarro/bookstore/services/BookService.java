package com.nagarro.bookstore.services;

import java.util.List;
import java.util.Optional;

import com.nagarro.bookstore.entity.Book;
import com.nagarro.bookstore.model.BookRequest;

/**
 * @author himaniagarwal The Interface BookService.
 */
public interface BookService {

	/**
	 * saves book in db
	 *
	 * @param book
	 *            the bookRequest object
	 * @return the persisted book object
	 */
	public Book add(BookRequest book);

	/**
	 * Deletes the book associated with given isbn
	 * 
	 *
	 * @param isbn
	 *            the isbn for book to be deleted
	 */
	public void delete(String isbn);
 
	/**
	 * Finds list of books matching all the input parameters. Returns full list
	 * of books when no parameter provided.
	 *
	 * @param author
	 *            the complete or partial author name
	 * @param isbn
	 *            the ISBN
	 * @param title
	 *            the complete or partial book title
	 * @return the list
	 */
	public List<Book> findByQuery(String author, String isbn, String title);

	/**
	 * fetches book by ISBN
	 *
	 * @param isbn
	 *            the ISBN
	 * @return the optional object which is null if no book found for given ISBN
	 */
	public Optional<Book> getByISBN(String isbn);

	/**
	 * fetches the media coverage for a given book title. Checks if the title is
	 * contained in media coverage's post or title
	 *
	 * @param title
	 *            the book title
	 * @return the list of all media coverage title
	 */
	public List<String> getMediaCoverageForBook(String title);

	/**
	 * Reduces the quantity of a book by one if current count is greater than 0
	 * .
	 *
	 * @param isbn
	 *            the isbn of book
	 * 
	 */
	public void updateBookQuantity(String isbn);

	/**
	 * Updates the book record.
	 *
	 * @param bookRequest the book request
	 * @param book the book record
	 * @return the updated book record
	 */
	Book update(BookRequest bookRequest,  Book book);

}
