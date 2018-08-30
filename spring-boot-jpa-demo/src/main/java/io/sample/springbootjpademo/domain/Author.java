package io.sample.springbootjpademo.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Author {
    
    @Id
    @GeneratedValue
    private Long id;
    private String nickname;
    private String phone;
    
    @Temporal(TemporalType.DATE)
    private Date signDate;
}
