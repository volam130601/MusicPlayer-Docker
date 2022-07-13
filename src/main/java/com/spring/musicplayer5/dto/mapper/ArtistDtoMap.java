package com.spring.musicplayer5.dto.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ArtistDtoMap {
    private long id;
    private String name;
    private String link;
    private String picture;
    private String picture_small;
    private String picture_medium;
    private String picture_big;
    private String picture_xl;
    private String share;
    private String type;
    private String tracklist;
    private boolean radio;
}
