package com.hongchan.snsspringboot.user;

import com.hongchan.snsspringboot.board.Board;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class User {

    @Id
    @Column(name = "USER_ID")
    private String username;

    private String password;

    private String email;

}
