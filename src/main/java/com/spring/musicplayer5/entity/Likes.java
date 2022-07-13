package com.spring.musicplayer5.entity;

import com.spring.musicplayer5.entity.embedable.LikeId;
import lombok.*;
import org.jetbrains.annotations.NotNull;

import javax.persistence.*;

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
@Entity
public class Likes {
    @EmbeddedId
    private LikeId id;

    private Boolean isLike;

    @ManyToOne
    @MapsId(value = "username")
    @JoinColumn(name = "username")
    private User user;

    @ManyToOne
    @MapsId(value = "track_id")
    @JoinColumn(name = "track_id")
    private Track track;
}
