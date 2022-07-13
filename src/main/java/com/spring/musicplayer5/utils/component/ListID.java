package com.spring.musicplayer5.utils.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.musicplayer5.entity.Track;
import com.spring.musicplayer5.repositories.TrackRepository;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ListID {

    @Autowired
    private TrackRepository trackRepository;

    public List<Long> ids() {
        List<Track> track = trackRepository.findAll();
        List<Long> ids = new ArrayList<>();
        track.forEach(t -> ids.add(t.getId()));
        return ids;
    }

    public List<Long> ids_rapidAPI() throws IOException {
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
        return ids;
    }

}
