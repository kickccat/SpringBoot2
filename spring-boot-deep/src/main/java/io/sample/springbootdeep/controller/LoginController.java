package io.sample.springbootdeep.controller;

import io.sample.springbootdeep.domain.User;
import io.sample.springbootdeep.domain.UserRepository;
import io.sample.springbootdeep.form.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class LoginController {
    
    private final UserRepository userRepository;
    
    @Autowired
    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "register";
    }
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @PostMapping("/register")
    public String registerReceiver(@Valid UserForm userForm, BindingResult result) {
        
        if (!userForm.identityPassword()) {
            result.rejectValue("confirmPassword", "identity error", "两次密码不一致");
        }
        if (result.hasErrors()) {
            return "register";
        }
        
        User user = userForm.convertToUser();
        userRepository.save(user);
        return "redirect:/login";
    }
}
