package com.nagarro.bookstore.configuration;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import com.nagarro.bookstore.services.BookService;

@Profile("test")
@Configuration
public class BookServiceTestConfiguration {
    @Bean
    @Primary
    public BookService bookService() {
        return Mockito.mock(BookService.class);
    }
}