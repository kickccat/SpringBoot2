package io.sample.blog.web;

import io.sample.blog.model.Type;
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
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TypeFrontController {
    
    private final BlogService blogService;
    private final TypeService typeService;
    
    @Autowired
    public TypeFrontController(BlogService blogService, TypeService typeService) {
        this.blogService = blogService;
        this.typeService = typeService;
    }
    
    @GetMapping("/types/{id}")
    public String types(@PathVariable(name = "id") Long id,
                        @PageableDefault(size = 5, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        Model model) {
        List<Type> types = typeService.listTypeTop(1000);
        if (id == -1) {
            id = types.get(0).getId();
        }
        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(id);
        model.addAttribute("types", types);
        model.addAttribute("page", blogService.listBlogs(pageable, blogQuery));
        model.addAttribute("activeTypeId", id);
        return "types";
    }
}
