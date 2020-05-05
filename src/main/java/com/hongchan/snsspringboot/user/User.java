package com.hongchan.snsspringboot.user;

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
