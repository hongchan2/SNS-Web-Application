package com.hongchan.snsspringboot.timeline;

import com.hongchan.snsspringboot.board.*;
import com.hongchan.snsspringboot.follow.FollowCntInfo;
import com.hongchan.snsspringboot.follow.FollowService;
import com.hongchan.snsspringboot.follow.Follower;
import com.hongchan.snsspringboot.follow.Following;
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

    @Autowired
    FollowService followService;

    @Autowired
    BoardService boardService;

    public List<TimelineBoard> getTimelineBoardList(String username, Pageable pageable) {
        final Page<Timeline> timelinePage = timelineRepository.findAllByUser_Username(username, pageable);

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
        if(timelineRepository.findByUser_UsernameAndBoard_id(user.getUsername(), board.getId()) != null) return;

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

    public void beforeAccessTimeline(User user) {
        List<Following> followingList = followService.getFollowingList(user.getUsername());

        for(Following following : followingList) {
            long follower = followService.getFollowCnt(following.getDestUser().getUsername()).getFollower();

            if(follower < 500L) {
                // SmallFollower 전략 : No Operation
            }
            else {
                // BigFollower 전략 : dest 유저의 게시물을 모두 가져와 user의 타임라인에 추가

                List<Board> boardList = boardService.getBoardList(following.getDestUser().getUsername());

                for(Board board : boardList) {
                    addTimeline(user, board);
                }
            }
        }
    }

}
