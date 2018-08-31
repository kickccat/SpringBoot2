package io.sample.springbootjpademo.service;

import io.sample.springbootjpademo.domain.Article;
import io.sample.springbootjpademo.domain.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {
    
    private final ArticleRepository articleRepository;
    
    @Autowired
    public ArticleServiceImpl(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }
    
    @Override
    public Article saveArticle(Article article) {
        return articleRepository.save(article);
    }
    
    @Override
    public Article updateArticle(Article article) {
        return articleRepository.save(article);
    }
    
    @Override
    public Article findArticleById(Long id) {
        return articleRepository.findById(id).orElse(null);
    }
    
    @Override
    public void deleteArticleById(Long id) {
        articleRepository.deleteById(id);
    }
}
