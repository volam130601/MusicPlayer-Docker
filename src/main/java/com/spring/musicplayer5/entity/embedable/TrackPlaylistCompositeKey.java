package com.spring.musicplayer5.entity.embedable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
@Embeddable @Builder @Data @AllArgsConstructor @NoArgsConstructor
public class TrackPlaylistCompositeKey implements Serializable{

    @Column(name = "playlist_id")
    long playlistId;

    @Column(name = "track_id")
    long trackId;
}
