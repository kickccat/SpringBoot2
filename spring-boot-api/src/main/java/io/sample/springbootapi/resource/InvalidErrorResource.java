package io.sample.springbootapi.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class InvalidErrorResource {
    
    private String message;
    private Object errors;
}
