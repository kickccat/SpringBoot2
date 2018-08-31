package io.sample.springbootjpademo;

import com.alibaba.fastjson.JSON;
import io.sample.springbootjpademo.domain.Article;
import io.sample.springbootjpademo.domain.Comment;
import io.sample.springbootjpademo.service.ArticleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(value = SpringRunner.class)
public class ArticleTest {
    
    @Autowired
    private ArticleService articleService;
    
    @Test
    public void saveArticle() {
        Article article = new Article();
        article.setTitle("关于神功");
        article.setContent("天下几大神功的分类。。。");
    
        Comment comment1 = new Comment("欲练神功");
        Comment comment = new Comment("必先自宫");
    
        article.addComment(comment);
        article.addComment(comment1);
    
        articleService.saveArticle(article);
    }
    
    @Test
    public void updateArticle() {
        Article article = articleService.findArticleById(3L);
        article.setContent("九阳神功。。。。");
    
        articleService.updateArticle(article);
    }
    
    @Test
    public void findArticle() {
        Article article = articleService.findArticleById(2L);
        System.out.println(JSON.toJSONString(article, true));
    }
    
    @Test
    public void deleteArticle() {
        articleService.deleteArticleById(1L);
    }
}
