package com.spring.musicplayer5.controllers.impl;

import com.spring.musicplayer5.controllers.AlbumController;
import com.spring.musicplayer5.dto.AlbumOfTrackDto;
import com.spring.musicplayer5.dto.ResponseObject;
import com.spring.musicplayer5.entity.Album;
import com.spring.musicplayer5.entity.Track;
import com.spring.musicplayer5.services.AlbumService;
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

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/album")
public class AlbumControllerImpl implements AlbumController {
    @Autowired
    private TrackService trackService;
    @Autowired
    private AlbumService albumService;

    @Override
    @GetMapping("/get_track")
    public ResponseEntity<ResponseObject> getTracksBelongIdAlbum(@RequestParam long albumId) {
        Optional<Album> album = albumService.findById(albumId);
        if(album.isPresent()) {
            Page<Track> tracks = trackService.findByAlbum_Id(albumId, PageRequest.of(0 , 20));
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Get Track have Album_id Successfully!" , tracks.toList() , tracks.getSize())
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("FAILED", "Cannot found Track have Album_id Failed!" , "ERROR!")
        );
    }

    @Override
    @GetMapping
    public ResponseEntity<ResponseObject> getTop5Track() {
        Page<Album> albums = albumService.findAll(PageRequest.of(0 , 5));
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Get Track have Album_id Successfully!" , albums.toList(), albums.getSize())
        );
    }
}
