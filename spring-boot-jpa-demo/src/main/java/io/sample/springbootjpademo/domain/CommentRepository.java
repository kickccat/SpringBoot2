package io.sample.springbootjpademo.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    
    @Transactional
    @Modifying
    @Query("DELETE FROM Comment c WHERE c.id = ?1")
    void deleteById(Long id);
}
