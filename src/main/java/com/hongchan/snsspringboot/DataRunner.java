package com.hongchan.snsspringboot;

import com.hongchan.snsspringboot.board.Board;
import com.hongchan.snsspringboot.board.BoardService;
import com.hongchan.snsspringboot.board.Comment;
import com.hongchan.snsspringboot.user.UserAccountService;
import com.hongchan.snsspringboot.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataRunner implements ApplicationRunner {

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    BoardService boardService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User hongchan = new User();
        hongchan.setUsername("hongchan");
        hongchan.setPassword("1234");
        hongchan.setEmail("ipeter@naver.com");

        User jiyun = new User();
        jiyun.setUsername("jiyun");
        jiyun.setPassword("1234");
        jiyun.setEmail("jiyun@naver.com");

        User taesu = new User();
        taesu.setUsername("taesu");
        taesu.setPassword("1234");
        taesu.setEmail("taesu@naver.com");

        User jisung = new User();
        jisung.setUsername("jisung");
        jisung.setPassword("1234");
        jisung.setEmail("jisung@naver.com");

        User yongjae = new User();
        yongjae.setUsername("yongjae");
        yongjae.setPassword("1234");
        yongjae.setEmail("yongjae@naver.com");

        userAccountService.signUp(hongchan);
        userAccountService.signUp(jiyun);
        userAccountService.signUp(taesu);
        userAccountService.signUp(jisung);
        userAccountService.signUp(yongjae);

        /*
        홍찬과 지윤, 태수는 맞팔로우
        홍찬은 지성, 용재와 팔로우

        홍찬은 "홍찬 / 윤홍찬입니다 / (현재시간)" 게시판(1)을 작성
        지윤은 "지윤 / 김지윤입니다 / (현재시간)" 게시판(2)을 작성
        홍찬은 "홍찬2 / 윤홍찬입니다2 / (현재시간)" 게시판(3)을 작성

        지윤은 게시판1에 좋아요를 함
        지윤은 게시판1에 "댓글입니다 / (현재시간)" 댓글을 작성
        지윤은 게시판1에 "댓글입니다2 / (현재시간)" 댓글을 작성
         */
        Board board1 = new Board();
        board1.setTitle("[Hongchan] Board1 Title");
        board1.setContent("Board1 Content");
        boardService.writePost(board1, hongchan);

        Board board2 = new Board();
        board2.setTitle("[Jiyun] Board2 Title");
        board2.setContent("Board2 Content");
        boardService.writePost(board2, jiyun);

        Board board3 = new Board();
        board3.setTitle("[Hongchan] Board3 Title");
        board3.setContent("Board3 Content");
        boardService.writePost(board3, hongchan);
    }
}
