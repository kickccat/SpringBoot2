package io.sample.springbootdeep.controller;

import io.sample.springbootdeep.domain.Book;
import io.sample.springbootdeep.exception.BookNotFoundException;
import io.sample.springbootdeep.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/books")
public class BookController {
    
    private final BookService bookService;
    
    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    
    @GetMapping("/{id}")
    public String getBookById(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            throw new BookNotFoundException("书单信息不存在");
        }
        model.addAttribute("book", book);
        return "book";
    }
    
//    @ExceptionHandler({Exception.class})
//    public ModelAndView handleException(HttpServletRequest request, Exception e) {
//
//        log.error("Request URL: {}, Exception: {}", request.getRequestURL(), e.getMessage());
//
//        ModelAndView view = new ModelAndView();
//        view.addObject("url", request.getRequestURL());
//        view.addObject("exception", e);
//        view.setViewName("error/error");
//
//        return view;
//    }
}
