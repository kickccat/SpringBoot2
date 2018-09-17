package io.sample.blog.service;

import io.sample.blog.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {
    
    Type saveType(Type type);
    
    Type getTypeById(Long id);
    
    Type getTypeByName(String name);
    
    Page<Type> listType(Pageable pageable);
    
    List<Type> listType();
    
    Type updateTypeById(Long id, Type type);
    
    void deleteTypeById(Long id);
}
