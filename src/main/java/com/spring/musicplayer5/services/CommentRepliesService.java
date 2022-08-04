package com.spring.musicplayer5.services;

import com.spring.musicplayer5.entity.Comment;
import com.spring.musicplayer5.entity.CommentReplies;
import com.spring.musicplayer5.repositories.CommentRepliesRepo;

import java.util.List;
import java.util.Optional;

public interface CommentRepliesService {
    List<CommentReplies> findAll();
    CommentReplies save(CommentReplies entity);
    Optional<CommentReplies> findById(long id);
    void deleteById(long id);
}
