package io.sample.blog.dao;

import io.sample.blog.model.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    
    Tag findTagByName(String name);
    
    @Query("SELECT t from Tag t")
    List<Tag> findTopByPage(Pageable pageable);
}
