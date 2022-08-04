package com.spring.musicplayer5.repositories;

import com.spring.musicplayer5.entity.Comment;
import com.spring.musicplayer5.entity.CommentReplies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepliesRepo extends JpaRepository<CommentReplies, Long> {
}
