package com.spring.musicplayer5.repositories;

import com.spring.musicplayer5.entity.Likes;
import com.spring.musicplayer5.entity.embedable.LikeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikesRepository extends JpaRepository<Likes , LikeId> {
    int countByIdTrackId(long track_id);
}
