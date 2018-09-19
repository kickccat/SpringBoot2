package io.sample.blog.service;

import io.sample.blog.dao.BlogRepository;
import io.sample.blog.exceptions.NotFoundException;
import io.sample.blog.model.Blog;
import io.sample.blog.model.Type;
import io.sample.blog.searchvo.BlogQuery;
import io.sample.blog.utils.MarkdownUtils;
import io.sample.blog.utils.MyBeanUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
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
    public Blog getAndConvert(Long id) {
        Blog blog = blogRepository.findById(id).orElse(null);
        if (blog == null) {
            throw new NotFoundException("Blog not exists...");
        }
        Blog blog1 = new Blog();
        BeanUtils.copyProperties(blog, blog1);
        String content = blog1.getContent();
        blog1.setContent(MarkdownUtils.markdownToHtmlExtensions(content));
        return blog1;
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
    public Page<Blog> listBlogs(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }
    
    @Override
    public Page<Blog> listBlogs(String query, Pageable pageable) {
        return blogRepository.findTopByQuery(query, pageable);
    }
    
    @Override
    public List<Blog> listBlogTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "updateTime");
        Pageable pageable = PageRequest.of(0, size, sort);
        return blogRepository.findTop(pageable);
    }
    
    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        if (blog.getId() == null) {
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
        } else {
            blog.setUpdateTime(new Date());
        }
        return blogRepository.save(blog);
    }
    
    @Transactional
    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Optional<Blog> blog1 = blogRepository.findById(id);
        if (!blog1.isPresent()) {
            throw new NotFoundException("该博客不存在");
        }
        BeanUtils.copyProperties(blog, blog1.get(), MyBeanUtils.getNullPropertyNames(blog)); // Not transfer the null value fields
        blog1.get().setUpdateTime(new Date());
        return blogRepository.save(blog1.get());
    }
    
    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }
}
