package com.spring.musicplayer5.controllers.impl;

import com.spring.musicplayer5.dto.PlaylistHasTrackDto;
import com.spring.musicplayer5.dto.ResponseObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface PlaylistHasTrackControllerImpl {
    @GetMapping("/getAll")
    ResponseEntity<ResponseObject> getAll();

    @GetMapping("/getByPlaylistId")
    ResponseEntity<ResponseObject> getByIdPlaylist(@RequestParam long playlistId);

    @PostMapping("/addNew")
    ResponseEntity<ResponseObject> saveTrackHasPlaylist(@RequestBody PlaylistHasTrackDto phl);

    @DeleteMapping("/deleteByTrackIdInPlaylist")
    ResponseEntity<ResponseObject> deleteByTrackIdInPlaylist(@RequestParam(required = false) long playlistId,
                                                             @RequestParam(required = false) long trackId);
}
