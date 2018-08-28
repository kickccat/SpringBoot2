package io.sample.springbootapi.dto;

import io.sample.springbootapi.domain.Book;
import io.sample.springbootapi.util.BookUtils;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class BookDTO {
    
    @NotNull
    private Integer status;
    
    @NotBlank
    private String name;
    
    @NotBlank
    private String author;
    
    @Length(max = 20)
    private String description;
    
    public void convertBook(Book todoBook) {
        new BookConvert().convert(this, todoBook);
    }
    
    public Book convertBook() {
        return new BookConvert().convert(this);
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
            Book book = new Book();
            BeanUtils.copyProperties(bookDTO, book);
            return book;
        }
    }
}
