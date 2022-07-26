package com.spring.musicplayer5.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.musicplayer5.dto.TrackDto;
import com.spring.musicplayer5.dto.mapper.AlbumDtoMap;
import com.spring.musicplayer5.dto.mapper.ArtistDtoMap;
import com.spring.musicplayer5.dto.mapper.GenreDtoMap;
import com.spring.musicplayer5.dto.mapper.TrackDtoMap;
import com.spring.musicplayer5.entity.Album;
import com.spring.musicplayer5.entity.Artist;
import com.spring.musicplayer5.entity.Genre;
import com.spring.musicplayer5.entity.Track;
import com.spring.musicplayer5.repositories.AlbumRepository;
import com.spring.musicplayer5.repositories.ArtistRepository;
import com.spring.musicplayer5.repositories.TrackRepository;
import com.spring.musicplayer5.services.AlbumService;
import com.spring.musicplayer5.services.ArtistService;
import com.spring.musicplayer5.services.GenreService;
import com.spring.musicplayer5.services.TrackService;
import com.spring.musicplayer5.utils.ListID;
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
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.util.*;

@SpringBootTest
public class PushDataIntoDatabase {

    @Autowired
    private TrackService trackService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ArtistService artistService;
    @Autowired
    private GenreService genreService;

    private List<String> listArtistName = listArtist();
    static List<String> listArtist() {
        List<String> list = new ArrayList<>();
        list.add("eminem");
        list.add("justin%20bieber");
        list.add("maroon");
        list.add("Taylor%20Swift");
        list.add("Martin%20Garrix");
        list.add("son%20tung");
        list.add("Chillies");
        return list;
    }

    @Test
    void getAllData_from_RapidAPI() throws JSONException, IOException {
        getAll_Artist_fromRapidAPI();
        getAll_Album_fromArtist();
        getAll_Artist_fromAlbum();
        getAll_Track_fromAlbumList();
    }
    @Test
    void updateAll() throws JSONException, IOException {
        updateAlbum();
        updateGenre();
        updateArtist();
        updateTrack();
    }

