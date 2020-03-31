package com.hongchan.snsspringboot.timeline;

import com.hongchan.snsspringboot.board.Board;
import com.hongchan.snsspringboot.board.CommentService;
import com.hongchan.snsspringboot.board.LikesService;
import com.hongchan.snsspringboot.user.AuthUser;
import com.hongchan.snsspringboot.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TimelineService {

    @Autowired
    TimelineRepository timelineRepository;

    @Autowired
    LikesService likesService;

    @Autowired
    CommentService commentService;

    public List<TimelineBoard> getTimelineBoardList(User user) {
        final List<Timeline> timeline = timelineRepository.findByUser(user);

        List<TimelineBoard> boardList = new ArrayList<>();
        for(int i = 0; i < timeline.size(); i++) {
            TimelineBoard board = new TimelineBoard();

            board.setUsername(timeline.get(i).getUser().getUsername());
            board.setBoard(timeline.get(i).getBoard());
            board.setLikes(likesService.getLikeList(board.getBoard()));
            board.setComments(commentService.getCommentList(board.getBoard()));

            boardList.add(board);
        }

        return boardList;
    }

    public void addTimeline(Board board, User user) {
        Timeline timeline = new Timeline();
        timeline.setBoard(board);
        timeline.setUser(user);
        timelineRepository.save(timeline);
    }
}
