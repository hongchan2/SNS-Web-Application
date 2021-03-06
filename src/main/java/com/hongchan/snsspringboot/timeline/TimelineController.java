package com.hongchan.snsspringboot.timeline;

import com.hongchan.snsspringboot.user.AuthUser;
import com.hongchan.snsspringboot.user.User;
import com.hongchan.snsspringboot.user.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class TimelineController {

    @Autowired
    TimelineService timelineService;

    @Autowired
    UserAccountService userAccountService;

    @GetMapping("/")
    public String root() {
        return "redirect:/timeline";
    }

    @GetMapping("/timeline")
    public String timeline(@AuthUser User user, Model model) {
        timelineService.beforeAccessTimeline(user);
        List<TimelineBoard> timelineBoardList = timelineService.getTimelineBoardList(user.getUsername(),
                PageRequest.of(0, 10, Sort.Direction.DESC, "Board_dateTime"));
        List<User> randomUserList = userAccountService.getFiveRandomUser(user);

        model.addAttribute("randomUserList", randomUserList);
        model.addAttribute("user", user);
        model.addAttribute("boardList", timelineBoardList);
        return "timeline";
    }

    @GetMapping("/timeline/scroll")
    public @ResponseBody List<TimelineBoard> timelineScroll(@AuthUser User user,
                                               Pageable pageable) {
        return timelineService.getTimelineBoardList(user.getUsername(), pageable);
    }
}