    @Test
    void getAll_Artist_fromRapidAPI() throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient();
        if(!listArtistName.isEmpty()) {
            for (int i = 0 ; i < listArtistName.size() ; i++) {
                String str = listArtistName.get(i);
                //Source Merge data in RapidAPI into DB in MySQL
                Request request = new Request.Builder()
                        .url("https://deezerdevs-deezer.p.rapidapi.com/search?q="+str)
                        .get()
                        .addHeader("X-RapidAPI-Key", "ebbbd71ef7msh69bfbff4e1b5c89p166207jsn760712159459")
                        .addHeader("X-RapidAPI-Host", "deezerdevs-deezer.p.rapidapi.com")
                        .build();
                Response response = client.newCall(request).execute();
                ObjectMapper objectMapper = new ObjectMapper();
                ResponseBody responseBody = client.newCall(request).execute().body();
                JSONObject jsonObject = new JSONObject(responseBody.string());
                if (!jsonObject.toString().contains("\"error\"")) {
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    //List ID of *Search API in RapidID
                    for(int j = 0 ; j < jsonArray.length() ; j++) {
                        JSONObject jsonObject_temp = jsonArray.getJSONObject(j);
                        TrackDtoMap trackDtoMap = objectMapper.readValue(jsonObject_temp.toString() , TrackDtoMap.class);
                        ArtistDtoMap artistDtoMap = objectMapper.convertValue(trackDtoMap.getArtist(),ArtistDtoMap.class);
                        Artist artist = new Artist();
                        BeanUtils.copyProperties(artistDtoMap , artist);
                        artistService.save(artist);
                    }
                } else i--;
            }
        }
    }

    @Test
    void getAll_Album_fromArtist() throws IOException, JSONException {
        List<Artist> artistList = artistService.findAll();
        OkHttpClient client = new OkHttpClient();
        for (int i = 0 ; i < artistList.size() ; i++){
            Artist artist = artistList.get(i);
            Request request = new Request.Builder()
                    .url("https://api.deezer.com/artist/"+artist.getId()+"/top?limit=50")
                    .get()
                    .build();
            Response response = client.newCall(request).execute();
            ObjectMapper objectMapper = new ObjectMapper();
            ResponseBody responseBody = client.newCall(request).execute().body();
            JSONObject jsonObject = new JSONObject(responseBody.string());
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            //List ID of *Search API in RapidID
            for(int j = 0 ; j < jsonArray.length() ; j++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(j);
                TrackDtoMap trackDtoMap = objectMapper.readValue(jsonObject1.toString() , TrackDtoMap.class);
                AlbumDtoMap albumDtoMap = objectMapper.convertValue(trackDtoMap.getAlbum(), AlbumDtoMap.class);
                Album album = new Album();
                BeanUtils.copyProperties(albumDtoMap , album);
                albumService.save(album);
            }
        }
    }

    @Test
    void getAll_Artist_fromAlbum() throws IOException, JSONException {
        List<Album> albumList = albumService.findAll();
        OkHttpClient client = new OkHttpClient();
        for (int i = 0 ; i < albumList.size() ; i++){
            Album album = albumList.get(i);
            Request request = new Request.Builder()
                    .url("https://api.deezer.com/album/"+album.getId()+"/tracks")
                    .get()
                    .build();
            Response response = client.newCall(request).execute();
            ObjectMapper objectMapper = new ObjectMapper();
            ResponseBody responseBody = client.newCall(request).execute().body();
            JSONObject jsonObject = new JSONObject(responseBody.string());
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            //List ID of *Search API in RapidID
            for(int j = 0 ; j < jsonArray.length() ; j++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(j);
                TrackDtoMap trackDtoMap = objectMapper.readValue(jsonObject1.toString() , TrackDtoMap.class);
                ArtistDtoMap artistDtoMap = objectMapper.convertValue(trackDtoMap.getArtist(), ArtistDtoMap.class);
                Artist artist = new Artist();
                BeanUtils.copyProperties(artistDtoMap , artist);
                artistService.save(artist);
            }
        }
    }

    @Test
    void updateAlbum() throws IOException, JSONException {
        List<Album> albumList = albumService.findAll();
        OkHttpClient client = new OkHttpClient();
        for (int i = 0 ; i < albumList.size() ; i++){
            Album album = albumList.get(i);
            Request request = new Request.Builder()
                    .url("https://deezerdevs-deezer.p.rapidapi.com/album/"+album.getId())
                    .get()
                    .addHeader("X-RapidAPI-Key", "ebbbd71ef7msh69bfbff4e1b5c89p166207jsn760712159459")
                    .addHeader("X-RapidAPI-Host", "deezerdevs-deezer.p.rapidapi.com")
                    .build();

            Response response = client.newCall(request).execute();
            ObjectMapper objectMapper = new ObjectMapper();
            ResponseBody responseBody = client.newCall(request).execute().body();
            JSONObject jsonObject = new JSONObject(responseBody.string());
            if (!jsonObject.toString().contains("\"error\"")) {
                AlbumDtoMap albumDtoMap = objectMapper.readValue(jsonObject.toString() , AlbumDtoMap.class);
                BeanUtils.copyProperties(albumDtoMap, album);
                Genre genre = new Genre();
                if(albumDtoMap.getGenre_id() > 0) {
                    genre.setId(albumDtoMap.getGenre_id());
                    genreService.save(genre);
                    album.setGenre(genre);
                }
                albumService.save(album);
            } else i--;
        }
    }

    @Test
    void  updateGenre() throws IOException, JSONException {
        List<Genre> genreList = genreService.findAll();
        OkHttpClient client = new OkHttpClient();
        for (int i = 0 ; i < genreList.size(); i++) {
            Genre genre = genreList.get(i);
            if(genre.getId() > 0) {
                Request request = new Request.Builder()
                        .url("https://deezerdevs-deezer.p.rapidapi.com/genre/"+genre.getId())
                        .get()
                        .addHeader("X-RapidAPI-Key", "ebbbd71ef7msh69bfbff4e1b5c89p166207jsn760712159459")
                        .addHeader("X-RapidAPI-Host", "deezerdevs-deezer.p.rapidapi.com")
                        .build();

                Response response = client.newCall(request).execute();
                ObjectMapper objectMapper = new ObjectMapper();
                ResponseBody responseBody = client.newCall(request).execute().body();
                JSONObject jsonObject = new JSONObject(responseBody.string());
                if (!jsonObject.toString().contains("\"error\"")) {
                    GenreDtoMap genreDtoMap = objectMapper .readValue(jsonObject.toString() , GenreDtoMap.class);
                    BeanUtils.copyProperties(genreDtoMap , genre);
                    genreService.save(genre);
                } else i--;
            }
        }
    }

    @Test
    void updateArtist() throws IOException, JSONException {
        List<Artist> artistList = artistService.findAll();
        OkHttpClient client = new OkHttpClient();
        for (int i = 0 ; i < artistList.size() ; i++){
            Artist artist = artistList.get(i);
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
            if (!jsonObject.toString().contains("\"error\"")) {
                ArtistDtoMap artistDtoMap = objectMapper.readValue(jsonObject.toString() , ArtistDtoMap.class);
                BeanUtils.copyProperties(artistDtoMap, artist);
                artistService.save(artist);
            } else i--;
        }
    }

    @Test
    void getAll_Track_fromAlbumList() throws JSONException, IOException {
        OkHttpClient client = new OkHttpClient();
        List<Album> albumList = albumService.findAll();
        for(Album  album : albumList) {
            Request request = new Request.Builder()
                    .url(album.getTracklist())
                    .get()
                    .build();
            Response response = client.newCall(request).execute();
            ObjectMapper objectMapper = new ObjectMapper();
            ResponseBody responseBody = client.newCall(request).execute().body();
            JSONObject jsonObject = new JSONObject(responseBody.string());
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for(int i = 0 ; i < jsonArray.length() ; i++) {
                JSONObject _jsonObject = jsonArray.getJSONObject(i);
                TrackDtoMap trackDtoMap = objectMapper.readValue(_jsonObject.toString() , TrackDtoMap.class);
                ArtistDtoMap artistDtoMap = objectMapper.convertValue(trackDtoMap.getArtist(), ArtistDtoMap.class);
                Track track = new Track();
                BeanUtils.copyProperties(trackDtoMap, track);
                Artist artist = new Artist();
                BeanUtils.copyProperties(artistDtoMap , artist);
                track.setAlbum(album);
                track.setArtist(artist);
                trackService.save(track);
                System.out.println(track);
            }
        }
    }

    @Test
    void updateTrack() throws IOException, JSONException {
        List<Track> trackList = trackService.findAll();
        OkHttpClient client = new OkHttpClient();
        for (int i = 0 ; i < trackList.size() ; i++){
            Track track = trackList.get(i);
            Request request = new Request.Builder()
                    	.url("https://deezerdevs-deezer.p.rapidapi.com/track/"+track.getId())
                    	.get()
                    	.addHeader("X-RapidAPI-Key", "ebbbd71ef7msh69bfbff4e1b5c89p166207jsn760712159459")
                    	.addHeader("X-RapidAPI-Host", "deezerdevs-deezer.p.rapidapi.com")
                    	.build();
            Response response = client.newCall(request).execute();
            ObjectMapper objectMapper = new ObjectMapper();
            ResponseBody responseBody = client.newCall(request).execute().body();
            JSONObject jsonObject = new JSONObject(responseBody.string());
            if (!jsonObject.toString().contains("\"error\"")) {
                TrackDtoMap trackDtoMap = objectMapper.readValue(jsonObject.toString() , TrackDtoMap.class);
                BeanUtils.copyProperties(trackDtoMap, track);
                trackService.save(track);
            } else i--;
        }
    }

}
