package com.hongchan.snsspringboot.follow;

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

    public List<Follower> getFollowerList(String username) {
        return followerRepository.findBySrcUser_Username(username);
    }

    public List<Following> getFollowingList(String username) {
        return followingRepository.findBySrcUser_Username(username);
    }

    public FollowCntInfo getFollowCnt(String username) {
        FollowCntInfo cntInfo = new FollowCntInfo();
        cntInfo.setFollower(followerRepository.countBySrcUser_Username(username));
        cntInfo.setFollowing(followingRepository.countBySrcUser_Username(username));

        return cntInfo;
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
    }

    public void unFollow(User srcUser, String username) {
        User destUser = userAccountService.getUser(username);

        Following following = followingRepository.findBySrcUser_UsernameAndDestUser_Username(srcUser.getUsername(), destUser.getUsername());
        Follower follower = followerRepository.findByDestUser_UsernameAndSrcUser_Username(destUser.getUsername(), srcUser.getUsername());

        followingRepository.delete(following);
        followerRepository.delete(follower);
    }

}
