package com.spring.musicplayer5.controllers.impl;

import com.spring.musicplayer5.dto.ResponseObject;
import com.spring.musicplayer5.entity.Artist;
import com.spring.musicplayer5.entity.Track;
import com.spring.musicplayer5.services.ArtistService;
import com.spring.musicplayer5.services.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/artist")
public class ArtistControllerImpl {
    @Autowired
    private ArtistService artistService;

    @Autowired
    private TrackService trackService;

    @GetMapping
    public ResponseEntity<ResponseObject> getTop5Artist() {
        Page<Artist> artists = artistService.findAll(PageRequest.of(0, 5));
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Get 5 Artist Hot", artists .toList(), artists.toList().size())
        );
    }

    @GetMapping("/get_page")
    public ResponseEntity<ResponseObject> getPageArtist(@RequestParam int page ,
                                                        @RequestParam int size) {
        Page<Artist> artists = artistService.findAll(PageRequest.of(page, size));
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Get paging success!", artists.toList(), artists.toList().size() , page , size)
        );
    }

    @GetMapping("/get_track")
    public ResponseEntity<ResponseObject> getTrack(@RequestParam long artistId) {
        Page<Track> tracks = trackService.findByArtistId(artistId , PageRequest.of(0, 20));
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Get "+tracks.toList().size()+" tracks of Artist successfully!", tracks.toList(), tracks.toList().size())
        );
    }
}
