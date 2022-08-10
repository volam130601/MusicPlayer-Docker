package com.spring.musicplayer5.controllers.impl;

import com.spring.musicplayer5.controllers.CommentController;
import com.spring.musicplayer5.dto.CommentDto;
import com.spring.musicplayer5.dto.LikeOfCommentDto;
import com.spring.musicplayer5.dto.ResponseObject;
import com.spring.musicplayer5.entity.Comment;
import com.spring.musicplayer5.entity.LikesOfComment;
import com.spring.musicplayer5.entity.Track;
import com.spring.musicplayer5.entity.User;
import com.spring.musicplayer5.services.CommentService;
import com.spring.musicplayer5.services.LikeOfCommentService;
import com.spring.musicplayer5.services.TrackService;
import com.spring.musicplayer5.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/comment")
public class CommentControllerImpl implements CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private TrackService trackService;

    @Autowired
    private LikeOfCommentService likeOfCommentService;

    @Override
    @GetMapping
    public ResponseEntity<ResponseObject> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK" , "Get all data of comment successfully!" , commentService.findAll())
        );
    }

    @Override
    @PostMapping
    public ResponseEntity<ResponseObject> UserCommentForTrack(@RequestBody CommentDto commentDto) {
        Optional<User> exsistUser = userService.findByUsername(commentDto.getUsername());
        Optional<Track> exsistTrack = trackService.findById(commentDto.getTrack_id());
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

    @Override
    @PutMapping("/repair-content")
    public ResponseEntity<ResponseObject> repairContentByUser(@RequestBody CommentDto commentDto) {
        Optional<Comment> exsist = commentService.findByIdAndUserUsername(commentDto.getComment_id() , commentDto.getUsername());
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

    @Override
    @DeleteMapping
    public ResponseEntity<ResponseObject> deleteComment(@RequestBody CommentDto commentDto) {
        Optional<Comment> exsistComment = commentService.findByIdAndUserUsername(commentDto.getComment_id() , commentDto.getUsername());
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

    @Override
    @GetMapping("/find_by_track_id")
    public ResponseEntity<ResponseObject> findByTrackId(@RequestParam Long trackId) {
        List<Comment> comments = commentService.findByTrackId(trackId);
        return ResponseEntity.status(HttpStatus.OK).body(
            new ResponseObject("SUCCESS" , "Find is success!" , comments)
        );
    }

    @Override
    @PostMapping("/likes")
    public ResponseEntity<ResponseObject> saveLikeOfComment(@RequestBody CommentDto commentDto) {
        Optional<Comment> existComment = commentService.findById(commentDto.getComment_id());
        Optional<User> existUser = userService.findByUsername(commentDto.getUsername());
        if(existComment.isPresent() && existUser.isPresent()) {
            Optional<LikesOfComment> existThis = likeOfCommentService.findByCommentIdAndUserUsername(commentDto.getComment_id(), commentDto.getUsername());
            if(!existThis.isPresent()) {
                LikesOfComment likes = LikesOfComment.builder()
                        .comment(Comment.builder().id(commentDto.getComment_id()).build())
                        .user(User.builder().username(commentDto.getUsername()).build())
                        .build();
                if(commentDto.getIsLiked() != null){
                    likes.setLiked(commentDto.getIsLiked());
                }
                if(commentDto.getIsDisliked() != null) {
                    likes.setDisliked(commentDto.getIsDisliked());
                }
                likeOfCommentService.save(likes);
            } else {
                if(commentDto.getIsLiked() != null && commentDto.getIsLiked() == true) {
                    existThis.get().setLiked(true);
                } else {
                    existThis.get().setLiked(false);
                }
                if(commentDto.getIsDisliked() != null  && commentDto.getIsDisliked() == true) {
                    existThis.get().setDisliked(true);
                } else {
                    existThis.get().setDisliked(false);
                }
                likeOfCommentService.save(existThis.get());
            }
            int isLiked = likeOfCommentService.countByLiked(commentDto.getComment_id());
            int isDisliked = likeOfCommentService.countByDisliked(commentDto.getComment_id());
            commentDto.setLikes(isLiked);
            commentDto.setDislikes(isDisliked);

            existComment.get().setLikes(isLiked);
            existComment.get().setDislikes(isDisliked);
            commentService.save(existComment.get());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("SUCCESS" , "Save like is successfully!" , existComment.get())
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("NOT_FOUND" , "Cannot found comment or user!" , null)
        );
    }

    @Override
    @GetMapping("/likes")
    public ResponseEntity<ResponseObject> getAllLikeOfCommentByUser(@RequestParam String username) {
        List<LikesOfComment> likesOfComments = likeOfCommentService.findByUserUsername(username);
        if(!likesOfComments.isEmpty()) {
            List<LikeOfCommentDto> list = new ArrayList<>();
            likesOfComments.forEach(loc -> {
                LikeOfCommentDto l = new LikeOfCommentDto();
                BeanUtils.copyProperties(loc , l);
                l.setComment_id(loc.getComment().getId());
                l.setUsername(username);
                list.add(l);
            });
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseObject("SUCCESS", "Found username is success!", list));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("NOT_FOUND" , "Cannot found username!" , null)
        );
    }
}
