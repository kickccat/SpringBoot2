package io.sample.blog.service;

import io.sample.blog.dao.BlogRepository;
import io.sample.blog.exceptions.NotFoundException;
import io.sample.blog.model.Blog;
import io.sample.blog.model.Type;
import io.sample.blog.searchvo.BlogQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BlogServiceImpl implements BlogService {
    
    private final BlogRepository blogRepository;
    
    @Autowired
    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }
    
    @Override
    public Blog getBlog(Long id) {
        return blogRepository.findById(id).orElse(null);
    }
    
    @Override
    public Page<Blog> listBlogs(Pageable pageable, BlogQuery blog) {
        return blogRepository.findAll((Specification<Blog>) (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!StringUtils.isEmpty(blog.getTitle())) {
                predicates.add(criteriaBuilder.like(root.get("title"), "%" + blog.getTitle() + "%"));
            }
            if (blog.getTypeId() != null) {
                predicates.add(criteriaBuilder.equal(root.<Type>get("type").get("id"), blog.getTypeId()));
            }
            if (blog.isRecommendable()) {
                predicates.add(criteriaBuilder.equal(root.<Boolean>get("recommendable"), blog.isRecommendable()));
            }
            criteriaQuery.where(predicates.toArray(new Predicate[0]));
            return null;
        }, pageable);
    }
    
    @Override
    public Blog saveBlog(Blog blog) {
        return blogRepository.save(blog);
    }
    
    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Optional<Blog> blog1 = blogRepository.findById(id);
        if (!blog1.isPresent()) {
            throw new NotFoundException("该博客不存在");
        }
        BeanUtils.copyProperties(blog, blog1.get());
        return blogRepository.save(blog1.get());
    }
    
    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
}
