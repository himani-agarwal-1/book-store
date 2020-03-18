package com.nagarro.bookstore.services;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nagarro.bookstore.constant.Constants;
import com.nagarro.bookstore.entity.Book;
import com.nagarro.bookstore.entity.BookQuantity;
import com.nagarro.bookstore.exception.BookStoreException;
import com.nagarro.bookstore.model.BookRequest;
import com.nagarro.bookstore.model.ResponseMessage;
import com.nagarro.bookstore.repositories.BookQuantityRepository;
import com.nagarro.bookstore.repositories.BookRepository;
import com.nagarro.bookstore.utils.RestClientUtil;

/**
 * @author himaniagarwal Implements CRUD and order operations for book store
 *         API.
 */
@Service
public class BookServiceImpl implements BookService {

	private static Logger LOGGER = LoggerFactory.getLogger(BookServiceImpl.class);

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private BookQuantityRepository bookQuantityRepository;
	
	@Autowired
	private RestClientUtil restClientUtil;

	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nagarro.bookstore.services.BookService#add(com.nagarro.bookstore.
	 * model.BookRequest)
	 */
	@Override
	@Transactional
	public Book add(BookRequest bookRequest) {

		LOGGER.debug("request for add operaration received for title {}", bookRequest.getTitle());
		Book book = new Book();
		BeanUtils.copyProperties(bookRequest, book);
		List<Book> books = bookRepository.findByTitleIgnoreCase(book.getTitle());
		if (books != null && books.size() > 0) {
			throw new BookStoreException(
					new ResponseMessage(Constants.BOOK_ALREADY_EXIST, "title.already.exist"));
		}
		book = bookRepository.save(book);

		BookQuantity bookQuantity = new BookQuantity();
		bookQuantity.setQuantity(bookRequest.getQuantity());
		bookQuantity.setIsbn(book.getIsbn());
		book.setBookQuantity(bookQuantity);
		LOGGER.debug("book saved successfully {}", bookRequest.getTitle());
		bookQuantityRepository.save(bookQuantity);

		return book;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nagarro.bookstore.services.BookService#delete(java.lang.String)
	 */
	@Override
	@Transactional
	public void delete(String isbn) {
		LOGGER.debug("deleting book with isbn {}",isbn);
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

		LOGGER.debug("input args are : author {} , isbn {} , title {}", author, isbn, title);
		author = StringUtils.isBlank(author) ? Constants.EMPTY_STRING : author;
		isbn = StringUtils.isBlank(isbn) ? Constants.EMPTY_STRING : isbn;
		title = StringUtils.isBlank(title) ? Constants.EMPTY_STRING : title;
		return bookRepository.findByTitleContainingAndAuthorContainingAndIsbnContaining(title, author, isbn);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.nagarro.bookstore.services.BookService#getMediaCoverageForBook(java.
	 * lang.String)
	 */
	@Override
	public List<String> getMediaCoverageForBook(String title) {

		LOGGER.debug("fetching media coverage for title {}" , title);
		List<Map<String, Object>> reponses = restClientUtil.getAllMediaCoverage();
		/*
		 * 1. filters list based on criteria - book title contained in either
		 * body or title of media report. 2. maps the list of objects into list
		 * of string ( string being the media title)
		 */
		return reponses.stream().filter(x -> {
			return x.get(Constants.BODY).toString().contains(title) || x.get(Constants.TITLE).toString().contains(title);
		}).map(x -> x.get(Constants.TITLE).toString()).collect(Collectors.toList());

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
	@Transactional
	public void updateBookQuantity(String isbn) {
		
		Optional<BookQuantity> bookQuantityOptional = bookQuantityRepository.findById(isbn);
		if (bookQuantityOptional.isPresent()) {
			BookQuantity currentBookQuantity = bookQuantityOptional.get();
			if (currentBookQuantity.getQuantity() > 0) {
				Integer updatedQuantity = currentBookQuantity.getQuantity() - 1;
				LOGGER.debug("currentBookQuantity.getQuantity() = {}", currentBookQuantity.getQuantity());
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
	@Transactional
	public Book update(BookRequest bookRequest, Book book) {

		BeanUtils.copyProperties(bookRequest, book);
		book = bookRepository.save(book);

		Optional<BookQuantity> bookQuanityOptional = bookQuantityRepository.findById(book.getIsbn());
		if (bookQuanityOptional.isPresent() && bookQuanityOptional.get().getQuantity() != bookRequest.getQuantity()) {
			BookQuantity bookQuantity = bookQuanityOptional.get();
			bookQuantity.setQuantity(bookRequest.getQuantity());
			bookQuantityRepository.save(bookQuantity);
			LOGGER.info("Book quantity updated for isbn : {} updated quantity :   {}" , book.getIsbn() ,
					+ bookQuantity.getQuantity());
		}
		return book;
	}
}
