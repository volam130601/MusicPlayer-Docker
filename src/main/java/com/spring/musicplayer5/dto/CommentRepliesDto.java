package com.spring.musicplayer5.dto;

import com.spring.musicplayer5.entity.Comment;
import com.spring.musicplayer5.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class CommentRepliesDto {
    private long id;
    private long comment_id;
    private User user;
    private String username;
    private String content;

    private int likes;
    private int dislikes;
    private Date createAt;
}
