package com.hongchan.snsspringboot.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikesService {

    @Autowired
    LikesRepository likesRepository;

    public List<Likes> getLikeList(Long boardId) {
        final List<Likes> likesList = likesRepository.findByBoard_Id(boardId);
        return likesList;
    }
}
