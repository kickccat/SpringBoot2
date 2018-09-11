package io.sample.blog.dao;

import io.sample.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    
    User findByUsernameAndPassword(String username, String password);
}
