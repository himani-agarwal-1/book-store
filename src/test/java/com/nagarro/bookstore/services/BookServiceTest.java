package com.nagarro.bookstore.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import com.nagarro.bookstore.entity.Book;
import com.nagarro.bookstore.entity.BookQuantity;
import com.nagarro.bookstore.exception.BookStoreException;
import com.nagarro.bookstore.model.BookRequest;
import com.nagarro.bookstore.repositories.BookQuantityRepository;
import com.nagarro.bookstore.repositories.BookRepository;
import com.nagarro.bookstore.utils.RestClientUtil;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceTest {

	@InjectMocks
	private BookServiceImpl bookService;
	@Mock
	private BookRepository bookRepository;
	@Mock
	private BookQuantityRepository bookQuantityRepository;

	@Mock
	private RestClientUtil restClientUtil;

	private Book book;

	@Before
	public void init() {
		book = new Book();
		book.setTitle("title");
		book.setIsbn("isbn");
		book.setAuthor("author");
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testAddSuccess() {

		BookRequest bookRequest = new BookRequest();
		bookRequest.setTitle("title");
		bookRequest.setQuantity(10);

		book.setTitle(bookRequest.getTitle());

		Mockito.when(bookRepository.findByTitleIgnoreCase(bookRequest.getTitle())).thenReturn(null);
		Mockito.when(bookRepository.save(Mockito.any(Book.class))).thenReturn(book);
		BookQuantity bookQuantity = new BookQuantity();
		Mockito.when(bookQuantityRepository.save(Mockito.any(BookQuantity.class))).thenReturn(bookQuantity);

		Book response = bookService.add(bookRequest);
		Assert.assertNotNull(response);
		Assert.assertNotNull(book.getIsbn());
		Assert.assertEquals(bookRequest.getTitle(), response.getTitle());
	}

	@Test(expected = BookStoreException.class)
	public void testAddWhenTitleAlreadyExist() {

		BookRequest bookRequest = new BookRequest();
		bookRequest.setTitle("title");
		bookRequest.setQuantity(10);

		book.setTitle(bookRequest.getTitle());
		List<Book> books = new ArrayList<>();
		books.add(book);

		Mockito.when(bookRepository.findByTitleIgnoreCase(bookRequest.getTitle())).thenReturn(books);
		bookService.add(bookRequest);

	}

	@Test
	public void testDeleteSuccess() {

		Mockito.doNothing().when(bookRepository).delete(Mockito.any(Book.class));
		String isbn = "isbn";
		bookService.delete(isbn);
	}

	@Test
	public void testFindByQuery() {

		List<Book> books = new ArrayList<>();
		books.add(book);

		String title = "title";
		String author = "author";
		String isbn = "isbn";
		Mockito.when(bookRepository.findByTitleContainingAndAuthorContainingAndIsbnContaining(title, author, isbn))
				.thenReturn(books);
		List<Book> responses = bookService.findByQuery(author, isbn, title);
		Assert.assertNotNull(responses);
		Assert.assertEquals(books.size(), responses.size());
	}

	@Test
	public void testFindByQueryWhenParametersAreNull() {

		List<Book> books = new ArrayList<>();

		String title = null;
		String author = null;
		String isbn = null;

		List<Book> responses = bookService.findByQuery(author, isbn, title);
		Assert.assertNotNull(responses);
		Assert.assertEquals(books.size(), responses.size());
	}

	@Test
	public void testGetMediaCoverageForBook() {

		List<Map<String, Object>> mediaList = new ArrayList<>();
		Map<String, Object> map = new HashMap<>();
		map.put("title", "title");
		map.put("body", "body");
		map.put("id", 1);
		mediaList.add(map);
		String title = "t";
		Mockito.when(restClientUtil.getAllMediaCoverage())
				.thenReturn(mediaList);
		List<String> responses = bookService.getMediaCoverageForBook(title);
		Assert.assertNotNull(responses);
		Assert.assertEquals(1, responses.size());

		responses = bookService.getMediaCoverageForBook("nomatch");
		Assert.assertNotNull(responses);
		Assert.assertEquals(0, responses.size());
	}

	@Test
	public void testGetByISBN() {

		Optional<Book> bookOptional = Optional.of(book);
		String isbn = "isbn";
		Mockito.when(bookRepository.findById(isbn)).thenReturn(bookOptional);
		Optional<Book> response = bookService.getByISBN(isbn);
		Assert.assertNotNull(response);
		Assert.assertTrue(response.isPresent());

	}

	@Test
	public void testGetByISBNWhenISBNNotFound() {

		Optional<Book> bookOptional = Optional.empty();
		String isbn = "isbn";
		Mockito.when(bookRepository.findById(isbn)).thenReturn(bookOptional);
		Optional<Book> response = bookService.getByISBN(isbn);
		Assert.assertNotNull(response);
		Assert.assertFalse(response.isPresent());

	}
	
	@Test
	public void testUpdateSuccess() {

		BookRequest bookRequest = new BookRequest();
		bookRequest.setTitle("title");
		bookRequest.setQuantity(10);

		book.setTitle(bookRequest.getTitle());

		Mockito.when(bookRepository.save(Mockito.any(Book.class))).thenReturn(book);
		BookQuantity bookQuantity = new BookQuantity();
		bookQuantity.setIsbn(book.getIsbn());
		bookQuantity.setQuantity(11);
		Mockito.when(bookQuantityRepository.findById(Mockito.anyString())).thenReturn(Optional.of(bookQuantity));
		Mockito.when(bookQuantityRepository.save(Mockito.any(BookQuantity.class))).thenReturn(bookQuantity);

		Book response = bookService.update(bookRequest,book);
		Assert.assertNotNull(response);
		Assert.assertNotNull(book.getIsbn());
		Assert.assertEquals(bookRequest.getTitle(), response.getTitle());
	}

	
	@Test(expected = BookStoreException.class)
	public void testUpdateQuantityWhenBookOutOfStock() {
		BookQuantity bookQuantity = new BookQuantity();
		bookQuantity.setIsbn(book.getIsbn());
		bookQuantity.setQuantity(0);
		
		
		Mockito.when(bookQuantityRepository.findById(Mockito.anyString())).thenReturn(Optional.of(bookQuantity));
		bookService.updateBookQuantity("isbn");
		
	}
	
	

}
