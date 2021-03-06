package com.hongchan.snsspringboot.follow;

import com.hongchan.snsspringboot.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Follower {

    @Id
    @GeneratedValue
    @Column(name = "FOLLOWER_ID")
    private long id;

    @ManyToOne
    @JoinColumn(name = "FOLLOWER_SRC_USER_ID")
    private User srcUser;

    @ManyToOne
    @JoinColumn(name = "FOLLOWER_DEST_USER_ID")
    private User destUser;

}
