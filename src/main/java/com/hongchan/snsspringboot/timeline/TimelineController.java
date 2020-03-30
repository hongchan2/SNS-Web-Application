package com.hongchan.snsspringboot.timeline;

import com.hongchan.snsspringboot.user.AuthUser;
import com.hongchan.snsspringboot.user.User;
import com.hongchan.snsspringboot.user.UserAccount;
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
    public String home(@AuthUser User user, Model model) {
        System.out.println("[Current User] : " + user.getUsername());
        model.addAttribute("user", user);
        return "timeline";
    }
}
