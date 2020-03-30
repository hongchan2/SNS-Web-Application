package com.hongchan.snsspringboot.timeline;

import com.hongchan.snsspringboot.user.AuthUser;
import com.hongchan.snsspringboot.user.User;
import com.hongchan.snsspringboot.user.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TimelineController {

    @Autowired
    TimelineService timelineService;

    @GetMapping("/")
    public String root() {
        return "redirect:/timeline";
    }

    @GetMapping("/timeline")
    public String home(@AuthUser User user, Model model) {
        final List<TimelineBoard> boardList = timelineService.getBoardList(user);

        // TODO: 20개만 가져오고, 나머지는 AJAX 통신으로 갱신하기

        model.addAttribute("user", user);
        model.addAttribute("boardlist", boardList);
        return "timeline";
    }
}
