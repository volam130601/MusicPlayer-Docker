package com.spring.musicplayer5.controllers;

import com.spring.musicplayer5.dto.CommentDto;
import com.spring.musicplayer5.dto.ResponseObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface CommentRepliesController {
    @GetMapping
    ResponseEntity<ResponseObject> findAll();

    @PostMapping
    ResponseEntity<ResponseObject> save(@RequestBody CommentDto commentDto);
}
