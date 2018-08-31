package io.sample.springbootjpademo.service;

import io.sample.springbootjpademo.domain.Author;
import io.sample.springbootjpademo.domain.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class AuthorServiceImpl implements AuthorService {
    
    private final AuthorRepository authorRepository;
    
    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    
    @Transactional
    @Override
    public Author updateAuthor() {
        Author author = new Author();
        author.setPhone("0171156879");
        author.setSignDate(new Date());
        author.setNickname("小加");
        Author author1 = authorRepository.save(author);
        
        author1.setPhone("22222222222");
    
        return authorRepository.save(author1);
    }
    
    @Override
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }
    
    @Override
    public Author updateAuthor(Author author) {
        return authorRepository.save(author);
    }
    
    @Override
    public Author findAuthoById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }
    
    @Override
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
