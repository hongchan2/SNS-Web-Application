package com.hongchan.snsspringboot.follow;

import com.hongchan.snsspringboot.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FollowingRepository extends JpaRepository<Following, Integer> {

    List<Following> findBySrcUser_Username(String username);

    Following findBySrcUser_UsernameAndDestUser_Username(String srcUser_Username, String destUser_Username);

    long countBySrcUser_Username(String srcUser_Username);
}
