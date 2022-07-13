package com.spring.musicplayer5.services.impl;

import com.spring.musicplayer5.entity.Likes;
import com.spring.musicplayer5.entity.embedable.LikeId;
import com.spring.musicplayer5.repositories.LikesRepository;
import com.spring.musicplayer5.services.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikesServiceImpl implements LikesService {
    @Autowired
    private LikesRepository likesRepository;

    @Override
    public List<Likes> getAll() {
        return likesRepository.findAll();
    }

    @Override
    public Likes save(Likes entity) {
        return likesRepository.save(entity);
    }

    @Override
    public boolean existsById(LikeId likeId) {
        return likesRepository.existsById(likeId);
    }

    @Override
    public int countByIdTrackId(long track_id) {
        return likesRepository.countByIdTrackId(track_id);
    }
}
