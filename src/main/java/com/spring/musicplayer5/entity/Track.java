package com.spring.musicplayer5.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
public class Track {
    @Id
    private long id;
    private String title;
    private String link;
    private Integer duration;
    private String preview;
    private String release_date;

    @Column(name = "ranks")
    private Integer rank;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "artist_id")
    private Artist artist;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "album_id")
    private Album album;

    @OneToMany(mappedBy = "track")
    @ToString.Exclude
    @JsonIgnore
    private List<Comment> comments;

    @OneToMany(mappedBy = "track")
    @ToString.Exclude
    @JsonIgnore
    private List<Likes> likes;

    //Chuyển qua dùng hết @ManyToOne
//    @ManyToMany(cascade = CascadeType.ALL)
//    @ToString.Exclude
//    @JsonIgnore
//    @JoinTable(name = "Track_has_Playlist",
//            joinColumns = @JoinColumn(name = "track_id"),
//            inverseJoinColumns = @JoinColumn(name = "playlist_id")
//    )
//    private List<Playlist> playlists;

//    @OneToMany(mappedBy = "track")
//    @JsonIgnore
//    private Collection<TrackPlaylist> trackPlaylist;
//
//    public Collection<TrackPlaylist> getTrackPlaylist() {
//        return trackPlaylist;
//    }
//
//    public void setTrackPlaylist(Collection<TrackPlaylist> trackPlaylist) {
//        this.trackPlaylist = trackPlaylist;
//    }
}
