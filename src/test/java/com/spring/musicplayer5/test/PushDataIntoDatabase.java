package com.spring.musicplayer5.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.musicplayer5.dto.mapper.AlbumDtoMap;
import com.spring.musicplayer5.dto.mapper.ArtistDtoMap;
import com.spring.musicplayer5.dto.mapper.TrackDtoMap;
import com.spring.musicplayer5.entity.Album;
import com.spring.musicplayer5.entity.Artist;
import com.spring.musicplayer5.entity.Track;
import com.spring.musicplayer5.repositories.AlbumRepository;
import com.spring.musicplayer5.repositories.ArtistRepository;
import com.spring.musicplayer5.repositories.TrackRepository;
import com.spring.musicplayer5.services.TrackService;
import com.spring.musicplayer5.utils.component.ListID;
import com.spring.musicplayer5.utils.mapper.AlbumMapper;
import com.spring.musicplayer5.utils.mapper.ArtistMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class PushDataIntoDatabase {
    @Autowired
    private ListID listID;

    @Autowired
    private TrackService trackService;
    @Autowired
    private AlbumMapper albumMapper;
    @Autowired
    private ArtistMapper artistMapper;

    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private TrackRepository trackRepository;

    @Test
    void pushData_Track() throws JSONException, IOException {
        List<Long> ids = listID.ids();
        if(ids != null) {
            for (Long id : ids) {
                //Source Merge data in RapidAPI into DB in MySQL
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                            .url("https://deezerdevs-deezer.p.rapidapi.com/track/" + String.valueOf(id))
                            .get()
                            .addHeader("X-RapidAPI-Key", "4dd52768admshc84996327dfdd8cp18363ajsn766e280ff097")
                            .addHeader("X-RapidAPI-Host", "deezerdevs-deezer.p.rapidapi.com")
                            .build();

                Response response = client.newCall(request).execute();
                ObjectMapper objectMapper = new ObjectMapper();
                ResponseBody responseBody = client.newCall(request).execute().body();
                JSONObject jsonObject = new JSONObject(responseBody.string());
                if (!jsonObject.toString().contains("error")) {
                    TrackDtoMap trackDtoMap = objectMapper.readValue(jsonObject.toString() , TrackDtoMap.class);
                    ArtistDtoMap artistDtoMap = objectMapper.convertValue(trackDtoMap.getArtist(), ArtistDtoMap.class);
                    AlbumDtoMap albumDtoMap = objectMapper.convertValue(trackDtoMap.getAlbum(), AlbumDtoMap.class);
                    Track track = new Track();
                    BeanUtils.copyProperties(trackDtoMap, track);
                    Artist artist = new Artist();
                    BeanUtils.copyProperties(artistDtoMap, artist);
                    track.setArtist(artist);
                    Album album = new Album();
                    BeanUtils.copyProperties(albumDtoMap, album);
                    track.setAlbum(album);

                    System.out.println(album);
                    //push data into Track
//                    trackService.save(track);
                    //push data into Artist and Album
//                    artistRepository.save(artist);
//                    albumRepository.save(album);
                }
            }
        }
    }


}
