package com.spring.musicplayer5.repositories;

import com.spring.musicplayer5.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PlaylistRepository extends JpaRepository<Playlist , Long> {
    Optional<Playlist> findByName(String name);

    @Query("select pl from Playlist pl where pl.user.username = ?1")
    List<Playlist> findPlaylistByUsername(String username);
}
