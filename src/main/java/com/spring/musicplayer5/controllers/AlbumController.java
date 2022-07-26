package com.spring.musicplayer5.controllers;

import com.spring.musicplayer5.controllers.impl.AlbumControllerImpl;
import com.spring.musicplayer5.dto.AlbumOfTrackDto;
import com.spring.musicplayer5.dto.ResponseObject;
import com.spring.musicplayer5.dto.mapper.TrackDtoMap;
import com.spring.musicplayer5.entity.Album;
import com.spring.musicplayer5.entity.Track;
import com.spring.musicplayer5.services.AlbumService;
import com.spring.musicplayer5.services.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/album")
public class AlbumController implements AlbumControllerImpl {
    @Autowired
    private TrackService trackService;
    @Autowired
    private AlbumService albumService;

    @Override
    @GetMapping("/get_track")
    public ResponseEntity<ResponseObject> getTracksBelongIdAlbum(@RequestParam long albumId) {
        List<Track> tracks = trackService.findByAlbum_Id(albumId);
        Optional<Album> album = albumService.findById(albumId);
        if(!tracks.isEmpty() && album.isPresent()) {
            AlbumOfTrackDto albumOfTrackDto = AlbumOfTrackDto.builder()
                    .album(album.get())
                    .tracks(tracks)
                    .build();
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Get Track have Album_id Successfully!" , tracks , tracks.size())
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("FAILED", "Cannot found Track have Album_id Failed!" , "ERROR!")
        );
    }

    @Override
    @GetMapping
    public ResponseEntity<ResponseObject> getTop5() {
        Page<Album> albums = albumService.findAll(PageRequest.of(0 , 5 ));
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Get Track have Album_id Successfully!" , albums.toList(), albums.getSize())
        );
    }
}
