package com.spring.musicplayer5.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
@Entity
public class Playlist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "username")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

//    @ManyToMany(mappedBy = "playlists")
//    // LAZY để tránh việc truy xuất dữ liệu không cần thiết. Lúc nào cần thì mới query
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private List<Track> tracks;
}
