package com.spring.musicplayer5.services;

import com.spring.musicplayer5.entity.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ArtistService {
    List<Artist> findAll();
    Page<Artist> findAll(Pageable pageable);
    Artist save(Artist entity);
    Optional<Artist> findById(long id);
    void deleteById(long id);
}
