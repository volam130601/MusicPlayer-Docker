package com.spring.musicplayer5.controllers;

import com.spring.musicplayer5.dto.PlaylistHasTrackDto;
import com.spring.musicplayer5.dto.ResponseObject;
import com.spring.musicplayer5.dto.TrackDto;
import com.spring.musicplayer5.entity.Playlist;
import com.spring.musicplayer5.entity.Track;
import com.spring.musicplayer5.entity.TrackPlaylist;
import com.spring.musicplayer5.entity.embedable.TrackPlaylistCompositeKey;
import com.spring.musicplayer5.services.PlaylistService;
import com.spring.musicplayer5.services.TrackPlaylistService;
import com.spring.musicplayer5.services.TrackService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/playlist_has_track")
public class PlaylistHasTrackController {
    @Autowired
    private TrackPlaylistService trackPlaylistService;
    @Autowired
    private TrackService trackService;
    @Autowired
    private PlaylistService playlistService;

    @GetMapping("/getAll")
    public ResponseEntity<ResponseObject> getAll() {
        List<TrackPlaylist> list = trackPlaylistService.getAll();
        if(list.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK" , "Data is Empty!" ,
                            null)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK" , "Get Data of Track Has Playlist!" ,
                        list)
        );
    }

    @GetMapping("/getByPlaylistId")
    public ResponseEntity<ResponseObject> getByIdPlaylist(@RequestParam long playlistId) {
        List<TrackPlaylist> list = trackPlaylistService.findByIdPlaylistId(playlistId);
        List<TrackDto> trackDtos = new ArrayList<>();
        list.forEach(l -> {
            TrackDto trackDto = new TrackDto();
            BeanUtils.copyProperties(l.getTrack() , trackDto);
            trackDtos.add(trackDto);
        });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK" , "Get List By PlaylistID Success!" , trackDtos)
        );
    }

    @PostMapping("/addNew")
    public ResponseEntity<ResponseObject> saveTrackHasPlaylist(@RequestBody PlaylistHasTrackDto phl) {
        Optional<Track> existsTrack = trackService.findById(phl.getTrackId());
        Optional<Playlist> existsPlaylist = playlistService.findById(phl.getPlaylistId());
        if(existsTrack.isPresent() && existsPlaylist.isPresent()) {
            if (!trackPlaylistService.existsByIdTrackIdAndId_PlaylistId(phl.getTrackId(), phl.getPlaylistId())) {
                TrackPlaylistCompositeKey tplKey = TrackPlaylistCompositeKey.builder()
                        .trackId(phl.getTrackId())
                        .playlistId(phl.getPlaylistId())
                        .build();
                TrackPlaylist trackPlaylist = TrackPlaylist.builder()
                        .id(tplKey)
                        .track(existsTrack.get())
                        .playlist(existsPlaylist.get())
                        .build();
                trackPlaylistService.save(trackPlaylist);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("OK" , "Insert data of Playlist Has Track is Success!" ,
                                trackPlaylist)
                );
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("EXISTS" , "Data is exists!" ,"")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("FAILED" , "Data of Track and Playlist isn't exists!" ,"")
        );
    }

    @DeleteMapping("/deleteByTrackIdInPlaylist")
    public ResponseEntity<ResponseObject> deleteByTrackIdInPlaylist(@RequestParam(required=false) long playlistId ,
                                                                    @RequestParam(required=false) long trackId) {
        if (trackPlaylistService.existsByIdTrackIdAndId_PlaylistId(trackId, playlistId)) {
            trackPlaylistService.deleteByIdPlaylistIdAndIdTrackId(playlistId ,trackId);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK" , "Delete Data is Success!" ,"success")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("FAILED" , "Data isn't Exists!" ,"error")
        );
    }

}
