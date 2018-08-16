package io.sample.springboot_demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class HelloController {
    
    @Value("${book.name}")
    private String name;
    
    @Value("${book.author}")
    private String author;
    
    @Value("${book.isbn}")
    private String isbn;
    
    @Value("${book.description}")
    private String description;
    
    @GetMapping("/sayHello")
    public String hello() {
        return "Hello Spring Boot";
    }
    
    @GetMapping("/books")
    public Object getAll(@RequestParam("page") int page, @RequestParam(value = "size", defaultValue = "10") int size) {
        Map<String, Object> book = new HashMap<>();
        book.put("Name", "World");
        book.put("Author", "John");
        book.put("ISBN", "aabbcc");
        
        Map<String, Object> book2 = new HashMap<>();
        book2.put("Name", "Earth");
        book2.put("Author", "Alice");
        book2.put("ISBN", "ddffgg");
    
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
        books.put("Name", name);
        books.put("ISBN", isbn);
        books.put("Author", author);
        books.put("Description", description);
        
        return books;
    }
    
    @PostMapping("/books")
    public Object addBook(@RequestParam("name") String name, @RequestParam("author") String author,
                          @RequestParam("isbn") String isbn) {
        Map<String, Object> book = new HashMap<>();
        book.put("Name", name);
        book.put("Author", author);
        book.put("ISBN", isbn);
        return book;
    }
}