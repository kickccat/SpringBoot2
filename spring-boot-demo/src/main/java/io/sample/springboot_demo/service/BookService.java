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
    
    public List<Book> findByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }
    
    public List<Book> findByAuthorAndStatus(String author, int status) {
        return bookRepository.findByAuthorAndStatus(author, status);
    }
    
    public List<Book> findByDescriptionEndsWith(String desc) {
        return bookRepository.findByDescriptionEndsWith(desc);
    }
    
    public List<Book> findByJPQL(int len) {
        return bookRepository.findByJPQL(len);
    }
    
    public List<Book> findBySQL(int len) {
        return bookRepository.findBySQL(len);
    }
}
