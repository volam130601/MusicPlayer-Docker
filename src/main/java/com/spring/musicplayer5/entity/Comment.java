package com.spring.musicplayer5.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

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

    @OneToMany(mappedBy = "comment")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<CommentReplies> commentRepliesList;

    @OneToMany(mappedBy = "comment")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<LikesOfComment> likesOfComments;
}
