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
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {
    
    private final UserRepository userRepository;
    
    @GetMapping("/")
    public String index() {
        return "index";
    }
    
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
    
    @PostMapping("/login")
    public String loginPost(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            return "index";
        }
        return "login";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
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
    
    @GetMapping("/exception")
    public String testException() {
        throw new RuntimeException("测试异常处理");
    }
}
