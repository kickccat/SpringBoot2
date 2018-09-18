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
    
    @Lob
    @Basic(fetch = FetchType.LAZY)
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
    
    @Transient
    private String tagIds;
    
    public void init() {
        this.tagIds = tagsToIds(this.getTags());
    }
    
    private String tagsToIds(List<Tag> tags) {
        if (!tags.isEmpty()) {
            StringBuilder ids = new StringBuilder();
            boolean flag = false;
            for (Tag tag : tags) {
                if (flag) {
                    ids.append(",");
                } else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        } else {
            return tagIds;
        }
    }
}
