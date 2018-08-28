package io.sample.springbootapi.api;

import io.sample.springbootapi.domain.Book;
import io.sample.springbootapi.dto.BookDTO;
import io.sample.springbootapi.exception.InvalidRequestException;
import io.sample.springbootapi.exception.NotFoundException;
import io.sample.springbootapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        if (books.isEmpty()) {
            throw new NotFoundException("Books not found...");
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
    
    @GetMapping("/books/{id}")
    public ResponseEntity<?> getBookById(@PathVariable("id") long id) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            throw new NotFoundException(String.format("book by id %s not found", id));
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }
    
    @PostMapping("/books")
    public ResponseEntity<?> saveBook(@Valid @RequestBody BookDTO bookDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException("Invalid parameter", bindingResult);
        }
        Book book1 = bookService.saveBook(bookDTO.convertBook());
        return new ResponseEntity<>(book1, HttpStatus.CREATED);
    }
    
    @PutMapping("/books/{id}")
    public ResponseEntity<?> updateBook(@PathVariable("id") long id, @Valid @RequestBody BookDTO bookDTO, BindingResult bindingResult) {
        Book todoBook = bookService.getBookById(id);
        if (todoBook == null) {
            throw new NotFoundException(String.format("book by id %s not found", id));
        }
        if (bindingResult.hasErrors()) {
            throw new InvalidRequestException("Invalid parameter", bindingResult);
        }
        bookDTO.convertBook(todoBook);
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
