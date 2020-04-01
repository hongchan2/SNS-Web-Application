package com.hongchan.snsspringboot.board;

import com.hongchan.snsspringboot.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    public List<Comment> getCommentList(Long boradId) {
        final List<Comment> commentList = commentRepository.findByBoard_Id(boradId);
        return commentList;
    }

    public void writeComment(User user, Board board , Comment comment) {
        comment.setDateTime(LocalDateTime.now());
        comment.setUsername(user.getUsername());
        comment.setBoard(board);

        commentRepository.save(comment);
    }
}
