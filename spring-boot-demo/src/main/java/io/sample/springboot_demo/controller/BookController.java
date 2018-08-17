package io.sample.springboot_demo.controller;

import io.sample.springboot_demo.domain.Book;
import io.sample.springboot_demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BookController {
    
    private final BookService bookService;
    
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    
    @GetMapping("/books")
    public List<Book> listAllBooks() {
        return bookService.getAllBooks();
    }
    
    @PostMapping("/books")
    public Book addOneBook(@RequestParam("name") String name, @RequestParam("author") String author,
                           @RequestParam("description") String description, @RequestParam("status") int status) {
        Book book = new Book();
        book.setAuthor(author);
        book.setDescription(description);
        book.setName(name);
        book.setStatus(status);
        
        return bookService.addUpdateBook(book);
    }
    
    @GetMapping("/books/{id}")
    public Book getOne(@PathVariable("id") long id) {
        return bookService.getOne(id);
    }
    
    @PutMapping("/books")
    public Book updateBook(@RequestParam("name") String name, @RequestParam("author") String author,
                           @RequestParam("description") String description, @RequestParam("status") int status,
                           @RequestParam("id") long id) {
        Book book = new Book();
        book.setId(id);
        book.setAuthor(author);
        book.setDescription(description);
        book.setName(name);
        book.setStatus(status);
    
        return bookService.addUpdateBook(book);
    }
    
    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable("id") long id) {
        bookService.deleteOne(id);
    }
}
