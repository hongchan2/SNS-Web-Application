package com.hongchan.snsspringboot.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    public List<Comment> getCommentList(Board board) {
        final List<Comment> commentList = commentRepository.findByBoard(board);
        return commentList;
    }
}
