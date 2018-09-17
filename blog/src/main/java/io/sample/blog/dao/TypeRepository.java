package io.sample.blog.dao;

import io.sample.blog.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<Type, Long> {
    
    Type findByName(String name);
}
