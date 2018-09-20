package io.sample.blog.service;

import io.sample.blog.dao.CommentRepository;
import io.sample.blog.model.Comment;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    
    private final CommentRepository commentRepository;
    private List<Comment> tempReplies = new ArrayList<>(); // Temporary store the children comments
    
    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    
    @Override
    public List<Comment> listCommentsByBlogId(Long blogId) {
        Sort sort = Sort.by("createTime");
        List<Comment> comments = commentRepository.findByBlogIdAndParentCommentNull(blogId, sort);
        return eachComment(comments);
    }
    
    // Insert children comments to the parent comment
    private List<Comment> eachComment(List<Comment> comments) {
        List<Comment> commentsView = new ArrayList<>();
        for (Comment comment : comments) {
            Comment comment1 = new Comment();
            BeanUtils.copyProperties(comment, comment1);
            commentsView.add(comment1);
        }
        combineChildren(commentsView);
        return commentsView;
    }
    
    // Merge the children comments to the first child comment
    private void combineChildren(List<Comment> commentsView) {
        for (Comment comment : commentsView) {
            List<Comment> childrenComments = comment.getChildrenComments();
            for (Comment reply : childrenComments) {
                recursive(reply);
            }
            comment.setChildrenComments(tempReplies);
            tempReplies = new ArrayList<>();
        }
    }
    
    // Find all children comments recursively
    private void recursive(Comment reply) {
        tempReplies.add(reply);
        if (reply.getChildrenComments().size() > 0) {
            List<Comment> replies = reply.getChildrenComments();
            for (Comment comment : replies) {
                tempReplies.add(comment);
                if (comment.getChildrenComments().size() > 0) {
                    recursive(comment);
                }
            }
        }
    }
    
    @Override
    public Comment saveComment(Comment comment) {
        Long parentCommentId = comment.getParentComment().getId();
        Comment co = commentRepository.findById(parentCommentId).orElse(null);
        if (parentCommentId != -1 && co != null) {
            comment.setParentComment(co);
        } else {
            comment.setParentComment(null);
        }
        comment.setCreateTime(new Date());
        return commentRepository.save(comment);
    }
}
