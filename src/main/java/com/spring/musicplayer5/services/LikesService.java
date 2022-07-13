package com.spring.musicplayer5.services;

import com.spring.musicplayer5.entity.Artist;
import com.spring.musicplayer5.entity.Likes;
import com.spring.musicplayer5.entity.embedable.LikeId;

import java.util.List;
import java.util.Optional;

public interface LikesService {
    List<Likes> getAll();
    Likes save(Likes entity);
    boolean existsById(LikeId likeId);
    int countByIdTrackId(long track_id);

//    Optional<Likes> findById(long id);
//    void deleteById(long id);
}
