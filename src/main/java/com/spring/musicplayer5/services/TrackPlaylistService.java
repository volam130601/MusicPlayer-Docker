package com.spring.musicplayer5.services;

import com.spring.musicplayer5.entity.Artist;
import com.spring.musicplayer5.entity.TrackPlaylist;
import com.spring.musicplayer5.entity.embedable.TrackPlaylistCompositeKey;

import java.util.List;
import java.util.Optional;

public interface TrackPlaylistService {
    List<TrackPlaylist> getAll();
    List<TrackPlaylist> findByIdPlaylistId(long playlistId);
    TrackPlaylist save(TrackPlaylist entity);
    boolean existsByIdTrackIdAndId_PlaylistId(long trackId , long playlistId);
    void deleteByIdPlaylistId(long playlistId);
    void deleteByIdPlaylistIdAndIdTrackId(long playlistId,long trackId);
}
