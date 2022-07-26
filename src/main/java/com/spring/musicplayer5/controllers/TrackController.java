package com.spring.musicplayer5.controllers;

import com.spring.musicplayer5.controllers.impl.TrackControllerImpl;
import com.spring.musicplayer5.dto.ResponseObject;
import com.spring.musicplayer5.dto.TrackDto;
import com.spring.musicplayer5.entity.Track;
import com.spring.musicplayer5.services.TrackService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
@RequestMapping("/api/track")
public class TrackController implements TrackControllerImpl {

    @Autowired
    private TrackService trackService;

    @Override
    @GetMapping
    public ResponseEntity<ResponseObject> findAll() {
        List<Track> tracks = trackService.findByTop(PageRequest.of(0 , 100));
        List<TrackDto> trackDtos = new ArrayList<>();
        for (Track track : tracks) {
            TrackDto trackDto = new TrackDto();
            BeanUtils.copyProperties(track , trackDto);
            trackDtos.add(trackDto);
        }
        if(!trackDtos.isEmpty()) {
            return ResponseEntity.ok(
                    new ResponseObject("OK", "Get Data Track Successfully!" ,trackDtos)
            );
        }
        return ResponseEntity.ok(
                new ResponseObject("FAILED", "Get Data Track Failed!" , "ERROR!")
        );
    }
    //Repair
    @Override
    @GetMapping("/get_id")
    public ResponseEntity<ResponseObject> getById(@RequestParam long id) {
        Optional<Track> track = trackService.findById(id);
        if(track.isPresent()) {
            TrackDto trackDto = new TrackDto();
            BeanUtils.copyProperties(track.get(), trackDto);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Get Data Track Successfully!" , trackDto)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("FAILED", "Cannot found Track Failed!" , "ERROR!")
        );
    }

    @Override
    @GetMapping("/search")
    public ResponseEntity<ResponseObject> getByTitle(@RequestParam String title) {
        List<Track> tracks = trackService.findByTitle(title);
        if(!tracks.isEmpty()) {
            List<TrackDto> trackDtos = new ArrayList<>();
            for(Track track : tracks) {
                TrackDto trackDto = new TrackDto();
                BeanUtils.copyProperties(track , trackDto);
                trackDtos.add(trackDto);
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Search Track Successfully!" , trackDtos)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("FAILED", "Cannot found Track Failed!" , null)
        );
    }

    //Code Pagination API : list of Tracks
    @Override
    @GetMapping("/paging")
    public ResponseEntity<ResponseObject> getTracksPage(@RequestParam Integer page, @RequestParam Integer size) {
        Page<Track> tracks = trackService.findAll(PageRequest.of(page , size));
        List<TrackDto> trackDtos = new ArrayList<>();
        tracks.forEach(t -> {
            TrackDto trackDto = new TrackDto();
            BeanUtils.copyProperties(t , trackDto);
            trackDtos.add(trackDto);
        });
        if(!trackDtos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Get page list of Tracks Successfully!" , trackDtos)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("FAILED", "Cannot paging for Track is Failed!" , "ERROR")
        );
    }

    @Override
    @GetMapping("/get_top")
    public ResponseEntity<ResponseObject> getTopTrack(@RequestParam Integer size) {
        List<Track> tracks = trackService.findByTop(PageRequest.of(0 , size));
        List<TrackDto> trackDtos = new ArrayList<>();
        tracks.forEach(t -> {
            TrackDto trackDto = new TrackDto();
            BeanUtils.copyProperties(t , trackDto);
            trackDtos.add(trackDto);
        });
        if(!trackDtos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK", "Get Top "+size+" list of Tracks Successfully!" , trackDtos)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("FAILED", "Cannot get Top of Track is Failed!" , "ERROR!")
        );
    }
}
