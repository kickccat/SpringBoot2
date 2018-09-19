package io.sample.blog.service;

import io.sample.blog.dao.TagRepository;
import io.sample.blog.exceptions.NotFoundException;
import io.sample.blog.model.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {
    
    private final TagRepository tagRepository;
    
    @Autowired
    public TagServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }
    
    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return tagRepository.save(tag);
    }
    
    @Override
    public Tag getTagById(Long id) {
        return tagRepository.findById(id).orElse(null);
    }
    
    @Override
    public Tag getTagByName(String name) {
        return tagRepository.findTagByName(name);
    }
    
    @Override
    public Page<Tag> listTag(Pageable pageable) {
        return tagRepository.findAll(pageable);
    }
    
    @Override
    public List<Tag> listTag() {
        return tagRepository.findAll();
    }
    
    @Override
    public List<Tag> listTagTop(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "blogs.size");
        Pageable pageable = PageRequest.of(0, size, sort);
        return tagRepository.findTopByPage(pageable);
    }
    
    @Override
    public List<Tag> listTag(String ids) {
        
        return tagRepository.findAllById(stringToList(ids));
    }
    
    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Optional<Tag> optionalTag = tagRepository.findById(id);
        if (!optionalTag.isPresent()) {
            throw new NotFoundException("标签不存在");
        }
        BeanUtils.copyProperties(tag, optionalTag.get());
        return tagRepository.save(optionalTag.get());
    }
    
    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }
    
    private List<Long> stringToList(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            list = Arrays.stream(ids.split(",")).map(Long::parseLong).collect(Collectors.toCollection(ArrayList::new));
        }
        return list;
    }
}
