package com.spring.musicplayer5.services.impl;

import com.spring.musicplayer5.entity.Comment;
import com.spring.musicplayer5.entity.CommentReplies;
import com.spring.musicplayer5.repositories.CommentRepliesRepo;
import com.spring.musicplayer5.services.CommentRepliesService;
import com.spring.musicplayer5.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentRepliesServiceImpl implements CommentRepliesService {
    @Autowired
    private CommentRepliesRepo repo;

    @Override
    public List<CommentReplies> findAll() {
        return repo.findAll();
    }

    @Override
    public CommentReplies save(CommentReplies entity) {
        return repo.save(entity);
    }

    @Override
    public Optional<CommentReplies> findById(long id) {
        return repo.findById(id);
    }

    @Override
    public void deleteById(long id) {
        repo.deleteById(id);
    }
}
