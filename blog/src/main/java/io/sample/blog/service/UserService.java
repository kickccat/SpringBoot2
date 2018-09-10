package io.sample.blog.service;

import io.sample.blog.model.User;

public interface UserService {
    
    User checkUser(String username, String password);
}
