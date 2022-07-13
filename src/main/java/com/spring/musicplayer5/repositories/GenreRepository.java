package com.spring.musicplayer5.repositories;

import com.spring.musicplayer5.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {
}
