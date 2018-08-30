package io.sample.springbootjpademo.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    
    List<Author> findByPhoneAndNickname(String phone, String nickname);
}
