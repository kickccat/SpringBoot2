package io.sample.springboot_demo.domain;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "book")
public class Book {
    
    private String name;
    
    private String author;
    
    private String isbn;
    
    private String description;
}
