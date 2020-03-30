package com.hongchan.snsspringboot.timeline;

import com.hongchan.snsspringboot.board.Board;
import com.hongchan.snsspringboot.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimelineService {

    @Autowired
    TimelineRepository timelineRepository;

    public List<Timeline> getTimeline(User user) {
        final List<Timeline> timeline = timelineRepository.findByUser(user);
        return timeline;
    }

    public void addTimeline(Board board, User user) {
        Timeline timeline = new Timeline();
        timeline.setBoard(board);
        timeline.setUser(user);
        timelineRepository.save(timeline);
    }
}
