package io.sample.blog.web.admin;

import io.sample.blog.searchvo.BlogQuery;
import io.sample.blog.service.BlogService;
import io.sample.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class BlogController {
    
    private final BlogService blogService;
    
    private final TypeService typeService;
    
    @Autowired
    public BlogController(BlogService blogService, TypeService typeService) {
        this.blogService = blogService;
        this.typeService = typeService;
    }
    
    @GetMapping("/blogs")
    public String blogs(Model model, @PageableDefault(size = 3, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        BlogQuery blog) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("page", blogService.listBlogs(pageable, blog));
        return "admin/blogs";
    }
    
    @PostMapping("/blogs/search")
    public String search(Model model, @PageableDefault(size = 3, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        BlogQuery blog) {
        model.addAttribute("page", blogService.listBlogs(pageable, blog));
        return "admin/blogs :: blogList";
    }
}
