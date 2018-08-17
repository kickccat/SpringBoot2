package io.sample.springboot_demo.service;

import io.sample.springboot_demo.domain.Book;
import io.sample.springboot_demo.domain.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    
    private final BookRepository bookRepository;
    
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    
    public Book addUpdateBook(Book book) {
        return bookRepository.save(book);
    }
    
    public Book getOne(long id) {
        return bookRepository.findById(id).orElseThrow();
    }
    
    public void deleteOne(long id) {
        bookRepository.deleteById(id);
    }
}
