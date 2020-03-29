package com.hongchan.snsspringboot;

import com.hongchan.snsspringboot.user.MyUserDetailService;
import com.hongchan.snsspringboot.user.User;
import com.hongchan.snsspringboot.user.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataRunner implements ApplicationRunner {

    @Autowired
    MyUserDetailService myUserDetailService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User user1 = new User();
        user1.setUsername("hongchan");
        user1.setPassword("1234");
        user1.setEmail("ipeter@naver.com");

        User user2 = new User();
        user2.setUsername("jiyun");
        user2.setPassword("1234");
        user2.setEmail("jiyun@naver.com");

        User user3 = new User();
        user3.setUsername("taesu");
        user3.setPassword("1234");
        user3.setEmail("taesu@naver.com");

        User user4 = new User();
        user4.setUsername("wakgood");
        user4.setPassword("1234");
        user4.setEmail("wakgood@naver.com");

        User user5 = new User();
        user5.setUsername("changmo");
        user5.setPassword("1234");
        user5.setEmail("changmo@naver.com");

        myUserDetailService.signUp(user1);
        myUserDetailService.signUp(user2);
        myUserDetailService.signUp(user3);
        myUserDetailService.signUp(user4);
        myUserDetailService.signUp(user5);
    }
}
