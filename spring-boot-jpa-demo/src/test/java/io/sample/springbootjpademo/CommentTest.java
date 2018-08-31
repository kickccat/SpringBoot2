package io.sample.springbootjpademo;

import io.sample.springbootjpademo.domain.Article;
import io.sample.springbootjpademo.domain.Comment;
import io.sample.springbootjpademo.service.ArticleService;
import io.sample.springbootjpademo.service.CommentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(value = SpringRunner.class)
@SpringBootTest
public class CommentTest {
    
    @Autowired
    private CommentService commentService;
    
    @Autowired
    private ArticleService articleService;
    
    @Test
    public void saveCommentTest() {
    
        Article article = articleService.findArticleById(3L);
        
        Comment comment = new Comment();
        comment.setContent("有没有九阴真经？");
        comment.setArticle(article);
    
        commentService.saveComment(comment);
    }
    
    @Test
    public void deleteCommentTest() {
        commentService.deleteComment(8L);
    }
}