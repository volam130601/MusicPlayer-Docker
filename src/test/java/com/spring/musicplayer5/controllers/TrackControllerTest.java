package com.spring.musicplayer5.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.musicplayer5.entity.Track;
import com.spring.musicplayer5.repositories.TrackRepository;
import com.spring.musicplayer5.services.TrackService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class TrackControllerTest {

    @Autowired
    private TrackService trackService;

    @Test
    void testPageTracks() {
        Pageable pageable = PageRequest.of(0 , 5);
        Page<Track> tracks = trackService.findAll(pageable);
        tracks.forEach(t ->{
            System.out.println(t.getId());
        });
    }

    @Test
    public void ids_rapidAPI() throws IOException, JSONException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://deezerdevs-deezer.p.rapidapi.com/search?q=eminem")
                .get()
                .addHeader("X-RapidAPI-Key", "4dd52768admshc84996327dfdd8cp18363ajsn766e280ff097")
                .addHeader("X-RapidAPI-Host", "deezerdevs-deezer.p.rapidapi.com")
                .build();

        Response response = client.newCall(request).execute();
        ObjectMapper objectMapper = new ObjectMapper();
        ResponseBody responseBody = client.newCall(request).execute().body();
        JSONObject jsonObject = new JSONObject(responseBody.string());
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        //List ID of *Search API in RapidID
        List<Long> ids = new ArrayList<>();
        for(int i = 0 ; i < jsonArray.length() ; i++) {
            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
            ids.add(jsonObject1.getLong("id"));
//                System.out.println(searchMapper.mapperValue(jsonObject1));
        }
        ids.forEach(i -> {
            System.out.println(i);
        });
    }


}