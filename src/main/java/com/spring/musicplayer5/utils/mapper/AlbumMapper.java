package com.spring.musicplayer5.utils.mapper;

import com.spring.musicplayer5.entity.Album;
import com.spring.musicplayer5.entity.Artist;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class AlbumMapper {
    public Album mapperValue(JSONObject json) {
        long id =Long.parseLong(json.getString("id"));
        String name = json.getString("title");
        String cover = json.getString("cover");
        String cover_medium = json.getString("cover_medium");
        Album album = Album.builder()
                .id(json.getLong("id"))
                .title(json.getString("title"))
                .cover(json.getString("cover"))
                .cover_medium(json.getString("cover_medium"))
                .build();
        return album;
    }

}
