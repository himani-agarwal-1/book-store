package com.nagarro.bookstore.repositories;

import org.springframework.data.repository.CrudRepository;

import com.nagarro.bookstore.model.BookQuantity;

/**
 * @author himaniagarwal The Interface BookQuantityRepository.
 */
public interface BookQuantityRepository extends CrudRepository<BookQuantity, String> {

	
}

