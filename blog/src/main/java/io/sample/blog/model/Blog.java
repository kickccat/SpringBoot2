package io.sample.blog.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "t_blog")
public class Blog {
    
    @Id
    @GeneratedValue
    private Long id;
    
    private String title;
    private String content;
    private String firstImg;
    private String flag;
    private Integer views;
    private boolean donation;
    private boolean shareRight;
    private boolean commendable;
    private boolean publishable;
    private boolean recommendable;
    
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createTime;
    
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date updateTime;
    
    @ManyToOne
    private Type type;
    
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Tag> tags = new ArrayList<>();
    
    @ManyToOne
    private User user;
    
    @OneToMany(mappedBy = "blog")
    private List<Comment> comments = new ArrayList<>();
}
