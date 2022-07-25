package com.spring.musicplayer5.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.musicplayer5.dto.AlbumOfTrackDto;
import com.spring.musicplayer5.dto.mapper.AlbumDtoMap;
import com.spring.musicplayer5.entity.Album;
import com.spring.musicplayer5.entity.Track;
import com.spring.musicplayer5.services.AlbumService;
import com.spring.musicplayer5.services.GenreService;
import com.spring.musicplayer5.services.TrackService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
@SpringBootTest
class AlbumControllerTest {
    @Autowired
    private AlbumService albumService;
    @Autowired
    private TrackService trackService;

    @Autowired
    private GenreService genreService;
    @Test
    void pushDataIntoAlbum() throws IOException, JSONException, ParseException {
        List<Album> albums = albumService.findAll();
        List<Long> ids = new ArrayList<>();
        for (Album album : albums) {
            ids.add(album.getId());
        }
        if(ids != null) {
            for (Long id : ids) {
                OkHttpClient client = new OkHttpClient();

                Request request = new Request.Builder()
                        .url("https://deezerdevs-deezer.p.rapidapi.com/album/"+id)
                        .get()
                        .addHeader("X-RapidAPI-Key", "4dd52768admshc84996327dfdd8cp18363ajsn766e280ff097")
                        .addHeader("X-RapidAPI-Host", "deezerdevs-deezer.p.rapidapi.com")
                        .build();

                Response response = client.newCall(request).execute();
                ObjectMapper objectMapper = new ObjectMapper();
                ResponseBody responseBody = client.newCall(request).execute().body();
                JSONObject jsonObject = new JSONObject(responseBody.string());
                if (!jsonObject.toString().contains("error")) {
                    AlbumDtoMap albumDtoMap = objectMapper.readValue(jsonObject.toString(), AlbumDtoMap.class);

                    //Get Tracklist later (not priority)
//                    System.out.println(jsonObject.get("tracklist"));

                    //Merge data into DB: genre
//                    OkHttpClient client_Genre = new OkHttpClient();
//
//                    Request request_Genre = new Request.Builder()
//                            .url("https://deezerdevs-deezer.p.rapidapi.com/genre/"+albumDtoMap.getGenre_id())
//                            .get()
//                            .addHeader("X-RapidAPI-Key", "4dd52768admshc84996327dfdd8cp18363ajsn766e280ff097")
//                            .addHeader("X-RapidAPI-Host", "deezerdevs-deezer.p.rapidapi.com")
//                            .build();
//
//                    Response response_Genre = client_Genre.newCall(request_Genre).execute();
//                    ObjectMapper objectMapper_Genre = new ObjectMapper();
//                    ResponseBody responseBody_Genre = client_Genre.newCall(request_Genre).execute().body();
//                    JSONObject jsonObject_Genre = new JSONObject(responseBody_Genre.string());
//                    if (!jsonObject_Genre.toString().contains("error")) {
//                        GenreDtoMap genreDtoMap = objectMapper_Genre.readValue(jsonObject_Genre.toString(), GenreDtoMap.class);
//                        Genre genre = new Genre();
//                        BeanUtils.copyProperties(genreDtoMap, genre);
//                        genreService.save(genre);
//                    }
                    //Save album
//                    Album album = new Album();
//                    BeanUtils.copyProperties(albumDtoMap,  album);
//                    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(albumDtoMap.getRelease_date());
//                    album.setRelease_date(date);
//                    Genre genre = Genre.builder().id(albumDtoMap.getGenre_id()).build();
//                    album.setGenre(genre);
//                    albumService.save(album);
                }
            }
        }
    }

    @Test
    void getAllAlbumOfTrack() {
        List<Album> albums = albumService.findAll();
        System.out.println(albums);
        List<AlbumOfTrackDto> albumOfTrackDtoList = new ArrayList<>();
        albums.forEach(album -> {
            List<Track> trackList = trackService.findByAlbum_Id(album.getId());
            AlbumOfTrackDto albumOfTrackDto = AlbumOfTrackDto.builder()
                    .album(album)
                    .tracks(trackList).build();
            albumOfTrackDtoList.add(albumOfTrackDto);
        });
        System.out.println(albumOfTrackDtoList);
    }
}