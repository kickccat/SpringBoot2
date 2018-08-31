package io.sample.springbootjpademo.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Data
public class Comment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String content;
    
    @ManyToOne
    private Article article;
    
    public Comment() {
    }
    
    public Comment(String content) {
        this.content = content;
    }
    
    public void clearComment() {
        this.getArticle().getComments().remove(this);
    }
}
