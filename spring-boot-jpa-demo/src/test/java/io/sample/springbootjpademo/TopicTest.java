package io.sample.springbootjpademo;

import io.sample.springbootjpademo.domain.Topic;
import io.sample.springbootjpademo.service.ArticleService;
import io.sample.springbootjpademo.service.TopicService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TopicTest {
    
    @Autowired
    private TopicService topicService;
    
    @Autowired
    private ArticleService articleService;
    
    @Test
    public void saveTopic() {
        Topic topic = new Topic();
        topic.setTheme("脑洞");
        
        topicService.saveTopic(topic);
    }
    
    @Test
    public void updateTopic() {
        Topic topic = topicService.findTopicById(1L);
        topic.setTheme("秘籍");
        
        topicService.saveTopic(topic);
    }
}
