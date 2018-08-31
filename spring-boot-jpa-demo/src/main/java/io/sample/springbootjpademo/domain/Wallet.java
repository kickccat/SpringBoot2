package io.sample.springbootjpademo.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
public class Wallet {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private BigDecimal balance;
    
    @OneToOne(mappedBy = "wallet")
    private Author author;
    
    public Wallet(BigDecimal balance) {
        this.balance = balance;
    }
}
