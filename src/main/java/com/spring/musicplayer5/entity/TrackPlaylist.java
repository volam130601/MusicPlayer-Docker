package com.spring.musicplayer5.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.spring.musicplayer5.entity.embedable.TrackPlaylistCompositeKey;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
@Entity
@Table(name = "Track_has_Playlist")
public class TrackPlaylist {
    @JsonIgnore
    @EmbeddedId
    private TrackPlaylistCompositeKey id;

    @ManyToOne
    @MapsId(value = "playlist_id")
    @JoinColumn(name = "playlist_id")
    private Playlist playlist;

    @ManyToOne
    @MapsId(value = "track_id")
    @JoinColumn(name = "track_id")
    private Track track;
}
