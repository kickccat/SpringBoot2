package io.sample.springbootdeep.service;

import io.sample.springbootdeep.domain.Book;
import io.sample.springbootdeep.domain.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    
    private final BookRepository bookRepository;
    
    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    
    @Override
    public Book getBookById(Long id) {
        return bookRepository.getOne(id);
    }
}
