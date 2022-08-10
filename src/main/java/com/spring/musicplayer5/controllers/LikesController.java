package com.spring.musicplayer5.controllers;

import com.spring.musicplayer5.dto.ResponseObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface LikesController {
    @GetMapping("/count_likes_of_tracks")
    ResponseEntity<ResponseObject> countLikesOfTrack(@RequestParam long track_id);

    @PostMapping("/add_track_likes")
    ResponseEntity<ResponseObject> addTrackLikes(@RequestParam long track_id,
                                                 @RequestParam String username);
}
