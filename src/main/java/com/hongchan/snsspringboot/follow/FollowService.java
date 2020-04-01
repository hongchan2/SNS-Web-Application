package com.hongchan.snsspringboot.follow;

import com.hongchan.snsspringboot.timeline.TimelineService;
import com.hongchan.snsspringboot.user.User;
import com.hongchan.snsspringboot.user.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowService {

    @Autowired
    FollowerRepository followerRepository;

    @Autowired
    FollowingRepository followingRepository;

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    TimelineService timelineService;

    public List<Following> getFollowingList(String username) {
        return followingRepository.findBySrcUser_Username(username);
    }

    public List<Follower> getFollowerList(String username) {
        return followerRepository.findBySrcUser_Username(username);
    }

    public FollowCntInfo getFollowCnt(String username) {
        FollowCntInfo cntInfo = new FollowCntInfo();
        cntInfo.setFollower(followerRepository.countBySrcUser_Username(username));
        cntInfo.setFollowing(followingRepository.countBySrcUser_Username(username));

        return cntInfo;
    }

    public boolean isFollowing(String src, String dest) {
        Following following = followingRepository.findBySrcUser_UsernameAndDestUser_Username(src, dest);
        return (following != null) ? true : false;
    }

    public boolean isFollower(String src, String dest) {
        Follower follower = followerRepository.findByDestUser_UsernameAndSrcUser_Username(src, dest);
        return (follower != null) ? true : false;
    }

    public void follow(User srcUser, String username) {
        User destUser = userAccountService.getUser(username);

        Following following = new Following();
        following.setSrcUser(srcUser);
        following.setDestUser(destUser);

        Follower follower = new Follower();
        follower.setSrcUser(destUser);
        follower.setDestUser(srcUser);

        followingRepository.save(following);
        followerRepository.save(follower);

        // srcUser의 타임라인에 게시물 추가
        timelineService.afterFollowProcess(srcUser, username);
    }

    public void unFollow(User srcUser, String username) {
        User destUser = userAccountService.getUser(username);

        followingRepository.deleteBySrcUser_UsernameAndDestUser_Username(srcUser.getUsername(), destUser.getUsername());
        followerRepository.deleteBySrcUser_UsernameAndDestUser_Username(destUser.getUsername(), srcUser.getUsername());

        // srcUser의 타임라인에 게시물 삭제
        timelineService.afterUnfollowProcess(srcUser, username);
    }

}
