package com.spring.musicplayer5.controllers;

import com.spring.musicplayer5.entity.TrackPlaylist;
import com.spring.musicplayer5.services.TrackPlaylistService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class PlaylistHasTrackControllerTest {
    @Autowired
    private TrackPlaylistService service;
    @Test
    public void saveTrackPlaylist() {
        List<TrackPlaylist> list = service.getAll();
        for (TrackPlaylist trackPlaylist : list) {
            System.out.println(trackPlaylist.getPlaylist()+"\n======"+trackPlaylist.getTrack());
        }
        //Need ID playlist , ID Track
        TrackPlaylist trackPlaylist = TrackPlaylist.builder()
                .playlist(null)
                .track(null)
                .build();
    }
}