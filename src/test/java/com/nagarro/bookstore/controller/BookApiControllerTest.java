package com.nagarro.bookstore.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nagarro.bookstore.controller.BookApiController;
import com.nagarro.bookstore.exception.BookStoreException;
import com.nagarro.bookstore.model.Book;
import com.nagarro.bookstore.model.BookRequest;
import com.nagarro.bookstore.services.BookService;
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = BookApiController.class)
@ActiveProfiles("test")

@TestPropertySource(properties = { "logging.file.path", "test" })
public class BookApiControllerTest {

	@Autowired
	  private MockMvc mockMvc;

	  
	  private ObjectMapper objectMapper = new ObjectMapper();

	  @MockBean
	  private BookService bookService;
	  
	  @InjectMocks
	  private BookApiController bookApiController;
	  
	  @Before
	  public void init(){
	  MockitoAnnotations.initMocks(this);
	    mockMvc = MockMvcBuilders.standaloneSetup(bookApiController).build();
	  }
	  
	  @Test
	  public void testAddBook() throws Exception{
	  Book book = new Book();
		book.setTitle("title");
		book.setIsbn("isbn");
		book.setAuthor("author");
		
		BookRequest bookRequest = new BookRequest();
		bookRequest.setTitle("title");
		bookRequest.setQuantity(10);
		bookRequest.setAuthor("author");
		bookRequest.setPublishedBy("publishedBy");
		
		Mockito.when(bookService.add(bookRequest)).thenReturn(book);
		
		  String jsonpayload = objectMapper.writeValueAsString(bookRequest);
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/bookstore/book")
				    .contentType("application/json").content(jsonpayload);
		mockMvc.perform(request)
				    .andExpect(MockMvcResultMatchers.status().isOk());
				    
	  }
	  
	  @Test
	  public void testAddBookAuthorNotProvided() throws Exception{
	  Book book = new Book();
		book.setTitle("title");
		book.setIsbn("isbn");
		book.setAuthor("author");
		
		BookRequest bookRequest = new BookRequest();
		bookRequest.setTitle("title");
		bookRequest.setQuantity(10);
		bookRequest.setPublishedBy("publishedBy");
		
		Mockito.when(bookService.add(bookRequest)).thenReturn(book);
		
		  String jsonpayload = objectMapper.writeValueAsString(bookRequest);
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/bookstore/book")
				    .contentType("application/json").content(jsonpayload);
		mockMvc.perform(request)
				    .andExpect(MockMvcResultMatchers.status().isBadRequest());
				    
	  }
	  
	  @Test
	  public void testAddBookTitleAlreadyExist() throws Exception{
	  Book book = new Book();
		book.setTitle("title");
		book.setIsbn("isbn");
		book.setAuthor("author");
		
		BookRequest bookRequest = new BookRequest();
		bookRequest.setTitle("title");
		bookRequest.setQuantity(10);
		bookRequest.setPublishedBy("publishedBy");
		
		Mockito.when(bookService.add(bookRequest)).thenThrow(BookStoreException.class);
		
		  String jsonpayload = objectMapper.writeValueAsString(bookRequest);
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/bookstore/book")
				    .contentType("application/json").content(jsonpayload);
		mockMvc.perform(request)
				    .andExpect(MockMvcResultMatchers.status().isBadRequest());
				    
	  }
	  
	  @Test
	  public void testDeleteBook() throws Exception{
		
		Mockito.doNothing().when(bookService).delete(Mockito.anyString());
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/bookstore/book/{isbn}", "test");
				mockMvc.perform(request)
				    .andExpect(MockMvcResultMatchers.status().isOk());
				    
	  }
	  
	  @Test
	  public void testFindBooksByQuery() throws Exception{
	  Book book = new Book();
		String title = "title";
		book.setTitle(title);
		String isbn = "isbn";
		book.setIsbn(isbn);
		String author = "author";
		book.setAuthor(author);

		
		List<Book> books = new ArrayList<>();
		books.add(book);
		
		Mockito.when(bookService.findByQuery(author, isbn, title)).thenReturn(books);
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/bookstore/books")
		.param(author, author).param("isbn", isbn).param("title", title);
				mockMvc.perform(request)
				    .andExpect(MockMvcResultMatchers.status().isOk());
				    
	  }
	  
	  @Test
	  public void testGetMediaCoverage() throws Exception{
		String title = "title";
	
		List<String> titles = new ArrayList<>();
		titles.add(title);
		Mockito.when(bookService.getMediaCoverageForBook(title)).thenReturn(titles );
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/bookstore/mediaCoverage").param(title, title);
				mockMvc.perform(request)
				    .andExpect(MockMvcResultMatchers.status().isOk());
				    
	  }
	  
	  
	@Test
	  public void testGetByISBN() throws Exception{
		String isbn = "isbn";
	
			  Book book = new Book();
		String title = "title";
		book.setTitle(title);
		book.setIsbn(isbn);
		String author = "author";
		book.setAuthor(author);

		Mockito.when(bookService.getByISBN(isbn)).thenReturn(Optional.of(book) );
		
		MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/bookstore/book/{isbn}", isbn);
				mockMvc.perform(request)
				    .andExpect(MockMvcResultMatchers.status().isOk());
				    
	  }
	  
}
