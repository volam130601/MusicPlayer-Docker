package com.spring.musicplayer5.dto;

import com.spring.musicplayer5.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class LikeOfCommentDto {
    private long comment_id;
    private String username;

    private int likes;
    private int dislikes;

    private boolean isLiked;
    private boolean isDisliked;
}
