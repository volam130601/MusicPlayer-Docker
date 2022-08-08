package com.spring.musicplayer5.repositories;

import com.spring.musicplayer5.entity.LikesOfComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LikeOfCommentRepo extends JpaRepository<LikesOfComment, Long> {
    @Query("SELECT count(lc.id) from LikesOfComment lc WHERE lc.comment.id=?1 AND lc.isLiked = true")
    int countByLiked(Long comment_id);

    @Query("SELECT count(lc.id)  from LikesOfComment lc WHERE lc.comment.id=?1 AND lc.isDisliked = true ")
    int countByDisliked(Long comment_id);

    Optional<LikesOfComment> findByCommentIdAndUserUsername(Long comment_id , String username);
}
