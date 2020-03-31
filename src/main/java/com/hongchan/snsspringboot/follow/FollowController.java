package com.hongchan.snsspringboot.follow;

import com.hongchan.snsspringboot.user.AuthUser;
import com.hongchan.snsspringboot.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FollowController {

    @Autowired
    FollowService followService;

//    @PostMapping("/follow/{username}")
//    public String followUser(@PathVariable String username) {
//        return "redirect:/follow/" + username + "-process";
//    }

    @GetMapping("/follow/{username}")
    public String followUserProcess(@AuthUser User user, @PathVariable String username) {
        followService.follow(user, username);

        return "redirect:/timeline";
    }


}
