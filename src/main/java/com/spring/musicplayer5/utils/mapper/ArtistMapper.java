package com.spring.musicplayer5.utils.mapper;

import com.spring.musicplayer5.entity.Artist;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class ArtistMapper {
    public Artist mapperValue(JSONObject json) {
        long id =Long.parseLong(json.getString("id"));
        String name = json.getString("name");
        String picture = json.getString("picture");
        Artist artist = Artist.builder()
                .id(json.getLong("id"))
                .name(json.getString("name"))
                .picture(json.getString("picture"))
                .build();
        return artist;
    }

}
