package com.spring.musicplayer5.services;

import com.spring.musicplayer5.entity.Artist;
import com.spring.musicplayer5.entity.Playlist;

import java.util.List;
import java.util.Optional;

public interface PlaylistService {
    List<Playlist> getAll();
    Playlist save(Playlist entity);
    Optional<Playlist> findById(long id);
    Optional<Playlist> findByName(String name);
    void deleteById(long id);
    List<Playlist> findPlaylistByUsername(String username);
}
