package com.hongchan.snsspringboot.follow;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FollowerRepository extends JpaRepository<Follower, Integer> {

    List<Follower> findBySrcUser_Username(String username);

    Follower findByDestUser_UsernameAndSrcUser_Username(String destUser_Username, String srcUser_Username);

    long countBySrcUser_Username(String srcUser_Username);

    @Transactional
    void deleteBySrcUser_UsernameAndDestUser_Username(String srcUser_Username, String destUser_Username);
}
