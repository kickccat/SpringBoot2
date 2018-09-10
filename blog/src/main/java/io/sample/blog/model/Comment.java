package io.sample.blog.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "t_comment")
public class Comment {
    
    @Id
    @GeneratedValue
    private Long id;
    private String nickname;
    private String email;
    private String content;
    private String avatar;
    
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createTime;
    
    @ManyToOne
    private Blog blog;
    
    @OneToMany(mappedBy = "parentComment")
    private List<Comment> childrenComments = new ArrayList<>();
    
    @ManyToOne
    private Comment parentComment;
}
