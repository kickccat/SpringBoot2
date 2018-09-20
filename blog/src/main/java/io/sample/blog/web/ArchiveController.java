package io.sample.blog.web;

import io.sample.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ArchiveController {
    
    private final BlogService blogService;
    
    @Autowired
    public ArchiveController(BlogService blogService) {
        this.blogService = blogService;
    }
    
    @GetMapping("/archives")
    public String archives(Model model) {
        model.addAttribute("archiveMap", blogService.listArchives());
        model.addAttribute("blogCount", blogService.countBlog());
        return "archives";
    }
}
