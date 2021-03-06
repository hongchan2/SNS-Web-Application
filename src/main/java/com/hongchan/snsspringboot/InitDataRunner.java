package com.hongchan.snsspringboot;

import com.hongchan.snsspringboot.board.Board;
import com.hongchan.snsspringboot.board.BoardService;
import com.hongchan.snsspringboot.board.comment.Comment;
import com.hongchan.snsspringboot.board.comment.CommentService;
import com.hongchan.snsspringboot.follow.FollowService;
import com.hongchan.snsspringboot.user.UserAccountService;
import com.hongchan.snsspringboot.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class InitDataRunner implements ApplicationRunner {

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    BoardService boardService;

    @Autowired
    FollowService followService;

    @Autowired
    CommentService commentService;

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
            10000명의 팔로워를 가진 유저가 게시물에 글을 쓰는 테스트
            -> 엄청난 딜레이 발생
            -> 팔로워 수에 따른 전략으로 해결!
         */

        for(int i = 0; i < 500; i++) {
            User user = new User();
            user.setUsername("user" + i);
            user.setPassword("1234");
            user.setEmail("user" + i + "@naver.com");

            userAccountService.signUp(user);
            followService.follow(user, hongchan.getUsername());
        }

        /*
        홍찬과 지윤, 태수는 맞팔로우
        홍찬은 지성, 용재와 팔로우

        홍찬은 "Board1 Title / Board1 Content / (현재시간)" 게시판(1)을 작성
        지윤은 "Board2 Title / Board2 Content / (현재시간)" 게시판(2)을 작성
        홍찬은 "Board3 Title / Board3 Content / (현재시간)" 게시판(3)을 작성
         */

        followService.follow(hongchan, jiyun.getUsername());
        followService.follow(hongchan, taesu.getUsername());
        followService.follow(hongchan, jisung.getUsername());
        followService.follow(hongchan, yongjae.getUsername());

        followService.follow(jiyun, hongchan.getUsername());
        followService.follow(taesu, hongchan.getUsername());

        Board board1 = new Board();
        board1.setTitle("[Hongchan] Board1 Title");
        board1.setContent("Board1 Content");
        boardService.writePost(hongchan, board1);

        Board board2 = new Board();
        board2.setTitle("[Jiyun] Board2 Title");
        board2.setContent("Board2 Content");
        boardService.writePost(jiyun, board2);

        Board board3 = new Board();
        board3.setTitle("[Hongchan] Board3 Title");
        board3.setContent("Board3 Content");
        boardService.writePost(hongchan, board3);

        /*
            타임라인에 10000 개 정도의 게시물 테스트
            -> 속도 저하 문제
            -> 페이징으로 속도 개선 완료
         */
//        int loopCnt = 10000;
        int loopCnt = 10;
        for(int i = 0; i < loopCnt; i++) {
            Board board = new Board();
            board.setTitle("Test Title " + i);
            board.setContent("Test Content " + i);
            boardService.writePost(hongchan, board);

            Comment comment1 = new Comment();
            comment1.setContent("Test Comment1 - taesu " + i);
            commentService.writeComment(taesu, board, comment1);

            Comment comment2 = new Comment();
            comment2.setContent("Test Comment2 - jiyun " + i);
            commentService.writeComment(jiyun, board, comment2);
        }


        Board board = new Board();
        board.setTitle("longlong title longlong title longlong title");
        board.setContent("longlong content longlong content longlong content longlong content longlong content" +
                "longlong content longlong contentlonglong contentlonglong content longlong content longlong content" +
                "longlong content longlong content");
        boardService.writePost(hongchan, board);

        Comment comment1 = new Comment();
        comment1.setContent("longlong comment longlong comment longlong comment longlong comment longlong comment longlong comment" +
                "longlong comment longlong commentlonglong comment longlong comment ");
        commentService.writeComment(taesu, board, comment1);

        Comment comment2 = new Comment();
        comment1.setContent("longlong comment longlong comment longlong comment longlong comment longlong comment longlong comment" +
                "longlong comment longlong commentlonglong comment longlong comment ");

        /*
            지윤은 게시판1에 좋아요를 함
            지윤은 게시판1에 "댓글입니다 / (현재시간)" 댓글을 작성
            지윤은 게시판1에 "댓글입니다2 / (현재시간)" 댓글을 작성
         */



        System.out.println("=====INIT DATA RUNNER DONE====");
    }
}
