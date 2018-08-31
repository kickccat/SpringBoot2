package io.sample.springbootjpademo.service;

import io.sample.springbootjpademo.domain.Article;

public interface ArticleService {
    
    Article saveArticle(Article article);
    
    Article updateArticle(Article article);
    
    Article findArticleById(Long id);
    
    void deleteArticleById(Long id);
}
