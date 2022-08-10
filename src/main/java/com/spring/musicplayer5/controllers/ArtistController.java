package com.spring.musicplayer5.controllers;

import com.spring.musicplayer5.dto.ResponseObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface ArtistController {
    @GetMapping
    ResponseEntity<ResponseObject> getTop5Artist();

    @GetMapping("/get_page")
    ResponseEntity<ResponseObject> getPageArtist(@RequestParam int page,
                                                 @RequestParam int size);

    @GetMapping("/get_track")
    ResponseEntity<ResponseObject> getTrack(@RequestParam long artistId);
}
