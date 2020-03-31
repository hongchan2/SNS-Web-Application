package com.hongchan.snsspringboot.follow;

import com.hongchan.snsspringboot.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowService {

    @Autowired
    FollowerRepository followerRepository;

    @Autowired
    FollowingRepository followingRepository;

    public List<User> getFollowerList(User user) {
        final List<User> followerList = followerRepository.findBySrcUser(user);
        return followerList;
    }

    public List<User> getFollowingList(User user) {
        final List<User> followingList = followingRepository.findBySrcUser(user);
        return followingList;
    }


}
