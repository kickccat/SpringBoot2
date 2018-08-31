package io.sample.springbootjpademo.service;

import io.sample.springbootjpademo.domain.Article;
import io.sample.springbootjpademo.domain.ArticleRepository;
import io.sample.springbootjpademo.domain.Topic;
import io.sample.springbootjpademo.domain.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TopicServiceImpl implements TopicService {
    
    private final ArticleRepository articleRepository;
    private final TopicRepository topicRepository;
    
    @Autowired
    public TopicServiceImpl(ArticleRepository articleRepository, TopicRepository topicRepository) {
        this.articleRepository = articleRepository;
        this.topicRepository = topicRepository;
    }
    
    @Override
    public Topic saveTopic(Topic topic) {
        return topicRepository.save(topic);
    }
    
    @Override
    public Topic updateTopic(Topic topic) {
        return topicRepository.save(topic);
    }
    
    @Override
    public Topic findTopicById(Long id) {
        return topicRepository.findById(id).orElse(null);
    }
    
    @Override
    public Topic includeArticle(Long topicId, Long articleId) {
        Topic topic = topicRepository.getOne(topicId);
        Article article = articleRepository.getOne(articleId);
    
        topic.getArticles().add(article);
        return topic;
    }
    
    @Override
    public Topic excludeArticle(Long topicId, Long articleId) {
        Topic topic = topicRepository.getOne(topicId);
        Article article = articleRepository.getOne(articleId);
    
        topic.getArticles().remove(article);
        return topic;
    }
    
    @Override
    public void deleteTopic(Long id) {
        topicRepository.deleteById(id);
    }
}
