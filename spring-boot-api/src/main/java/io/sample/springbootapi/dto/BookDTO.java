package io.sample.springbootapi.dto;

import io.sample.springbootapi.domain.Book;
import io.sample.springbootapi.util.BookUtils;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class BookDTO {
    
    private Integer status;
    
    private String name;
    
    private String author;
    
    private String description;
    
    public void convertBook(Book todoBook) {
        new BookConvert().convert(this, todoBook);
    }
    
    private class BookConvert implements Convert<BookDTO, Book> {
    
        @Override
        public Book convert(BookDTO bookDTO, Book todoBook) {
            String[] ignorePropertyNames = BookUtils.getIgnorePropertyNames(bookDTO);
            BeanUtils.copyProperties(bookDTO, todoBook, ignorePropertyNames);
            return todoBook;
        }
    
        @Override
        public Book convert(BookDTO bookDTO) {
            return null;
        }
    }
}
