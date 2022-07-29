package com.spring.musicplayer5.services;

import com.spring.musicplayer5.entity.Track;
import com.spring.musicplayer5.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

public interface TrackService {
    List<Track> findAll();
    Track save(Track entity);
    Optional<Track> findById(long id);
    void deleteById(long id);
    Page<Track> findAll(Pageable pageable);
    Page<Track> findByTitle(String title, Pageable pageable);
    Page<Track> findByAlbum_Id(long id, Pageable pageable);
    List<Track> findByTop(Pageable pageable);

    Page<Track> findByArtistId(long artistId , Pageable pageable);

}
