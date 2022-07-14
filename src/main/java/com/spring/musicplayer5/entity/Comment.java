package com.spring.musicplayer5.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor @NoArgsConstructor
@Entity @Builder
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String content;
    private Date createAt;
    private int likes;
    private int dislikes;

    @ManyToOne
    @JoinColumn(name = "track_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Track track;

    @ManyToOne
    @JoinColumn(name = "user_name")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;
}
