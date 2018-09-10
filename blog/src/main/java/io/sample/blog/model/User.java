package io.sample.blog.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "t_user")
public class User {
    
    @Id
    @GeneratedValue
    private Long id;
    private String nickname;
    private String username;
    private String email;
    private String password;
    private String avatar;
    private Integer type;
    
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createTime;
    
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date updateTime;
    
    @OneToMany(mappedBy = "user")
    private List<Blog> blogs = new ArrayList<>();
}
