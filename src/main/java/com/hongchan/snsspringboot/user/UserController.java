package com.hongchan.snsspringboot.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @GetMapping("/user/{username}")
    public String userProfile(@AuthUser User user, @PathVariable String username,
                              Model model) {
        /*
            사용자 이름 / 팔로우 버튼 (본인이면 없어야 함)
            팔로워 수 , 팔로잉 수

            [게시글]

            1. username으로 User 객체 가져오기
            2. User로 팔로워, 팔로잉 객체 가져오기
            3. User로 TimelineBoard 가져오기 - 추후에 추가하기로
         */

//        userAccountService.getFollo

        model.addAttribute("user", user);
        model.addAttribute("follwer");
        model.addAttribute("following");

        return "/user/profile";
    }

}
