package com.spring.musicplayer5.controllers;

import com.spring.musicplayer5.dto.PlaylistDto;
import com.spring.musicplayer5.dto.ResponseObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface PlaylistController {
    @GetMapping("")
    ResponseEntity<ResponseObject> getAllPlaylist();

    @PostMapping
    ResponseEntity<ResponseObject> createPlaylist(@RequestBody PlaylistDto playlistDto);

    @GetMapping("/getByUsername")
    ResponseEntity<ResponseObject> getByUsername(@RequestParam String username);

    @PutMapping("/rename")
    ResponseEntity<ResponseObject> renamePlaylist(@RequestBody PlaylistDto playlistDto);

    @DeleteMapping
    ResponseEntity<ResponseObject> deleteById(@RequestParam long id);
}
