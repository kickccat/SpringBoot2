package io.sample.springbootjpademo.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Topic {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    
    private String theme;
    
    @ManyToMany
    @JoinTable(name = "t_topic_article", joinColumns = @JoinColumn(name = "topic_id", referencedColumnName = "id"), inverseJoinColumns =
    @JoinColumn(name = "article_id"))
    private List<Article> articles = new ArrayList<>();
}
