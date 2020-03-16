package com.nagarro.bookstore.repositories;

import org.springframework.data.repository.CrudRepository;

import com.nagarro.bookstore.entity.BookQuantity;

/**
 * @author himaniagarwal The Interface BookQuantityRepository.
 */
public interface BookQuantityRepository extends CrudRepository<BookQuantity, String> {

	
}

