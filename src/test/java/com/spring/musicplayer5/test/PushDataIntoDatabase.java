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
import com.spring.musicplayer5.services.AlbumService;
import com.spring.musicplayer5.services.ArtistService;
import com.spring.musicplayer5.services.TrackService;
import com.spring.musicplayer5.utils.component.ListID;
import com.spring.musicplayer5.utils.mapper.AlbumMapper;
import com.spring.musicplayer5.utils.mapper.ArtistMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
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
    private AlbumService albumService;
    @Autowired
    private ArtistRepository artistRepository;
    @Autowired
    private AlbumRepository albumRepository;
    @Autowired
    private TrackRepository trackRepository;

    @Test
    void pushData_Track() throws JSONException, IOException {
        List<Long> ids = listID.ids_rapidAPI();
        System.out.println(ids);
        if(ids != null) {
            for (int i = 0 ; i < ids.size() ; i++) {
                long id = ids.get(i);
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

                    //push data into Track
//                    trackService.save(track);
                    //push data into Artist and Album
                    System.out.println(artist.getId() + "<><>"+artist.getName());
//                    artistRepository.save(artist);
//                    albumRepository.save(album);
                } else {
//                    System.out.println("<<<<<<"+i+">>>>"+jsonObject.toString());
                    i--;
                }
            }
        }
    }

    @Autowired
    private ArtistService artistService;

    @Test
    public void pushData_Artist() throws IOException, JSONException {
        List<Artist> artists = artistService.findAll();
        OkHttpClient client = new OkHttpClient();
        for (int i = 0 ; i < artists.size() ; i++) {
            Artist artist = artists.get(i);
            Request request = new Request.Builder()
                    .url("https://deezerdevs-deezer.p.rapidapi.com/artist/"+artist.getId())
                    .get()
                    .addHeader("X-RapidAPI-Key", "ebbbd71ef7msh69bfbff4e1b5c89p166207jsn760712159459")
                    .addHeader("X-RapidAPI-Host", "deezerdevs-deezer.p.rapidapi.com")
                    .build();
            Response response = client.newCall(request).execute();
            ObjectMapper objectMapper = new ObjectMapper();
            ResponseBody responseBody = client.newCall(request).execute().body();
            JSONObject jsonObject = new JSONObject(responseBody.string());
            if (!jsonObject.toString().contains("error")) {
                ArtistDtoMap artistDtoMap = objectMapper.readValue(jsonObject.toString(), ArtistDtoMap.class);
                Artist artist1 = new Artist();
                BeanUtils.copyProperties(artistDtoMap , artist1);
                System.out.println(artist1.getId() + "<><>"+artist1.getName());
//                artistService.save(artist1);
            } else {
//                System.out.println("<<<<"+i+">>>>>>>"+jsonObject.toString());
                i--;
            }
        }

    }


    @Test
    public void getData_LinkAddress_PushIntoTrack() throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient();
        List<Artist> artists = artistService.findAll();
        List<Album> albumList = new ArrayList<>();
        List<Track> trackList = new ArrayList<>();
        for (int i = 0; i < artists.size(); i++) {
            Artist art = artists.get(i);
            Request request = new Request.Builder()
                    .url("https://api.deezer.com/artist/"+art.getId()+"/top?limit=50")
                    .get()
                    .build();
            Response response = client.newCall(request).execute();
            ObjectMapper objectMapper = new ObjectMapper();
            ResponseBody responseBody = client.newCall(request).execute().body();
            JSONObject jsonObject = new JSONObject(responseBody.string());
            if (!jsonObject.toString().contains("error")) {
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                //List ID of *Search API in RapidID
                for(int j = 0 ; j < jsonArray.length() ; j++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(j);
                    TrackDtoMap trackDtoMap = objectMapper.readValue(jsonObject1.toString() , TrackDtoMap.class);
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
                    trackList.add(track);
//                    System.out.println(track.getId() + "<><<>>" + track.getTitle());
                    //Add album
//                    if(checkAlbum_Title(albumList , album.getTitle())) {
//                        albumList.add(album);
//                    }
                    if(checkTrack_Title(trackList , track.getTitle())) {
                        trackList.add(track);
                    }
                }
            } else i--;
        }
//        albumList.forEach(album -> {
//            albumService.save(album);
//        });
        trackList.forEach(track -> {
            trackService.save(track);
        });
    }

    static boolean checkAlbum_Title(List<Album> albumList , String title) {
        for (Album album : albumList)
            if(album.getTitle().equals(title)) return false;
        return true;
    }
    static boolean checkTrack_Title(List<Track> trackList , String title) {
        for (Track track : trackList)
            if(track.getTitle().equals(title)) return false;
        return true;
    }

}
