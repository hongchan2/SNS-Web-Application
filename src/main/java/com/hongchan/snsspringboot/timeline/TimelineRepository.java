package com.hongchan.snsspringboot.timeline;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface TimelineRepository extends JpaRepository<Timeline, Integer> {

    Page<Timeline> findAllByUser_Username(String username, Pageable pageable);

    @Transactional
    void deleteByUser_UsernameAndBoard_Id(String user_Username, long board_Id);
}