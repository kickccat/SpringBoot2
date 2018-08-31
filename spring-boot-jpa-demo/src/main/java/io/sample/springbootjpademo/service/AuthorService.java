package io.sample.springbootjpademo.service;

import io.sample.springbootjpademo.domain.Author;

public interface AuthorService {
    
    Author updateAuthor();
    
    Author saveAuthor(Author author);
    
    Author updateAuthor(Author author);
    
    Author findAuthoById(Long id);
    
    void deleteAuthor(Long id);
}
