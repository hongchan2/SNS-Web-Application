package com.hongchan.snsspringboot.timeline;

import com.hongchan.snsspringboot.board.Board;
import com.hongchan.snsspringboot.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Timeline {

    @Id
    @GeneratedValue
    @Column(name = "TIMELINE_ID")
    private long id;

    @ManyToOne
    @JoinColumn(name = "TIMELINE_USER_ID")
    private User user;

    @ManyToOne
    @JoinColumn(name = "TIMELINE_BOARD_ID")
    private Board board;

}
