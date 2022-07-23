package com.spring.musicplayer5.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Builder
@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
public class Album {
    @Id
    private long id;
    private String title;
    private String label;
    private String link;
    private String cover;
    private String cover_small;
    private String cover_medium;
    private String cover_big;
    private String cover_xl;
    private String release_date;
    private String tracklist;
    private Integer fans;
    private Integer duration;
    private Integer nb_tracks;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Genre genre;

    @OneToMany(mappedBy = "album")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private List<Track> tracks;
}
