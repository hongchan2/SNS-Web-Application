package com.hongchan.snsspringboot.timeline;

import com.hongchan.snsspringboot.user.MyUserDetail;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TimelineController {

    @GetMapping("/")
    public String root() {
        return "redirect:/timeline";
    }

    @GetMapping("/timeline")
    public String home(@AuthenticationPrincipal MyUserDetail userDetail,
                       Model model) {

        System.out.println("[Current User] : " + userDetail.getUsername());
        model.addAttribute("user", userDetail.getUser());
        return "timeline";
    }
}
