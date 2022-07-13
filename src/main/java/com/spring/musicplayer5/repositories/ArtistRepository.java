package com.spring.musicplayer5.repositories;

import com.spring.musicplayer5.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
}
