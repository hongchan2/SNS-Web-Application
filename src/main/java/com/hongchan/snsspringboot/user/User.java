package com.hongchan.snsspringboot.user;

import com.hongchan.snsspringboot.follow.strategy.FollowerStrategy;
import com.hongchan.snsspringboot.follow.strategy.SmallFollowerStrategy;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter @Setter
public class User {

    @Id
    @Column(name = "USER_ID")
    private String username;

    private String password;

    private String email;

}
