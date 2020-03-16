package com.nagarro.bookstore.controller;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nagarro.bookstore.entity.Book;
import com.nagarro.bookstore.model.BookRequest;
import com.nagarro.bookstore.model.ResponseMessage;
import com.nagarro.bookstore.services.BookService;

/**
 * @author himaniagarwal Book Controller handles book API calls
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2020-03-05T04:48:53.265Z")

@Controller
@RequestMapping("/bookstore")
public class BookApiController implements BookApi {

	@Autowired
	private BookService bookService;

	@Override
	public ResponseEntity<Book> addBook(@RequestBody @Valid BookRequest book) {
		Book bookSaved = bookService.add(book);
		return new ResponseEntity<Book>(bookSaved, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> deleteBook(@PathVariable String isbn) {
		bookService.delete(isbn);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Book>> findBooksByQuery(@RequestParam(name = "title", required = false) String title,
			@RequestParam(name = "author", required = false) String author,
			@RequestParam(name = "isbn", required = false) String isbn) {
		List<Book> books = bookService.findByQuery(author, isbn, title);
		if (null == books || books.isEmpty()) {
			return new ResponseEntity(new ResponseMessage(" No match found", HttpStatus.NOT_FOUND.name()),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Book> getBookByIsbn(String isbn) {
		Optional<Book> bookOptional = bookService.getByISBN(isbn);
		if (!bookOptional.isPresent()) {
			return new ResponseEntity(new ResponseMessage(" No match found", HttpStatus.NOT_FOUND.name()),
					HttpStatus.NOT_FOUND);
		}
		Book bookRecord = bookOptional.get();
		return new ResponseEntity<Book>(bookRecord, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> orderBook(String isbn, Book book) {

		Optional<Book> bookOptional = bookService.getByISBN(isbn);
		if (!bookOptional.isPresent()) {
			return new ResponseEntity(
					new ResponseMessage("No matching book found with isbn: " + isbn, HttpStatus.NOT_FOUND.name()),
					HttpStatus.NOT_FOUND);

		}
		bookService.updateBookQuantity(isbn);
		String message = MessageFormat.format("Order for \"{0}\" has been processed successfully",
				bookOptional.get().getTitle());
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<String>> getMediaCoverage(String booktTitle) {
		List<String> mediaCoverage = bookService.getMediaCoverageForBook(booktTitle);
		mediaCoverage = null != mediaCoverage ? mediaCoverage : new ArrayList<>(0);
		return new ResponseEntity<List<String>>(mediaCoverage, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Book> updateBook(String isbn, @Valid BookRequest bookRequest) {
		Optional<Book> bookByISBN = bookService.getByISBN(isbn);
		if (!bookByISBN.isPresent()) {
			return new ResponseEntity(new ResponseMessage("Invalid ISBN provided", HttpStatus.NOT_FOUND.name()),
					HttpStatus.NOT_FOUND);
		}
		Book book = bookService.update(bookRequest, bookByISBN.get());
		return new ResponseEntity<>(book, HttpStatus.OK);
	}

}
