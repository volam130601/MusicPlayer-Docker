package com.spring.musicplayer5.controllers.impl;

import com.spring.musicplayer5.controllers.CommentRepliesController;
import com.spring.musicplayer5.dto.CommentDto;
import com.spring.musicplayer5.dto.CommentRepliesDto;
import com.spring.musicplayer5.dto.ResponseObject;
import com.spring.musicplayer5.entity.Comment;
import com.spring.musicplayer5.entity.CommentReplies;
import com.spring.musicplayer5.entity.User;
import com.spring.musicplayer5.services.CommentRepliesService;
import com.spring.musicplayer5.services.CommentService;
import com.spring.musicplayer5.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/comment_replies")
public class CommentRepliesControllerImpl implements CommentRepliesController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentRepliesService commentRepliesService;

    @GetMapping
    public ResponseEntity<ResponseObject> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK" , "Find All successfully!" , commentRepliesService.findAll())
        );
    }

    @PostMapping
    public ResponseEntity<ResponseObject> save(@RequestBody CommentDto commentDto) {
        Optional<Comment> commentExist = commentService.findById(commentDto.getComment_id());
        Optional<User> userExist = userService.findByUsername(commentDto.getUsername());
        if(commentExist.isPresent() && userExist.isPresent()) {
            CommentReplies commentReplies = CommentReplies.builder()
                    .content(commentDto.getContent())
                    .createAt(new Date())
                    .user(userExist.get())
                    .comment(commentExist.get())
                    .build();
            commentRepliesService.save(commentReplies);
            CommentRepliesDto commentRepliesDto = new CommentRepliesDto();
            BeanUtils.copyProperties(commentReplies , commentRepliesDto);
            commentRepliesDto.setComment_id(commentReplies.getComment().getId());
            commentRepliesDto.setUsername(commentReplies.getUser().getUsername());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK" , "Save comment reply is successfully!" , commentRepliesDto)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("NOT_FOUND" , "Cannot found comment_id or user" , null)
        );
    }



}
