package com.hongchan.snsspringboot.board;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Likes {

    @Id
    @GeneratedValue
    @Column(name = "LIKES_ID")
    private long id;

    @Column(name = "LIKES_USER_ID")
    private String username;

    @ManyToOne
    @JoinColumn(name = "BOARD_ID")
    private Board board;

}
