package io.sample.springboot_demo.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class HelloController {
    
    @GetMapping("/sayHello")
    public String hello() {
        return "Hello Spring Boot";
    }
    
    @GetMapping("/books")
    public Object getAll(@RequestParam("page") int page, @RequestParam(value = "size", defaultValue = "10") int size) {
        Map<String, Object> book = new HashMap<>();
        book.put("name", "World");
        book.put("author", "John");
        book.put("isbn", "aabbcc");
        
        Map<String, Object> book2 = new HashMap<>();
        book2.put("name", "Earth");
        book2.put("author", "Alice");
        book2.put("isbn", "ddffgg");
    
        List<Map> contents = new ArrayList<>();
        contents.add(book);
        contents.add(book2);
        
        Map<String, Object> pageMap = new HashMap<>();
        pageMap.put("page", page);
        pageMap.put("size", size);
        pageMap.put("content", contents);
        
        return pageMap;
    }
    
    /**
     * @return book
     * @Param id
     */
    @GetMapping("/books/{id}")
    public Object getById(@PathVariable("id") long id) {
        System.out.println("id: " + id);
        
        Map<String, Object> books = new HashMap<>();
        books.put("name", "好好学习");
        books.put("ISBN", "1234567890");
        books.put("author", "无名氏");
        
        return books;
    }
    
    @PostMapping("/books")
    public Object addBook(@RequestParam("name") String name, @RequestParam("author") String author,
                          @RequestParam("isbn") String isbn) {
        Map<String, Object> book = new HashMap<>();
        book.put("name", name);
        book.put("author", author);
        book.put("isbn", isbn);
        return book;
    }
}