package com.hongchan.snsspringboot.follow;

import com.hongchan.snsspringboot.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowingRepository extends JpaRepository<Following, Integer> {
    List<User> findBySrcUser_Username(String username);
}
