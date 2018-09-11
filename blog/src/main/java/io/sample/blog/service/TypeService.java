package io.sample.blog.service;

import io.sample.blog.model.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TypeService {
    
    Type saveType(Type type);
    
    Type getTypeById(Long id);
    
    Page<Type> listType(Pageable pageable);
    
    Type updateTypeById(Long id, Type type);
    
    void deleteTypeById(Long id);
}
