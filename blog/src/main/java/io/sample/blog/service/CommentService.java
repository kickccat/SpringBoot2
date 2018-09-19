package io.sample.blog.service;

import io.sample.blog.model.Comment;

import java.util.List;

public interface CommentService {
    
    List<Comment> listCommentsByBlogId(Long blogId);
    
    Comment saveComment(Comment comment);
}
