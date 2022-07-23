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
        List<String> list = new ArrayList<>();
        list.add("eminem");
        list.add("justin%20bieber");
        list.add("maroon");
        list.add("Taylor%20Swift");
        list.add("Martin%20Garrix");
        list.add("son%20tung");
        list.add("Chillies");
        List<Long> ids = new ArrayList<>();
        for (int i = 0 ; i < list.size() ; i++) {
            String str = list.get(i);
            System.out.println(str);
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
            if (!jsonObject.toString().contains("error")) {
                JSONArray jsonArray = jsonObject.getJSONArray("data");
                //List ID of *Search API in RapidID
                for(int j = 0 ; j < jsonArray.length() ; j++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(j);
                    ids.add(jsonObject1.getLong("id"));
                }
            } else i--;
        }
        return ids;
    }

}
