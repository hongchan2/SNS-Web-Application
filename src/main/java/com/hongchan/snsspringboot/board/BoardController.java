package com.hongchan.snsspringboot.board;

import com.hongchan.snsspringboot.user.AuthUser;
import com.hongchan.snsspringboot.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BoardController {

    @Autowired
    BoardService boardService;

    @GetMapping("/board/create")
    public String boardCreate(@AuthUser User user, Model model) {
        model.addAttribute(user);
        return "board-create";
    }

    @PostMapping("/board/create-process")
    public String boardCreateProcess(@AuthUser User user, @ModelAttribute Board board) {
        System.out.println(board.getTitle());
        System.out.println(board.getContent());
        boardService.writePost(board, user);

        return "redirect:/";
    }

}
