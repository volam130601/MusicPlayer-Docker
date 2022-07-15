package com.spring.musicplayer5.controllers;

import com.spring.musicplayer5.controllers.impl.UserControllerImpl;
import com.spring.musicplayer5.dto.ResponseObject;
import com.spring.musicplayer5.dto.UserDto;
import com.spring.musicplayer5.dto.login.LoginRequestDto;
import com.spring.musicplayer5.entity.Comment;
import com.spring.musicplayer5.entity.Playlist;
import com.spring.musicplayer5.entity.Role;
import com.spring.musicplayer5.entity.User;
import com.spring.musicplayer5.services.CommentService;
import com.spring.musicplayer5.services.PlaylistService;
import com.spring.musicplayer5.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController extends FilesController implements UserControllerImpl {

    @Autowired
    private UserService userService;
    @Autowired
    private PlaylistService playlistService;
    @Autowired
    private CommentService commentService;

    //Add validation for USer at login
    @Override
    @GetMapping("/login")
    public ResponseEntity<ResponseObject> login(@RequestBody LoginRequestDto loginRequestDto) {
        Optional<User> exists = userService.findByUsernameAndPassword(loginRequestDto.getUsername() , loginRequestDto.getPassword());
        if(exists.isPresent()) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(exists.get(), userDto);
            return ResponseEntity.ok(
                    new ResponseObject("OK", "Login success!" , userDto)

            );
        }
        return ResponseEntity.ok(
                new ResponseObject("FAILED", "Username or Password isn't exists or wrong!" , "failed")
        );
    }

    @Override
    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody LoginRequestDto loginRequestDTO){
        User newUser = new User();
        BeanUtils.copyProperties(loginRequestDTO , newUser);
        Optional<User> foundUser = userService.findByUsername(newUser.getUsername().trim());
        if(!foundUser.isPresent()) {
            Role role = Role.builder().id(2).build();
            newUser.setRole(role);
            userService.save(newUser);
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(newUser , userDto);
            return ResponseEntity.ok(
                    new ResponseObject("OK", "Register Account is Success!" , userDto)
            );
        }
        return ResponseEntity.ok(
                new ResponseObject("FAILED", "Username is exists!" , "ERROR")
        );
    }

    @Override
    @PutMapping("/update_info")
    public ResponseEntity<ResponseObject> updateInfoUser(@RequestBody UserDto userDto) {
        User updateUser = userService.findByUsername(userDto.getUsername())
                .map(user -> {
                    user.setFullName(userDto.getFullName());
                    user.setBirthday(userDto.getBirthday());
                    user.setCountry(userDto.getCountry());
                    user.setPhone(userDto.getPhone());
                    user.setEmail(userDto.getEmail());
                    return userService.save(user);
                }).orElseGet(()->{
                    User user = new User();
                    BeanUtils.copyProperties(userDto , user);
                    return userService.save(user);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK" , "Update info user is Successfully!" , updateUser)
        );
    }

    //Change follow Front End
    @Override
    @PutMapping("/change_password")
    public ResponseEntity<ResponseObject> change_password(@RequestBody UserDto userDto) {
        Optional<User> exists = userService.findByUsernameAndPassword(userDto.getUsername() , userDto.getPassword());
        if(exists.isPresent()) {
            User user = new User();
            BeanUtils.copyProperties(exists.get(), user);
            user.setPassword(userDto.getNew_password());
            userService.save(user);
            return ResponseEntity.ok(
                    new ResponseObject("OK" , "Change Password Successfully!" , user)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("FAILED" , "Change password is fail!" , "FAILED")
        );
    }
    @Override
    @PutMapping("/locked_account")
    public ResponseEntity<ResponseObject> locked_account(@RequestBody UserDto userDto) {
        Optional<User> exists = userService.findByUsername(userDto.getUsername());
        if(exists.isPresent()) {
            User user = exists.get();
            user.setLocked(true);
            userService.save(user);
            return ResponseEntity.ok(
                    new ResponseObject("OK" , "Locked User is Successfully!" , user)
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("FAILED" , "Locked User is failed!" , "FAILED")
        );
    }

    @Override
    @DeleteMapping("/del_all_not_constraint")
    public ResponseEntity<ResponseObject> deleteAllNotConstraint() {
        List<User> userList = userService.findAll();
        List<String> removeList = new ArrayList<>();
        userList.forEach(user -> {
                    List<Playlist> exsits = playlistService.findPlaylistByUsername(user.getUsername());
                    Optional<Comment> exsits_2 = commentService.findByUserUsername(user.getUsername());
                    if(exsits.isEmpty() && !exsits_2.isPresent()) {
                        userService.deleteByUsername(user.getUsername());
                        removeList.add(user.getUsername());
                    }
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK" , removeList.isEmpty() ? "No accounts have been deleted!" : "Delete All User Successfully!" , removeList)
        );
    }

    @Override
    @GetMapping
    public ResponseEntity<ResponseObject> getAllUser() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK" , "Get All User!" , userService.findAll())
        );
    }



}
