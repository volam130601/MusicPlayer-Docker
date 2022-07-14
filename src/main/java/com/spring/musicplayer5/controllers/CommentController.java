package com.spring.musicplayer5.controllers;

import com.spring.musicplayer5.dto.CommentDto;
import com.spring.musicplayer5.dto.ResponseObject;
import com.spring.musicplayer5.entity.Comment;
import com.spring.musicplayer5.entity.Track;
import com.spring.musicplayer5.entity.User;
import com.spring.musicplayer5.services.CommentService;
import com.spring.musicplayer5.services.TrackService;
import com.spring.musicplayer5.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private TrackService trackService;

    @GetMapping
    public ResponseEntity<ResponseObject> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK" , "Get all data of comment successfully!" , commentService.findAll())
        );
    }

    @PostMapping
    public ResponseEntity<ResponseObject> UserCommentForTrack(@RequestBody CommentDto commentDto) {
        Optional<User> exsistUser = userService.findByUsername(commentDto.getUsername());
        Optional<Track> exsistTrack = trackService.findById(commentDto.getTrackId());
        if(exsistUser.isPresent() && exsistTrack.isPresent()) {
            Comment comment = new Comment();
            BeanUtils.copyProperties(commentDto , comment);
            Date date = new Date();
            comment.setCreateAt(date);
            comment.setUser(exsistUser.get());
            comment.setTrack(exsistTrack.get());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK" , "Get all data of comment successfully!" , commentService.save(comment) )
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("FAILED" , "Cannot found username or track!" , null )
        );
    }

    @PutMapping("/repair-content")
    public ResponseEntity<ResponseObject> repairContentByUser(@RequestBody CommentDto commentDto) {
        Optional<Comment> exsist = commentService.findByIdAndUserUsername(commentDto.getId() , commentDto.getUsername());
        if(exsist.isPresent()) {
            Comment comment = exsist.get();
            comment.setContent(commentDto.getContent());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK" , "Repair comment successfully!" , commentService.save(comment))
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("FAILED" , "Cannot found comment of user!" , null)
        );
    }

    @DeleteMapping
    public ResponseEntity<ResponseObject> deleteComment(@RequestBody CommentDto commentDto) {
        Optional<Comment> exsistComment = commentService.findByIdAndUserUsername(commentDto.getId() , commentDto.getUsername());
        if(exsistComment.isPresent()) {
            commentService.deleteById(exsistComment.get().getId());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK" , "Delete comment successfully!" , "SUCCESS")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("FAILED" , "Cannot found comment of user!" , "")
        );
    }


}
