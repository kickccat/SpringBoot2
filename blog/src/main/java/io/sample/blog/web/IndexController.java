package io.sample.blog.web;

import io.sample.blog.service.BlogService;
import io.sample.blog.service.TagService;
import io.sample.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    
    private final BlogService blogService;
    private final TypeService typeService;
    private final TagService tagService;
    
    @Autowired
    public IndexController(BlogService blogService, TypeService typeService, TagService tagService) {
        this.blogService = blogService;
        this.typeService = typeService;
        this.tagService = tagService;
    }
    
    @GetMapping("/")
    public String index(Model model, @PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("page", blogService.listBlogs(pageable));
        model.addAttribute("types", typeService.listTypeTop(6));
        model.addAttribute("tags", tagService.listTagTop(10));
        model.addAttribute("recommendableBlogs", blogService.listBlogTop(8));
        return "index";
    }
    
    @PostMapping("/search")
    public String search(@PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable, @RequestParam String query,
                         Model model) {
        model.addAttribute("page", blogService.listBlogs("%" + query + "%", pageable));
        model.addAttribute("query", query);
        return "search";
    }
    
    @GetMapping("/blog/{id}")
    public String blog() {
        return "blog";
    }
}
