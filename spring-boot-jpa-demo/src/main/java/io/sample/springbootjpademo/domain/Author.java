package io.sample.springbootjpademo.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Author {
    
    @Id
    @GeneratedValue(generator = "native", strategy = GenerationType.AUTO)
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private String nickname;
    private String phone;
    
    @Temporal(TemporalType.DATE)
    private Date signDate;
    
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}) // 级联保存,更新和删除
    private Wallet wallet;
}
