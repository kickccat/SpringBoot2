package io.sample.springbootjpademo.service;

import io.sample.springbootjpademo.domain.Topic;

public interface TopicService {
    
    Topic saveTopic(Topic topic);
    
    Topic updateTopic(Topic topic);
    
    Topic findTopicById(Long id);
    
    Topic includeArticle(Long topicId, Long articleId);
    
    Topic excludeArticle(Long topicId, Long articleId);
    
    void deleteTopic(Long id);
}
