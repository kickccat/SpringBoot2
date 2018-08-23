package io.sample.springbootdeep.controller;

import io.sample.springbootdeep.domain.User;
import io.sample.springbootdeep.domain.UserRepository;
import io.sample.springbootdeep.form.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    
    private final UserRepository userRepository;
    
    @Autowired
    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @GetMapping("/register")
    public String register() {
        return "register";
    }
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @PostMapping("/register")
    public String registerReceiver(UserForm userForm) {
        User user = userForm.convertToUser();
        userRepository.save(user);
        return "redirect:/login";
    }
}
