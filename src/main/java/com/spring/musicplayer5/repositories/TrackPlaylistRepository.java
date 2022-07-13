package com.spring.musicplayer5.repositories;

import com.spring.musicplayer5.entity.TrackPlaylist;
import com.spring.musicplayer5.entity.embedable.TrackPlaylistCompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TrackPlaylistRepository extends JpaRepository<TrackPlaylist , TrackPlaylistCompositeKey> {
    List<TrackPlaylist> findByIdTrackId(long trackId);
    List<TrackPlaylist> findByIdPlaylistId(long playlistId);
    boolean existsByIdTrackIdAndId_PlaylistId(long trackId , long playlistId);

    @Modifying
    @Query("DELETE FROM TrackPlaylist tpl WHERE tpl.id.playlistId = ?1")
    void deleteByIdPlaylistId(long playlistId);

    void deleteByIdPlaylistIdAndIdTrackId(long playlistId,long trackId);

}
