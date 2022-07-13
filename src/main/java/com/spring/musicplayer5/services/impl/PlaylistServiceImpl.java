package com.spring.musicplayer5.services.impl;

import com.spring.musicplayer5.entity.Playlist;
import com.spring.musicplayer5.repositories.PlaylistRepository;
import com.spring.musicplayer5.services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaylistServiceImpl implements PlaylistService {
    @Autowired
    private PlaylistRepository playlistRepository;
    @Override
    public List<Playlist> getAll() {
        return playlistRepository.findAll();
    }

    @Override
    public Playlist save(Playlist entity) {
        return playlistRepository.save(entity);
    }

    @Override
    public Optional<Playlist> findById(long id) {
        return playlistRepository.findById(id);
    }

    @Override
    public void deleteById(long id) {
        playlistRepository.deleteById(id);
    }

    @Override
    public Optional<Playlist> findByName(String name) {
        return playlistRepository.findByName(name);
    }

    @Override
    public List<Playlist> findPlaylistByUsername(String username) {
        return playlistRepository.findPlaylistByUsername(username);
    }
}
