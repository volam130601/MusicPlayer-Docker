package com.spring.musicplayer5.services;

import com.spring.musicplayer5.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> findAll();
    Comment save(Comment entity);
    Optional<Comment> findById(long id);
    Optional<Comment> findByIdAndUserUsername(long id , String username);
    void deleteById(long id);
    Optional<Comment> findByUserUsername(String username);

}
