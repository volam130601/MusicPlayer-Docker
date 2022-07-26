package com.spring.musicplayer5.repositories;

import com.spring.musicplayer5.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PlaylistRepository extends JpaRepository<Playlist , Long> {
    Optional<Playlist> findByName(String name);

    @Query(value = "SELECT * FROM dbmusic_temp.playlist where name = ?1 and username = ?2", nativeQuery = true)
    List<Playlist> findByNameAndUserUsername(String name , String username);

    @Query(value = "select * from playlist where username = ?1", nativeQuery = true)
    List<Playlist> findPlaylistByUsername(String username);

}
