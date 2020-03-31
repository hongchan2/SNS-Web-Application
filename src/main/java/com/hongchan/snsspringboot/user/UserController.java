package com.hongchan.snsspringboot.user;

import com.hongchan.snsspringboot.follow.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    FollowService followService;

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

    @GetMapping("/user/{username}")
    public String userProfile(@AuthUser User user, @PathVariable String username,
                              Model model) {

        if(user == null) {
            return "redirect:/";
        }

        boolean isLoginUser = user.getUsername().equals(username);
        boolean isFollowingUser = followService.isFollowing(user.getUsername(), username);

        model.addAttribute("isLoginUser", isLoginUser);
        model.addAttribute("isFollowingUser", isFollowingUser);
        model.addAttribute("user", user);
        model.addAttribute("profileName", username);
        model.addAttribute("follower", followService.getFollowerList(username));
        model.addAttribute("following", followService.getFollowingList(username));

        return "/user/profile";
    }

}
