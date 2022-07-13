package com.spring.musicplayer5.test;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.musicplayer5.dto.SearchDto;
import com.spring.musicplayer5.entity.Album;
import com.spring.musicplayer5.entity.Artist;
import com.spring.musicplayer5.utils.mapper.AlbumMapper;
import com.spring.musicplayer5.utils.mapper.ArtistMapper;
import com.spring.musicplayer5.utils.mapper.SearchMapper;
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

import java.io.DataInput;
import java.io.IOException;
import java.util.*;

@SpringBootTest
public class testMergeDataOnRapidAPI {
    @Autowired
    private ArtistMapper artistMapper;
    @Autowired
    private AlbumMapper albumMapper;
    @Autowired
    private SearchMapper searchMapper;

    @Test
    public void getById() {
        try {
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://deezerdevs-deezer.p.rapidapi.com/artist/EMINEM")
                    .get()
                    .addHeader("X-RapidAPI-Key", "4dd52768admshc84996327dfdd8cp18363ajsn766e280ff097")
                    .addHeader("X-RapidAPI-Host", "deezerdevs-deezer.p.rapidapi.com")
                    .build();

            Response response = client.newCall(request).execute();
            ObjectMapper objectMapper = new ObjectMapper();
            ResponseBody responseBody = client.newCall(request).execute().body();
            JSONObject json = new JSONObject(responseBody.string());
            Artist artist = artistMapper.mapperValue(json);
//            System.out.println(artist.toString());
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getAllOnSearch() {
        try {
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
            System.out.println(ids.toString());

        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
