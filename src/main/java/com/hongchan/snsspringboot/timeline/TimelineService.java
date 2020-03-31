package com.hongchan.snsspringboot.timeline;

import com.hongchan.snsspringboot.board.Board;
import com.hongchan.snsspringboot.board.CommentService;
import com.hongchan.snsspringboot.board.LikesService;
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

    public List<TimelineBoard> getTimelineBoardList(String username) {
        final List<Timeline> timelineList = timelineRepository.findByUser_UsernameOrderByBoard_DateTimeDesc(username);

        List<TimelineBoard> boardList = new ArrayList<>();
        for(Timeline timeline : timelineList){
            TimelineBoard timelineBoard = new TimelineBoard();

            timelineBoard.setBoard(timeline.getBoard());
            timelineBoard.setUsername(timelineBoard.getBoard().getWriteUser().getUsername());
            timelineBoard.setLikeList(likesService.getLikeList(timelineBoard.getBoard().getId()));
            timelineBoard.setCommentList(commentService.getCommentList(timelineBoard.getBoard().getId()));

            boardList.add(timelineBoard);
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
