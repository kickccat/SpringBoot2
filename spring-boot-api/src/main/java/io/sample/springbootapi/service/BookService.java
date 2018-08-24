package io.sample.springbootapi.service;

import io.sample.springbootapi.domain.Book;

import java.util.List;

public interface BookService {
    
    List<Book> getAllBooks();
    
    Book getBookById(long id);
    
    Book saveBook(Book book);
    
    Book updateBook(Book book);
    
    void deleteBookById(long id);
    
    void deleteAllBooks();
}
