package com.spring.musicplayer5.repositories;

import com.spring.musicplayer5.entity.Album;
import com.spring.musicplayer5.entity.Artist;
import com.spring.musicplayer5.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}
