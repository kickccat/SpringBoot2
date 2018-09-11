package io.sample.blog.web.admin;

import io.sample.blog.model.Type;
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
public class TypeController {
    
    private final TypeService typeService;
    
    @Autowired
    public TypeController(TypeService typeService) {
        this.typeService = typeService;
    }
    
    @GetMapping("/types")
    public String types(@PageableDefault(size = 5, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        
        model.addAttribute("page", typeService.listType(pageable));
        return "admin/types";
    }
    
    @GetMapping("/types/addType")
    public String add() {
        return "admin/type-add";
    }
    
    @PostMapping("/types")
    public String post(Type type) {
        Type t = typeService.saveType(type);
        if (t == null) {
        
        } else {
        
        }
        return "redirect:/admin/types";
    }
}
