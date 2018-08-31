package io.sample.springbootjpademo.web;

import io.sample.springbootjpademo.domain.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/authors")
public class TestController {
    
    private final AuthorRepository authorRepository;
    
    @Autowired
    public TestController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    
    @GetMapping
    public Object findAuthorByPaging(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return authorRepository.findAll(pageable);
    }
}
