package com.hongchan.snsspringboot.board;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikesRepository extends JpaRepository<Likes, Integer> {
    List<Likes> findByBoard(Board board);
}
