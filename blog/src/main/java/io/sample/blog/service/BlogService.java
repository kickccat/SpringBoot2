package io.sample.blog.service;

import io.sample.blog.model.Blog;
import io.sample.blog.searchvo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BlogService {
    
    Blog getBlog(Long id);
    
    Page<Blog> listBlogs(Pageable pageable, BlogQuery blog);
    
    Blog saveBlog(Blog blog);
    
    Blog updateBlog(Long id, Blog blog);
    
    void deleteBlog(Long id);
}
