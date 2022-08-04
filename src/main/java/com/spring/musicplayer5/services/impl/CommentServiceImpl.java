package com.spring.musicplayer5.services.impl;

import com.spring.musicplayer5.entity.Comment;
import com.spring.musicplayer5.repositories.CommentRepository;
import com.spring.musicplayer5.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentRepository repository;

    @Override
    public List<Comment> findAll() {
        return repository.findAll();
    }

    @Override
    public Comment save(Comment entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<Comment> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Comment> findByIdAndUserUsername(long id, String username) {
        return repository.findByIdAndUserUsername(id , username);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Comment> findByUserUsername(String username) {
        return repository.findByUserUsername(username);
    }

    @Override
    public List<Comment> findByTrackId(Long trackId) {
        return repository.findByTrackId(trackId);
    }
}
