package com.spring.musicplayer5.controllers.impl;

import com.spring.musicplayer5.dto.ResponseObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface TrackControllerImpl {
    @GetMapping
    ResponseEntity<ResponseObject> findAll();

    //Repair
    @GetMapping("/get_id")
    ResponseEntity<ResponseObject> getById(@RequestParam long id);

    @GetMapping("/search")
    ResponseEntity<ResponseObject> getByTitle(@RequestParam String title);

    //Code Pagination API : list of Tracks
    @GetMapping("/paging")
    ResponseEntity<ResponseObject> getTracksPage(@RequestParam Integer page, @RequestParam Integer size);

    @GetMapping("/get_top")
    ResponseEntity<ResponseObject> getTopTrack(@RequestParam Integer size);
}
