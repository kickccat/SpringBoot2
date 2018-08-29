package io.sample.springbootapi.handler;

import io.sample.springbootapi.exception.InvalidRequestException;
import io.sample.springbootapi.exception.NotFoundException;
import io.sample.springbootapi.resource.ErrorResource;
import io.sample.springbootapi.resource.FieldResource;
import io.sample.springbootapi.resource.InvalidErrorResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class APIExceptionHandler {
    
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFound(RuntimeException e) {
        ErrorResource errorResource = new ErrorResource(e.getMessage());
        ResponseEntity result = new ResponseEntity<>(errorResource, HttpStatus.NOT_FOUND);
        log.warn("Return Content: {}", result);
        return result;
    }
    
    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<?> handleInvalidRequest(InvalidRequestException e) {
        Errors errors = e.getErrors();
        List<FieldResource> fieldResources = new ArrayList<>();
        List<FieldError> fieldErrors = errors.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            FieldResource fieldResource = new FieldResource(fieldError.getObjectName(), fieldError.getField(), fieldError.getCode(), fieldError.getDefaultMessage());
            fieldResources.add(fieldResource);
        }
        InvalidErrorResource errorResource = new InvalidErrorResource(e.getMessage(), fieldResources);
        ResponseEntity result = new ResponseEntity<>(errorResource, HttpStatus.BAD_REQUEST);
        log.warn("Return Content: {}", result);
        return result;
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        log.error("Error: {}", e);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
