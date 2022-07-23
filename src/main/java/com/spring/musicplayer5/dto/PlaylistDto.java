package com.spring.musicplayer5.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class PlaylistDto {
    private String id;
    private String name;
    private String username;
    private String rename;
}
