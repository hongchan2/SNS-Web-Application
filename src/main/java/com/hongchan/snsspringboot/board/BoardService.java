package com.hongchan.snsspringboot.board;

import com.hongchan.snsspringboot.follow.FollowCntInfo;
import com.hongchan.snsspringboot.follow.FollowService;
import com.hongchan.snsspringboot.follow.Follower;
import com.hongchan.snsspringboot.timeline.TimelineService;
import com.hongchan.snsspringboot.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    TimelineService timelineService;

    @Autowired
    FollowService followService;

    public void writePost(User user, Board board) {
        board.setDateTime(LocalDateTime.now());
        board.setWriteUser(user);
        boardRepository.save(board);

        // 해당 유저가 쓴 글은 해당 유저의 타임라인에 추가
        timelineService.addTimeline(user, board);

        long followerCnt = followService.getFollowCnt(user.getUsername()).getFollower();
        if(followerCnt < 500L) {
            // SmallFollower 전략 : 해당 유저를 팔로우하는 유저들(해당 유저의 팔로워들)의 타임라인에 추가

            List<Follower> followerList = followService.getFollowerList(user.getUsername());

            for(Follower follower : followerList) {
                User destUser = follower.getDestUser();
                timelineService.addTimeline(destUser, board);
            }
        }
        else {
            // BigFollower 전략 : No Operation
        }
    }

    public List<Board> getBoardList(String username) {
        return boardRepository.findByWriteUser_Username(username);
    }

}
