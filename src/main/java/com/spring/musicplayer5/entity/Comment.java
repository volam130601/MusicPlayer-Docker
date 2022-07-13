package com.spring.musicplayer5.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String detail;
    private Date createAt;
    private String createBy;

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
