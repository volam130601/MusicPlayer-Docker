package com.spring.musicplayer5.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data @AllArgsConstructor @NoArgsConstructor
public class CommentDto {
    private long id;
    private long trackId;
    private String username;
    private String content;
    private int likes;
    private int dislikes;
    private Date createAt;
}
