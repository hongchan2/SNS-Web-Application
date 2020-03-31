package com.hongchan.snsspringboot.timeline;

import com.hongchan.snsspringboot.board.Board;
import com.hongchan.snsspringboot.board.Comment;
import com.hongchan.snsspringboot.board.Likes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class TimelineBoard {

    private String username;

    private Board board;

    private List<Comment> commentList = new ArrayList<>();

    private List<Likes> likeList = new ArrayList<>();

}
