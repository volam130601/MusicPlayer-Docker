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
    List<Track> findByTitle(String title);

    List<Track> findByAlbum_Id(long id);
    List<Track> findByTop(Pageable pageable);


}
