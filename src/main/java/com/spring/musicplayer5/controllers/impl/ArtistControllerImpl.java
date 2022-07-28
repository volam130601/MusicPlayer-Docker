package com.spring.musicplayer5.controllers.impl;

import com.spring.musicplayer5.dto.ResponseObject;
import com.spring.musicplayer5.entity.Artist;
import com.spring.musicplayer5.services.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/Artist")
public class ArtistControllerImpl {
    @Autowired
    private ArtistService artistService;

    @GetMapping
    public ResponseEntity<ResponseObject> getTop5Artist() {
        Page<Artist> artists = artistService.findAll(PageRequest.of(0 , 5));
        return ResponseEntity.status(HttpStatus.OK).body(
            new ResponseObject("OK" , "Get 5 Artist Hot", artists.toList() , artists.getSize())
        );
    }
}
