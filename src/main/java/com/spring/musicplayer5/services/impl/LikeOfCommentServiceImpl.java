package com.spring.musicplayer5.services.impl;

import com.spring.musicplayer5.entity.LikesOfComment;
import com.spring.musicplayer5.repositories.LikeOfCommentRepo;
import com.spring.musicplayer5.services.LikeOfCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeOfCommentServiceImpl implements LikeOfCommentService {
    @Autowired
    private LikeOfCommentRepo repo;

    @Override
    public List<LikesOfComment> findAll() {
        return repo.findAll();
    }

    @Override
    public LikesOfComment save(LikesOfComment entity) {
        return repo.save(entity);
    }

    @Override
    public int countByLiked(Long comment_id) {
        return repo.countByLiked(comment_id);
    }

    @Override
    public int countByDisliked(Long comment_id) {
        return repo.countByDisliked(comment_id);
    }

    @Override
    public Optional<LikesOfComment> findByCommentIdAndUserUsername(Long comment_id, String username) {
        return repo.findByCommentIdAndUserUsername(comment_id, username);
    }

    @Override
    public List<LikesOfComment> findByUserUsername(String username) {
        return repo.findByUserUsername(username);
    }
}
