package com.spring.musicplayer5.controllers;

import com.spring.musicplayer5.dto.ResponseObject;
import com.spring.musicplayer5.dto.UserDto;
import com.spring.musicplayer5.dto.login.LoginRequestDto;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserController {

    //Add validation for USer at login
    @GetMapping("/login")
    ResponseEntity<ResponseObject> login(@RequestBody LoginRequestDto loginRequestDto);

    @PostMapping("/register")
    ResponseEntity<Object> register(@RequestBody LoginRequestDto loginRequestDTO);

    @PutMapping("/update_info")
    ResponseEntity<ResponseObject> updateInfoUser(@RequestBody UserDto userDto);

    @PutMapping("/change_password")
    ResponseEntity<ResponseObject> change_password(@RequestBody UserDto userDto);

    @PutMapping("/locked_account")
    ResponseEntity<ResponseObject> locked_account(@RequestBody UserDto userDto);

//    @DeleteMapping("/delete_all")
//    ResponseEntity<ResponseObject> deleteAllNotConstraint();

    @GetMapping
    ResponseEntity<ResponseObject> getAllUser();

    @RequestMapping(value = "/image", produces = {MediaType.IMAGE_PNG_VALUE, "application/json"})
    ResponseEntity<?> uploadImage(@RequestParam("imageFile") MultipartFile file,
                                  @RequestParam("username") String username) throws IOException;

    @GetMapping("/images/{filename:.+}")
    @ResponseBody
    ResponseEntity<Resource> serveFile(@PathVariable String filename);

    @GetMapping("/files/get_image")
    ResponseEntity<Resource> getImageByUser(@RequestParam String username);
    }
