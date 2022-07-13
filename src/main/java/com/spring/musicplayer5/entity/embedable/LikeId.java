package com.spring.musicplayer5.entity.embedable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data @Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class LikeId implements Serializable {
    @Column(name = "track_id")
    long trackId;
    @Column(name = "username")
    String username;
}
