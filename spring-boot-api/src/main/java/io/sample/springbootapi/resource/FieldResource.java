package io.sample.springbootapi.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class FieldResource {
    
    private String resource;
    
    private String field;
    
    private String code;
    
    private String message;
}
