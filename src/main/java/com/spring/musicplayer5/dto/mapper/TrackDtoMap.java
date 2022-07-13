package com.spring.musicplayer5.dto.mapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor @NoArgsConstructor
public class TrackDtoMap {
    private long id;
    private String title;
    private String link;
    private Integer duration;
    private String preview;
    private Object artist;
    private Object album;
    private ArtistDtoMap artistDtoMap;
    private AlbumDtoMap albumDtoMap;
    private Integer rank;

    private String md5_image;
    private String type;
    private Double gain;
    private String share;
    private Integer explicit_content_lyrics;
    private Integer track_position;
    private String bpm;
    private Boolean readable;
    private Integer explicit_content_cover;
    private String isrc;
    private String title_version;
    private Boolean explicit_lyrics;
    private String title_short;
    private Integer disk_number;
    private Object available_countries;
    private String release_date;
    private Object contributors;
}

