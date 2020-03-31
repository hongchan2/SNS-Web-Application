package com.hongchan.snsspringboot.follow;

import com.hongchan.snsspringboot.user.AuthUser;
import com.hongchan.snsspringboot.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FollowController {

    @Autowired
    FollowService followService;

    @PostMapping("/follow/{username}")
    public @ResponseBody FollowCntInfo followUser(@AuthUser User user,
                                           @PathVariable String username) {
        System.out.println("============");
        System.out.println(username);
        System.out.println("============");

        followService.follow(user, username);

        return followService.getFollowCnt(username);
    }

    // make unfollow postmapping


}
