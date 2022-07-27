package com.spring.musicplayer5.controllers;

import com.spring.musicplayer5.dto.CommentDto;
import com.spring.musicplayer5.dto.ResponseObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface CommentController {
    @GetMapping
    ResponseEntity<ResponseObject> findAll();

    @PostMapping
    ResponseEntity<ResponseObject> UserCommentForTrack(@RequestBody CommentDto commentDto);

    @PutMapping("/repair-content")
    ResponseEntity<ResponseObject> repairContentByUser(@RequestBody CommentDto commentDto);

    @DeleteMapping
    ResponseEntity<ResponseObject> deleteComment(@RequestBody CommentDto commentDto);
}
