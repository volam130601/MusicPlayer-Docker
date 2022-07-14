package com.spring.musicplayer5.repositories;

import com.spring.musicplayer5.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment , Long> {
    Optional<Comment> findByIdAndUserUsername(long id , String username);

}
