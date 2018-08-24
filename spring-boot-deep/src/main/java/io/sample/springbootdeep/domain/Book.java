package io.sample.springbootdeep.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Book {
    
    @Id
    @GeneratedValue
    private long id;
    
    private Integer status;
    
    private String name;
    
    private String author;
    
    private String description;
}