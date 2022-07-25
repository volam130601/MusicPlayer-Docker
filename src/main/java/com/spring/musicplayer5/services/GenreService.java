package com.spring.musicplayer5.services;

import com.spring.musicplayer5.entity.Artist;
import com.spring.musicplayer5.entity.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    List<Genre> findAll();
    Genre save(Genre entity);
    Optional<Genre> findById(long id);
    void deleteById(long id);
}
