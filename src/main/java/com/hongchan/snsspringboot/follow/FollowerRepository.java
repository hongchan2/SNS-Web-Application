package com.hongchan.snsspringboot.follow;

import com.hongchan.snsspringboot.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowerRepository extends JpaRepository<Follower, Integer> {
    List<Follower> findBySrcUser_Username(String username);
}
