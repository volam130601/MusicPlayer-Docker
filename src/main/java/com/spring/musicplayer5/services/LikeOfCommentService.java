package com.spring.musicplayer5.services;

import com.spring.musicplayer5.entity.LikesOfComment;

import java.util.List;
import java.util.Optional;

public interface LikeOfCommentService {
    List<LikesOfComment> findAll();
    LikesOfComment save(LikesOfComment entity);

    int countByLiked(Long comment_id);
    int countByDisliked(Long comment_id);
    Optional<LikesOfComment> findByCommentIdAndUserUsername(Long comment_id , String username);

}
