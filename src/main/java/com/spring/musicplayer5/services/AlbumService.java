package com.spring.musicplayer5.services;

import com.spring.musicplayer5.entity.Album;
import com.spring.musicplayer5.entity.Artist;

import java.util.List;
import java.util.Optional;

public interface AlbumService {
    List<Album> getAll();
    Album save(Album entity);
    Optional<Album> findById(long id);
    void deleteById(long id);
    boolean existsById(Long id);
}
