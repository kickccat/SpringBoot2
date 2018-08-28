package io.sample.springbootapi.resource;

import lombok.Getter;

public class ErrorResource {
    
    @Getter
    private String message;
    
    public ErrorResource(String message) {
        this.message = message;
    }
}
