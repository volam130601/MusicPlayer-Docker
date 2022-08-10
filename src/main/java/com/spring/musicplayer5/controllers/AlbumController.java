package com.spring.musicplayer5.controllers;

import com.spring.musicplayer5.dto.ResponseObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface AlbumController {
    @GetMapping("/get_track")
    ResponseEntity<ResponseObject> getTracksBelongIdAlbum(@RequestParam long albumId);

    @GetMapping
    ResponseEntity<ResponseObject> getTop5Track();
}
