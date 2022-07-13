package com.spring.musicplayer5.dto.mapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class AlbumDtoMap {
    private long id;
    private String title;
    private String label;
    private String link;
    private String cover;
    private String cover_small;
    private String cover_medium;
    private String cover_big;
    private String release_date;
    private long genre_id;

    private String cover_xl;
    private String type;
    private String md5_image;
    private String tracklist;
    private String share;
    private String record_type;
    private String upc;
    private boolean available;
    private boolean explicit_lyrics;
    private Integer nb_tracks;
    private Integer fans;
    private Integer duration;
    private Integer explicit_content_lyrics;
    private Integer explicit_content_cover;
    private Object artist;
    private Object genres;
    private Object tracks;
    private Object contributors;
}
