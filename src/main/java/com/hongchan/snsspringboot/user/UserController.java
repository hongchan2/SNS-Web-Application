package com.hongchan.snsspringboot.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserRespository userRespository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @GetMapping("/user/login")
    public String loginForm() {
        return "user/login";
    }

    @PostMapping("/user/login-process")
    public String loginProcess(@ModelAttribute User user) {


        return "redirect:/home";
    }

    @GetMapping("/user/signup")
    public String signup() {
        return "user/signup";
    }

    @PostMapping("/user/signup-process")
    public String signupProcess(@ModelAttribute User user) {
        String encPassword = encoder.encode(user.getPassword());
        user.setPassword(encPassword);

        userRespository.save(user);

        return "redirect:/user/login";
    }
}
