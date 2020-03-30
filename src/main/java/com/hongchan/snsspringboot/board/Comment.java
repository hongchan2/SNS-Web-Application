package com.hongchan.snsspringboot.board;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Comment {

    @Id
    @GeneratedValue
    @Column(name = "COMMENT_ID")
    private long id;

    @Column(name = "COMMENT_USER_ID")
    private String username;

    private String content;

    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "BOARD_ID")
    private Board board;

}
