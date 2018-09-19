package io.sample.blog.web;

import io.sample.blog.model.Comment;
import io.sample.blog.service.BlogService;
import io.sample.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CommentController {
    
    private final CommentService commentService;
    private final BlogService blogService;
    
    @Value("${comment.avatar}")
    private String avatar;
    
    @Autowired
    public CommentController(CommentService commentService, BlogService blogService) {
        this.commentService = commentService;
        this.blogService = blogService;
    }
    
    @GetMapping("/comments/{blogId}")
    public String comments(@PathVariable(name = "blogId") Long blogId, Model model) {
        model.addAttribute("comments", commentService.listCommentsByBlogId(blogId));
        return "blog :: commentList";
    }
    
    @PostMapping("/comments")
    public String post(Comment comment) {
        Long blogId = comment.getBlog().getId();
        comment.setBlog(blogService.getBlog(blogId));
        comment.setAvatar(avatar);
        commentService.saveComment(comment);
        return "redirect:/comments/" + blogId;
    }
}
