package io.sample.blog.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    
    @GetMapping("/")
    public String index() {
//        if (blog == null) {
//            throw new NotFoundException("Blog not exists...");
//        }
        return "index";
    }
    
    @GetMapping("/blog")
    public String blog() {
        return "blog";
    }
}
