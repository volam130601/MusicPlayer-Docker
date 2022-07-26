package com.spring.musicplayer5.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrackDto {
    private long id;
    private String title;
    private String link;
    private Integer duration;
    private String preview;
    private Integer rank;

    private Object artist;
    private Object album;

}
