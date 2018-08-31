package io.sample.springbootjpademo.service;

import io.sample.springbootjpademo.domain.Comment;
import io.sample.springbootjpademo.domain.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentServiceImpl implements CommentService {
    
    private CommentRepository commentRepository;
    
    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    
    @Override
    @Transactional
    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }
    
    @Override
    @Transactional
    public void deleteComment(Long id) {
        commentRepository.findById(id).ifPresent(Comment::clearComment);
        commentRepository.deleteById(id);
    }
}
