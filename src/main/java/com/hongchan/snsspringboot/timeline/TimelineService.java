package com.hongchan.snsspringboot.timeline;

import com.hongchan.snsspringboot.board.Board;
import com.hongchan.snsspringboot.board.BoardRepository;
import com.hongchan.snsspringboot.board.CommentService;
import com.hongchan.snsspringboot.board.LikesService;
import com.hongchan.snsspringboot.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Autowired
    BoardRepository boardRepository;

    public List<TimelineBoard> getTimelineBoardList(String username, Pageable pageable) {
        final Page<Timeline> timelinePage = timelineRepository.findAllByUser_Username(username, pageable);

        System.out.println("==============");
        System.out.println("ToTal Page " + timelinePage.getTotalPages());
        System.out.println("ToTal Element " + timelinePage.getTotalElements());
        for(Timeline timeline : timelinePage.getContent()) {
            System.out.println(timeline.getBoard().getTitle());
        }
        System.out.println("==============");

        List<TimelineBoard> boardList = new ArrayList<>();
        for(Timeline timeline : timelinePage.getContent()) {
            TimelineBoard timelineBoard = new TimelineBoard();

            timelineBoard.setBoard(timeline.getBoard());
            timelineBoard.setUsername(timelineBoard.getBoard().getWriteUser().getUsername());
            timelineBoard.setLikeList(likesService.getLikeList(timelineBoard.getBoard().getId()));
            timelineBoard.setCommentList(commentService.getCommentList(timelineBoard.getBoard().getId()));

            boardList.add(timelineBoard);
        }

        return boardList;
    }

    public void addTimeline(User user, Board board) {
        Timeline timeline = new Timeline();
        timeline.setUser(user);
        timeline.setBoard(board);
        timelineRepository.save(timeline);
    }

    public void removeTimeline(User user, Board board) {
        timelineRepository.deleteByUser_UsernameAndBoard_Id(user.getUsername(), board.getId());
    }

    // srcUser의 타임라인에 destUser의 게시물을 추가
    public void afterFollowProcess(User srcUser, String username) {
        final List<Board> boardList = boardRepository.findByWriteUser_Username(username);

        for(Board board : boardList) {
            addTimeline(srcUser, board);
        }
    }

    // srcUser의 타임라인에서 destUser의 게시물을 삭제
    public void afterUnfollowProcess(User srcUser, String username) {
        List<Board> boardList = boardRepository.findByWriteUser_Username(username);

        for(Board board : boardList) {
            removeTimeline(srcUser, board);
        }
    }

}
