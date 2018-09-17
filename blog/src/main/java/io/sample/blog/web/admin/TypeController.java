package io.sample.blog.web.admin;

import io.sample.blog.model.Type;
import io.sample.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

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
    
    @GetMapping("/types/input")
    public String add(Model model) {
        model.addAttribute("type", new Type());
        return "admin/type-input";
    }
    
    @GetMapping("/types/{id}/input")
    public String editType(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("type", typeService.getTypeById(id));
        return "admin/type-input";
    }
    
    @PostMapping("/types")
    public String post(@Valid Type type, BindingResult result, RedirectAttributes attributes) {
        if (existType(type, result))
            return "admin/type-input";
        Type t = typeService.saveType(type);
        if (t == null) {
            attributes.addFlashAttribute("message", "分类不能为空");
        } else {
            attributes.addFlashAttribute("message", "新增分类");
        }
        return "redirect:/admin/types";
    }
    
    @PostMapping("/types/{id}")
    public String editPost(@Valid Type type, BindingResult result, @PathVariable(name = "id") Long id, RedirectAttributes attributes) {
        if (existType(type, result))
            return "admin/type-input";
        Type t = typeService.updateTypeById(id, type);
        if (t == null) {
            attributes.addFlashAttribute("message", "分类不能为空, 更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/types";
    }
    
    private boolean existType(@Valid Type type, BindingResult result) {
        Type existType = typeService.getTypeByName(type.getName());
        if (existType != null) {
            result.rejectValue("name", "nameError", "分类已存在");
        }
        return result.hasErrors();
    }
}
