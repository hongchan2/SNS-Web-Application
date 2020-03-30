package com.hongchan.snsspringboot.board;

import com.hongchan.snsspringboot.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BoardService {

    @Autowired
    BoardRepository boardRepository;

    public void writePost(Board board, User user) {
        board.setDateTime(LocalDateTime.now());
        board.setWriteUser(user);
        boardRepository.save(board);
    }

}
