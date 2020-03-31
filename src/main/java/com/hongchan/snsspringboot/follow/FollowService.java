package com.hongchan.snsspringboot.follow;

import com.hongchan.snsspringboot.user.User;
import com.hongchan.snsspringboot.user.UserAccount;
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
        final List<Follower> followerList = followerRepository.findBySrcUser_Username(username);
        return followerList;
    }

    public List<Following> getFollowingList(String username) {
        final List<Following> followingList = followingRepository.findBySrcUser_Username(username);
        return followingList;
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


}
