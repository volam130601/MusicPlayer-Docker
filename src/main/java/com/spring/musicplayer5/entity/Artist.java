package com.spring.musicplayer5.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
@Entity
public class Artist {
    @Id
    private long id;
    private String name;
    private String picture;

    @OneToMany(mappedBy = "artist")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private List<Track> tracks;
}
