package com.spring.musicplayer5.dto;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class LikeOfCommentDto {
    private long comment_id;
    private String username;
    private int likes;
    private int dislikes;

    private Boolean isLiked;
    private Boolean isDisliked;
}
