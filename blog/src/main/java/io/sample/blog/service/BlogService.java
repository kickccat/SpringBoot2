package io.sample.blog.service;

import io.sample.blog.model.Blog;
import io.sample.blog.searchvo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogService {
    
    Blog getBlog(Long id);
    
    Blog getAndConvert(Long id);
    
    Page<Blog> listBlogs(Pageable pageable, BlogQuery blog);
    
    Page<Blog> listBlogs(Pageable pageable);
    
    Page<Blog> listBlogs(Long tagId, Pageable pageable);
    
    Page<Blog> listBlogs(String query, Pageable pageable);
    
    List<Blog> listBlogTop(Integer size);
    
    Blog saveBlog(Blog blog);
    
    Blog updateBlog(Long id, Blog blog);
    
    void deleteBlog(Long id);
}
