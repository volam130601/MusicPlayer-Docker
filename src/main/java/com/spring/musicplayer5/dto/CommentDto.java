package com.spring.musicplayer5.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data @AllArgsConstructor @NoArgsConstructor
public class CommentDto {
    private long comment_id;
    private long comment_replies_id;

    private long track_id;
    private String username;
    private String content;
    private Date createAt;

    private int likes;
    private int dislikes;

    private Boolean isLiked;
    private Boolean isDisliked;

}
