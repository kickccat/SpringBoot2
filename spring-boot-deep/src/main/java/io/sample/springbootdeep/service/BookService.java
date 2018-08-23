package io.sample.springbootdeep.service;

import io.sample.springbootdeep.domain.Book;

public interface BookService {
    Book getBookById(Long id);
}
