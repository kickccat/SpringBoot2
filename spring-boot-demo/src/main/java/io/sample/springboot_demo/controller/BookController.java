package io.sample.springboot_demo.controller;

import io.sample.springboot_demo.domain.Book;
import io.sample.springboot_demo.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BookController {
    
    private final BookService bookService;
    
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    
    @GetMapping("/books")
    public String listAll(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "books";
    }
    
    @GetMapping("/books/{id}")
    public String listOne(@PathVariable("id") long id, Model model) {
        Book book = bookService.getOne(id);
        model.addAttribute("book", book);
        return "book";
    }
    
    @GetMapping("/books/input")
    public String inputPage(Model model) {
        model.addAttribute("book", new Book());
        return "input";
    }
    
    @PostMapping("/books")
    public String addBook(Book book) {
        bookService.addUpdateBook(book);
        return "redirect:/books";
    }
    
    @GetMapping("/books/{id}/update")
    public String update(@PathVariable("id") long id, Model model) {
        Book book = bookService.getOne(id);
        model.addAttribute("book", book);
        return "input";
    }
}
