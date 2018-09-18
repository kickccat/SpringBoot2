package io.sample.blog.web.admin;

import io.sample.blog.model.Blog;
import io.sample.blog.model.User;
import io.sample.blog.searchvo.BlogQuery;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class BlogController {
    
    private static final String PUBLISHER = "admin/blog-publisher";
    public static final String LIST = "admin/blogs";
    private static final String REDIRECT_LIST = "redirect:admin/blogs";
    
    private final BlogService blogService;
    private final TypeService typeService;
    private final TagService tagService;
    
    @Autowired
    public BlogController(BlogService blogService, TypeService typeService, TagService tagService) {
        this.blogService = blogService;
        this.typeService = typeService;
        this.tagService = tagService;
    }
    
    @GetMapping("/blogs")
    public String blogs(Model model, @PageableDefault(size = 3, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                        BlogQuery blog) {
        model.addAttribute("tags", tagService.listTag());
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
    
    @GetMapping("/blogs/publisher")
    public String publish(Model model) {
        model.addAttribute("types", typeService.listType());
        model.addAttribute("tags", tagService.listTag());
        model.addAttribute("blog", new Blog());
        return PUBLISHER;
    }
    
    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session) {
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getTypeById(blog.getType().getId()));
        blog.setTags(tagService.listTag(blog.getTagIds()));
        
        Blog blog1 = blogService.saveBlog(blog);
        if (blog1 == null) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return REDIRECT_LIST;
    }
}
