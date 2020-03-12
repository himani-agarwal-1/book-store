package com.nagarro.bookstore.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nagarro.bookstore.model.Book;

/**
 * @author himaniagarwal The Interface BookRepository.
 */
@Repository
@Transactional
public interface BookRepository extends JpaRepository<Book, String> {

	public List<Book> findByTitleContainingAndAuthorContainingAndIsbnContaining(String title, String author,
			String isbn);

	public List<Book> findByTitleIgnoreCase(String title);

}
