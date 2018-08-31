package io.sample.springbootjpademo.service;

import io.sample.springbootjpademo.domain.Comment;

public interface CommentService {
    
    Comment saveComment(Comment comment);
    
    void deleteComment(Long id);
}
