package com.spring.musicplayer5.utils.mapper;

import com.spring.musicplayer5.dto.SearchDto;
import com.spring.musicplayer5.entity.Album;
import com.spring.musicplayer5.entity.Artist;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TrackMapper {
    @Autowired
    private AlbumMapper albumMapper;
    @Autowired
    private ArtistMapper artistMapper;

    public SearchDto mapperValue(JSONObject json) {
        JSONObject jsonArtist = json.getJSONObject("artist");
        JSONObject jsonAlbum = json.getJSONObject("album");
        Artist artist = artistMapper.mapperValue(jsonArtist);
        Album album = albumMapper.mapperValue(jsonAlbum);

        SearchDto searchDto = SearchDto.builder()
                .id(json.getLong("id"))
                .type(json.getString("type"))
                .artist(artist)
                .album(album)
                .link(json.getString("link"))
                .title(json.getString("title"))
                .duration(json.getInt("duration"))
                .build();
        return searchDto;
    }

}
