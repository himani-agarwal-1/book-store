package com.nagarro.bookstore.services;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nagarro.bookstore.entity.Book;
import com.nagarro.bookstore.entity.BookQuantity;
import com.nagarro.bookstore.exception.BookStoreException;
import com.nagarro.bookstore.model.BookRequest;
import com.nagarro.bookstore.model.ResponseMessage;
import com.nagarro.bookstore.repositories.BookQuantityRepository;
import com.nagarro.bookstore.repositories.BookRepository;

/**
 * @author himaniagarwal Implements CRUD and order operations for book store
 *         API.
 */
@Service
public class BookServiceImpl implements BookService {

	Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private BookQuantityRepository bookQuantityRepository;

	@Autowired
	RestTemplate restTemplate;

	/**
	 * reads property media.coverage.uri from application.properties evaluates
	 * tohttps://jsonplaceholder.typicode.com/posts
	 */
	@Value(value = "${media.coverage.uri}")
	private String mediaCoverageURI;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nagarro.bookstore.services.BookService#add(com.nagarro.bookstore.
	 * model.BookRequest)
	 */
	@Override
	public Book add(BookRequest bookRequest) {

		LOGGER.debug("request for add operaration received for title" + bookRequest.getTitle());
		Book book = new Book();
		BeanUtils.copyProperties(bookRequest, book);
		List<Book> books = bookRepository.findByTitleIgnoreCase(book.getTitle());
		if (books != null && books.size() > 0) {
			throw new BookStoreException(
					new ResponseMessage(" book with same name already exist", "title.already.exist"));
		}
		book = bookRepository.save(book);

		BookQuantity bookQuantity = new BookQuantity();
		bookQuantity.setQuantity(bookRequest.getQuantity());
		bookQuantity.setIsbn(book.getIsbn());
		LOGGER.debug("book saved successfully" + bookRequest.getTitle());
		bookQuantityRepository.save(bookQuantity);

		return book;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nagarro.bookstore.services.BookService#delete(java.lang.String)
	 */
	@Override
	public void delete(String isbn) {
		Book book = new Book();
		book.setIsbn(isbn);
		bookRepository.delete(book);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nagarro.bookstore.services.BookService#findByQuery(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public List<Book> findByQuery(String author, String isbn, String title) {

		LOGGER.debug("input args are : author %s , isbn %s , title %s", author, isbn, title);
		author = StringUtils.isBlank(author) ? "" : author;
		isbn = StringUtils.isBlank(isbn) ? "" : isbn;
		title = StringUtils.isBlank(title) ? "" : title;
		return bookRepository.findByTitleContainingAndAuthorContainingAndIsbnContaining(title, author, isbn);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nagarro.bookstore.services.BookService#getMediaCoverageForBook(java.
	 * lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getMediaCoverageForBook(String title) {

		LOGGER.debug("fetching media coverage for title " + title);
		List<Map<String, Object>> reponses = (List<Map<String, Object>>) restTemplate.getForObject(mediaCoverageURI,
				List.class);

		if (null == reponses) {
			return new ArrayList<String>(0);
		}
		/*
		 * 1. filters list based on criteria - book title contained in either
		 * body or title of media report. 2. maps the list of objects into list
		 * of string ( string being the media title)
		 */
		return reponses.stream().filter(x -> {
			return x.get("body").toString().contains(title) || x.get("title").toString().contains(title);
		}).map(x -> x.get("title").toString()).collect(Collectors.toList());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nagarro.bookstore.services.BookService#get(java.lang.String)
	 */
	@Override
	public Optional<Book> getByISBN(String isbn) {

		Optional<Book> book = bookRepository.findById(isbn);
		return book;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nagarro.bookstore.services.BookService#updateBookQuantity(java.lang.
	 * String)
	 */
	@Override
	public void updateBookQuantity(String isbn) {

		LOGGER.info("just after update query for isbn " + isbn + "  ...  " + Thread.currentThread().getId());
		try {
			LOGGER.info("threadname on hold= " + isbn + "  ...  " + Thread.currentThread().getId());
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		LOGGER.info("threadname resumed = " + isbn + "  ...  " + Thread.currentThread().getId());
		Optional<BookQuantity> bookQuantityOptional = bookQuantityRepository.findById(isbn);
		if (bookQuantityOptional.isPresent()) {
			BookQuantity currentBookQuantity = bookQuantityOptional.get();
			if (currentBookQuantity.getQuantity() > 0) {
				Integer updatedQuantity = currentBookQuantity.getQuantity() - 1;
				LOGGER.info("currentBookQuantity.getQuantity() = " + currentBookQuantity.getQuantity());
				currentBookQuantity.setQuantity(updatedQuantity);
				bookQuantityRepository.save(currentBookQuantity);
			} else {
				String message = MessageFormat.format(
						"Processing failed : We could not process your order as bookIsbn: {0} is out of stock.", isbn);
				throw new BookStoreException(new ResponseMessage(message, "book.out.of.stock"));
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nagarro.bookstore.services.BookService#update(com.nagarro.bookstore.
	 * model.BookRequest, com.nagarro.bookstore.entity.Book)
	 */
	@Override
	public Book update(BookRequest bookRequest, Book book) {

		BeanUtils.copyProperties(bookRequest, book);
		book = bookRepository.save(book);

		Optional<BookQuantity> bookQuanityOptional = bookQuantityRepository.findById(book.getIsbn());
		if (bookQuanityOptional.isPresent()) {
			BookQuantity bookQuantity = bookQuanityOptional.get();
			bookQuantity.setQuantity(bookRequest.getQuantity());
			bookQuantityRepository.save(bookQuantity);
			LOGGER.info("Book quantity updated for isbn : " + book.getIsbn() + " updated quantity :   "
					+ bookQuantity.getQuantity());
		}
		return book;
	}
}
