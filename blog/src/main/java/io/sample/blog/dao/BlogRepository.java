package io.sample.blog.dao;

import io.sample.blog.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {
    
    @Query("select b from Blog b where b.recommendable = true")
    List<Blog> findTop(Pageable pageable);
    
    @Query("select b from Blog b where b.title like ?1 or b.content like ?1 or b.description like ?1")
    Page<Blog> findTopByQuery(String query, Pageable pageable);
    
    @Transactional
    @Modifying
    @Query("UPDATE Blog b set b.views = b.views+1 WHERE b.id = ?1")
    int updateViews(Long id);
}
