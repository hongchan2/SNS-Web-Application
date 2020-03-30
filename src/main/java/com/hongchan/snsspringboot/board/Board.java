package com.hongchan.snsspringboot.board;

import com.hongchan.snsspringboot.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @Setter
public class Board {

    @Id
    @GeneratedValue
    @Column(name = "BOARD_ID")
    private long id;

    private String title;

    private String content;

    private LocalDateTime datetime;

    @ManyToOne
    @JoinColumn(name = "BOARD_USER_ID")
    private User writeUser;

}
