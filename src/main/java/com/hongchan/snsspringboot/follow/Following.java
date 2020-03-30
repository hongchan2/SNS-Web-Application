package com.hongchan.snsspringboot.follow;

import com.hongchan.snsspringboot.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Following {

    @Id
    @GeneratedValue
    @Column(name = "FOLLOWING_ID")
    private long id;

    @ManyToOne
    @JoinColumn(name = "FOLLOWER_SRC_USER_ID")
    private User srcUser;

    @ManyToOne
    @JoinColumn(name = "FOLLOWER_DEST_USER_ID")
    private User destUser;

}
