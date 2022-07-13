package com.spring.musicplayer5.services.impl;

import com.spring.musicplayer5.entity.Album;
import com.spring.musicplayer5.entity.Artist;
import com.spring.musicplayer5.repositories.AlbumRepository;
import com.spring.musicplayer5.repositories.ArtistRepository;
import com.spring.musicplayer5.services.AlbumService;
import com.spring.musicplayer5.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumRepository albumRepository;
    @Override
    public List<Album> getAll() {
        return albumRepository.findAll();
    }

    @Override
    public Album save(Album entity) {
        return albumRepository.save(entity);
    }

    @Override
    public Optional<Album> findById(long id) {
        return albumRepository.findById(id);
    }

    @Override
    public void deleteById(long id) {
        albumRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return albumRepository.existsById(id);
    }
}
