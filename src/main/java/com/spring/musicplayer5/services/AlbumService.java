package com.spring.musicplayer5.services;

import com.spring.musicplayer5.entity.Album;
import com.spring.musicplayer5.entity.Artist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AlbumService {
    List<Album> findAll();
    Page<Album> findAll(Pageable pageable);
    Album save(Album entity);
    Optional<Album> findById(long id);
    void deleteById(long id);
    boolean existsById(Long id);
}
