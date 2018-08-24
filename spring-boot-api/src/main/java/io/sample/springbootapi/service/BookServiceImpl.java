package io.sample.springbootapi.service;

import io.sample.springbootapi.domain.Book;
import io.sample.springbootapi.domain.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    
    private final BookRepository bookRepository;
    
    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    
    @Override
    public Book getBookById(long id) {
        return bookRepository.getOne(id);
    }
    
    @Override
    public Book saveBook(Book book) {
        return bookRepository.saveAndFlush(book);
    }
    
    @Override
    public Book updateBook(Book book) {
        return bookRepository.saveAndFlush(book);
    }
    
    @Override
    public void deleteBookById(long id) {
        bookRepository.deleteById(id);
    }
    
    @Override
    public void deleteAllBooks() {
        bookRepository.deleteAll();
    }
}
