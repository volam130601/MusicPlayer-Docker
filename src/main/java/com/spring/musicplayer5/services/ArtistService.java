package com.spring.musicplayer5.services;

import com.spring.musicplayer5.entity.Artist;

import java.util.List;
import java.util.Optional;

public interface ArtistService {
    List<Artist> findAll();
    Artist save(Artist entity);
    Optional<Artist> findById(long id);
    void deleteById(long id);
}
