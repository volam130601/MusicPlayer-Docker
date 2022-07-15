package com.spring.musicplayer5.controllers.impl;

import com.spring.musicplayer5.dto.ResponseObject;
import com.spring.musicplayer5.dto.UserDto;
import com.spring.musicplayer5.dto.login.LoginRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface UserControllerImpl {

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

    @DeleteMapping("/delete_all")
    ResponseEntity<ResponseObject> deleteAllNotConstraint();

    @GetMapping
    ResponseEntity<ResponseObject> getAllUser();
}
