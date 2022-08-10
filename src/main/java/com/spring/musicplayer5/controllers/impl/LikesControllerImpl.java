package com.spring.musicplayer5.controllers.impl;

import com.spring.musicplayer5.controllers.LikesController;
import com.spring.musicplayer5.dto.ResponseObject;
import com.spring.musicplayer5.entity.Likes;
import com.spring.musicplayer5.entity.Track;
import com.spring.musicplayer5.entity.User;
import com.spring.musicplayer5.entity.embedable.LikeId;
import com.spring.musicplayer5.services.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/likes")
public class LikesControllerImpl implements LikesController {
    @Autowired
    private LikesService likesService;
    @Override
    @GetMapping("/count_likes_of_tracks")
    public ResponseEntity<ResponseObject> countLikesOfTrack(@RequestParam long track_id) {
        int count = likesService.countByIdTrackId(track_id);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("COUNT" , "Count Likes of Track!" , count)
        );
    }
    @Override
    @PostMapping("/add_track_likes")
    public ResponseEntity<ResponseObject> addTrackLikes(@RequestParam long track_id,
                                                        @RequestParam String username) {
        LikeId likeId = LikeId.builder()
                .trackId(track_id)
                .username(username)
                .build();
        if(!likesService.existsById(likeId)) {
            Track track = Track.builder().id(track_id).build();
            User user = User.builder().username(username).build();
            Likes likes = Likes.builder()
                    .id(likeId)
                    .isLike(true)
                    .track(track)
                    .user(user)
                    .build();
            likesService.save(likes);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("SUCCESS" , "Data save is Success!" , likes)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("EXISTS" , "Data is exists!" , null)
        );
    }


}
