package io.sample.blog.web.admin;

import io.sample.blog.model.Tag;
import io.sample.blog.service.TagService;
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
public class TagController {
    
    private final TagService tagService;
    
    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }
    
    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 3, sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        model.addAttribute("page", tagService.listTag(pageable));
        return "admin/tags";
    }
    
    @GetMapping("/tags/input")
    public String input(Model model) {
        model.addAttribute("tag", new Tag());
        return "admin/tag-input";
    }
    
    @GetMapping("/tags/{id}/edit")
    public String editedTag(@PathVariable(name = "id") Long id, Model model) {
        model.addAttribute("tag", tagService.getTagById(id));
        return "admin/tag-input";
    }
    
    @GetMapping("/tags/{id}/delete")
    public String deleteTag(@PathVariable(name = "id") Long id, RedirectAttributes attributes) {
        tagService.deleteTag(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/tags";
    }
    
    @PostMapping("/tags")
    public String post(@Valid Tag tag, BindingResult result, RedirectAttributes attributes) {
        if (existTag(tag, result)) {
            return "admin/tag-input";
        }
        Tag t = tagService.saveTag(tag);
        if (t == null) {
            attributes.addFlashAttribute("message", "标签不能为空");
        } else {
            attributes.addFlashAttribute("message", "新增标签");
        }
        return "redirect:/admin/tags";
    }
    
    @PostMapping("/tags/{id}")
    public String editPost(@Valid Tag tag, BindingResult result, @PathVariable(name = "id") Long id, RedirectAttributes attributes) {
        if (existTag(tag, result)) {
            return "admin/tag-input";
        }
        Tag t = tagService.updateTag(id, tag);
        if (t == null) {
            attributes.addFlashAttribute("message", "标签不能为空, 更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/tags";
    }
    
    private boolean existTag(@Valid Tag tag, BindingResult result) {
        Tag existTag = tagService.getTagByName(tag.getName());
        if (existTag != null) {
            result.rejectValue("name", "nameError", "标签已存在");
        }
        return result.hasErrors();
    }
}
