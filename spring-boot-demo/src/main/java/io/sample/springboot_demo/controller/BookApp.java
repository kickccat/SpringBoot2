package io.sample.springboot_demo.controller;

import io.sample.springboot_demo.domain.Book;
import io.sample.springboot_demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BookApp {
    
    private final BookService bookService;
    
    @Autowired
    public BookApp(BookService bookService) {
        this.bookService = bookService;
    }
    
    @GetMapping("/books")
    public List<Book> listAllBooks() {
        return bookService.getAllBooks();
    }
    
    @GetMapping("/books/pages")
    public Page<Book> listAllBooksByPage(@PageableDefault(page = 0, size = 5, sort = {"id"},
            direction = Sort.Direction.DESC) Pageable pageable) {
        return bookService.getAllDivideByPage(pageable);
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
    
    @PostMapping("/books/byAuthor")
    public List<Book> findByAuthorName(@RequestParam("author") String author) {
        return bookService.findByAuthor(author);
    }
    
    @PostMapping("/books/byAuthorAndStatus")
    public List<Book> findByAuthorAndStatus(@RequestParam("author") String author, @RequestParam("status") int status) {
        return bookService.findByAuthorAndStatus(author, status);
    }
    
    @PostMapping("/books/byDescriptionEndsWith")
    public List<Book> findByDescEndsWith(@RequestParam("description") String desc) {
        return bookService.findByDescriptionEndsWith(desc);
    }
    
    @PostMapping("/books/byJPQL")
    public List<Book> findByJPQL(@RequestParam("len") int len) {
        return bookService.findByJPQL(len);
    }
    
    @PostMapping("/books/bySQL")
    public List<Book> findBySQL(@RequestParam("len") int len) {
        return bookService.findBySQL(len);
    }
    
    @PostMapping("/books/by")
    public int findBy(@RequestParam("status") int status, @RequestParam("id") long id) {
        return bookService.updateByJPQL(status, id);
    }
    
    @PostMapping("/books/delete")
    public int deleteBy(@RequestParam("id") long id) {
        return bookService.deleteByJPQL(id);
    }
}