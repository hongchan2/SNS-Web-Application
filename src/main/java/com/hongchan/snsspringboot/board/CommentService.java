package com.hongchan.snsspringboot.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    public List<Comment> getCommentList(Long boradId) {
        final List<Comment> commentList = commentRepository.findByBoard_Id(boradId);
        return commentList;
    }
}
