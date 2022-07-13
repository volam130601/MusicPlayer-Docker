package com.spring.musicplayer5.services.impl;

import com.spring.musicplayer5.entity.TrackPlaylist;
import com.spring.musicplayer5.entity.embedable.TrackPlaylistCompositeKey;
import com.spring.musicplayer5.repositories.TrackPlaylistRepository;
import com.spring.musicplayer5.services.TrackPlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Transactional
@Service
public class TrackPlaylistServiceImpl implements TrackPlaylistService {
    @Autowired
    private TrackPlaylistRepository repository;

    @Override
    public List<TrackPlaylist> getAll() {
        return repository.findAll();
    }

    @Override
    public TrackPlaylist save(TrackPlaylist entity) {
        return repository.save(entity);
    }

    @Override
    public boolean existsByIdTrackIdAndId_PlaylistId(long trackId , long playlistId){
        return repository.existsByIdTrackIdAndId_PlaylistId(trackId , playlistId);
    }

    @Override
    public List<TrackPlaylist> findByIdPlaylistId(long playlistId) {
        return repository.findByIdPlaylistId(playlistId);
    }

    @Override
    public void deleteByIdPlaylistId(long playlistId) {
        repository.deleteByIdPlaylistId(playlistId);
    }

    @Override
    public void deleteByIdPlaylistIdAndIdTrackId(long playlistId, long trackId) {
        repository.deleteByIdPlaylistIdAndIdTrackId(playlistId , trackId);
    }
}
