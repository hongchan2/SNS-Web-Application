package com.hongchan.snsspringboot.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    UserAccountService userAccountService;

    @GetMapping("/user/login")
    public String loginForm() {
        return "user/login";
    }

    @GetMapping("/user/signup")
    public String signup() {
        return "user/signup";
    }

    @PostMapping("/user/signup-process")
    public String signupProcess(@ModelAttribute User user) {
        userAccountService.signUp(user);

        return "redirect:/user/login";
    }
}
