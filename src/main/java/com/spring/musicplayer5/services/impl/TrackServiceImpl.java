package com.spring.musicplayer5.services.impl;

import com.spring.musicplayer5.entity.Track;
import com.spring.musicplayer5.entity.User;
import com.spring.musicplayer5.repositories.TrackRepository;
import com.spring.musicplayer5.services.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TrackServiceImpl implements TrackService {
    @Autowired
    private TrackRepository trackRepository;

    @Override
    public List<Track> findAll() {
        return trackRepository.findAll();
    }

    @Override
    public Track save(Track entity) {
        return trackRepository.save(entity);
    }

    @Override
    public Optional<Track> findById(long id) {
        return trackRepository.findById(id);
    }

    @Override
    public void deleteById(long id) {
        trackRepository.deleteById(id);
    }

    @Override
    public Page<Track> findAll(Pageable pageable) {
        return trackRepository.findAll(pageable);
    }

    @Override
    public Page<Track> findByTitle(String title ,Pageable pageable) {
        return trackRepository.findByTitle(title, pageable);
    }

    @Override
    public Page<Track> findByAlbum_Id(long id, Pageable pageable) {
        return trackRepository.findByAlbum_Id(id, pageable);
    }

    @Override
    public List<Track> findByTop(Pageable pageable) {
        return trackRepository.findByTop(pageable);
    }


    @Override
    public Page<Track> findByArtistId(long artistId, Pageable pageable) {
        return trackRepository.findByArtistId(artistId , pageable);
    }
}
