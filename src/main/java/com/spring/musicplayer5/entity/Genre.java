package com.spring.musicplayer5.entity;

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

    @OneToMany(mappedBy = "genre")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<Album> albums;
}
