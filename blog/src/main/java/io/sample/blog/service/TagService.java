package io.sample.blog.service;

import io.sample.blog.model.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {
    
    Tag saveTag(Tag tag);
    
    Tag getTagById(Long id);
    
    Tag getTagByName(String name);
    
    Page<Tag> listTag(Pageable pageable);
    
    List<Tag> listTag();
    
    List<Tag> listTagPop(Integer size);
    
    List<Tag> listTag(String ids);
    
    Tag updateTag(Long id, Tag tag);
    
    void deleteTag(Long id);
}
