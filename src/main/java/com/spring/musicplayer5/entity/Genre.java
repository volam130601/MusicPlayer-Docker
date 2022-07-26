package com.spring.musicplayer5.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
@Entity
public class Genre {
    @Id
    private long id;
    private String name;
    private String picture;
    private String picture_small;
    private String picture_medium;
    private String picture_big;
    private String picture_xl;

    @OneToMany(mappedBy = "genre")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private List<Album> albums;
}
