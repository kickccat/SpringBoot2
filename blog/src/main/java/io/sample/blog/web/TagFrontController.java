package io.sample.blog.web;

import io.sample.blog.model.Tag;
import io.sample.blog.service.BlogService;
import io.sample.blog.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TagFrontController {
    
    private final BlogService blogService;
    private final TagService tagService;
    
    @Autowired
    public TagFrontController(BlogService blogService, TagService tagService) {
        this.blogService = blogService;
        this.tagService = tagService;
    }
    
    @GetMapping("/tags/{id}")
    public String tags(@PathVariable(name = "id") Long id,
                        @PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        Model model) {
        List<Tag> tags = tagService.listTagTop(1000);
        if (id == -1) {
            id = tags.get(0).getId();
        }
        model.addAttribute("tags", tags);
        model.addAttribute("page", blogService.listBlogs(id, pageable));
        model.addAttribute("activeTagId", id);
        return "tags";
    }
}
