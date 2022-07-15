package com.spring.musicplayer5.repositories;

import com.spring.musicplayer5.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment , Long> {
    Optional<Comment> findByIdAndUserUsername(long id , String username);

    @Query("select c from Comment c where c.user.username = ?1 ")
    Optional<Comment> findByUserUsername(String username);

}
