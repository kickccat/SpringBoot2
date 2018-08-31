package io.sample.springbootjpademo;

import com.alibaba.fastjson.JSON;
import io.sample.springbootjpademo.domain.Author;
import io.sample.springbootjpademo.domain.AuthorRepository;
import io.sample.springbootjpademo.service.AuthorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorTest {
    
    @Autowired
    private AuthorRepository authorRepository;
    
    @Autowired
    private AuthorService authorService;
    
    @Test
    public void saveAuthorTest() {
        Author author = new Author();
        author.setNickname("Jack");
        author.setPhone("123456");
        author.setSignDate(new Date());
    
        authorRepository.save(author);
    }
    
    @Test
    public void findAuthorTest() {
        List<Author> authors = authorRepository.findByPhoneAndNickname("123456", "Jack");
        System.out.println(JSON.toJSONString(authors,true));
    }
    
    @Test
    public void findAuthorByPagingTest() {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(0, 5, sort);
        Page<Author> page = authorRepository.findAll(pageable);
        System.out.println(JSON.toJSONString(page, true));
    }
    
    @Test
    public void transactionalTest() {
        authorService.updateAuthor();
    }
}
