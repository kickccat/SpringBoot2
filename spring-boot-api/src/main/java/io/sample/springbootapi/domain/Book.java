package io.sample.springbootapi.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Book {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Set as Long to check if id == null, else with long default as 0
    
    private Integer status;
    
    private String name;
    
    private String author;
    
    private String description;
}