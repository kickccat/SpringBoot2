package io.sample.springbootapi.api;

import io.sample.springbootapi.domain.Book;
import io.sample.springbootapi.service.BookService;
import io.sample.springbootapi.util.BookUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class BookApi {
    
    private final BookService bookService;
    
    @Autowired
    public BookApi(BookService bookService) {
        this.bookService = bookService;
    }
    
    @GetMapping("/books")
    public ResponseEntity<?> listAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
    
    @GetMapping("/books/{id}")
    public ResponseEntity<?> getBookById(@PathVariable("id") long id) {
        Book book = bookService.getBookById(id);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }
    
    @PostMapping("/books")
    public ResponseEntity<?> saveBook(@RequestBody Book book) {
        Book book1 = bookService.saveBook(book);
        return new ResponseEntity<>(book1, HttpStatus.CREATED);
    }
    
    @PutMapping("/books/{id}")
    public ResponseEntity<?> updateBook(@PathVariable("id") long id, @RequestBody Book book) {
        Book todoBook = bookService.getBookById(id);
//        BeanUtils.copyProperties(book, todoBook);
        BookUtil.convertBook(book, todoBook);
        Book book1 = bookService.updateBook(todoBook);
        return new ResponseEntity<>(book1, HttpStatus.OK);
    }
    
    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable("id") long id) {
        bookService.deleteBookById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @DeleteMapping("/books")
    public ResponseEntity<?> deleteAllBooks() {
        bookService.deleteAllBooks();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
