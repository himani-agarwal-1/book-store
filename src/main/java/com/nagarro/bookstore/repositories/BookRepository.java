package com.nagarro.bookstore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.bookstore.entity.Book;

/**
 * @author himaniagarwal The Interface BookRepository.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, String> {

	public List<Book> findByTitleContainingAndAuthorContainingAndIsbnContaining(String title, String author,
			String isbn);

	public List<Book> findByTitleIgnoreCase(String title);

}
