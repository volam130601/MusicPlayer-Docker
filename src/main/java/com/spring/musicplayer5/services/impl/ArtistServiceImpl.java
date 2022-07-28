package com.spring.musicplayer5.services.impl;

import com.spring.musicplayer5.entity.Artist;
import com.spring.musicplayer5.repositories.ArtistRepository;
import com.spring.musicplayer5.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistServiceImpl implements ArtistService {
    @Autowired
    private ArtistRepository artistRepository;

    @Override
    public List<Artist> findAll() {
        return artistRepository.findAll();
    }

    @Override
    public Artist save(Artist entity) {
        return artistRepository.save(entity);
    }

    @Override
    public Optional<Artist> findById(long id) {
        return artistRepository.findById(id);
    }

    @Override
    public void deleteById(long id) {
        artistRepository.deleteById(id);
    }

    @Override
    public Page<Artist> findAll(Pageable pageable) {
        return artistRepository.findAll(pageable);
    }
}
