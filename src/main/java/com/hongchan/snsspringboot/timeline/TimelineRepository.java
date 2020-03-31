package com.hongchan.snsspringboot.timeline;

import com.hongchan.snsspringboot.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimelineRepository extends JpaRepository<Timeline, Integer> {

    List<Timeline> findByUser_UsernameOrderByBoard_DateTimeDesc(String username);
}