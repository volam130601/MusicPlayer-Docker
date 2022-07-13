package com.spring.musicplayer5.controllers;

import com.spring.musicplayer5.entity.Likes;
import com.spring.musicplayer5.entity.embedable.LikeId;
import com.spring.musicplayer5.services.LikesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class LikesControllerTest {
    @Autowired
    private LikesService likesService;

    @Test
    void testLikes() {
        LikeId likeId = LikeId.builder()
                .trackId(916424)
                .username("lamlbx123")
                .build();
        Likes likes = Likes.builder()
                .id(likeId)
                .track(null)
                .user(null)
                .build();
        System.out.println(likes);
    }
}