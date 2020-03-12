package com.nagarro.bookstore.repositories;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.nagarro.bookstore.model.BookQuantity;

/**
 * @author himaniagarwal The Interface BookQuantityRepository.
 */
public interface BookQuantityRepository extends CrudRepository<BookQuantity, String> {

	@Modifying
	@Query(value ="update book_quantity u set u.quantity = u.quantity -1  where u.quantity > 0 and u.isbn = :isbn", nativeQuery = true)
	public int updateQuantityForIsbn(@Param("isbn") String isbn);
}

