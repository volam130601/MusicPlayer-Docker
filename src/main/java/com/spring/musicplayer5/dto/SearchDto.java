package com.spring.musicplayer5.dto;

import com.spring.musicplayer5.entity.Album;
import com.spring.musicplayer5.entity.Artist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
public class SearchDto {
    private long id;
    private String type;
    private Artist artist;
    private Album album;
    private String link;
    private String title;
    private Integer duration;
}
