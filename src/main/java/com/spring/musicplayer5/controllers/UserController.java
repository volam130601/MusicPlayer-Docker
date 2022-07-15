package com.spring.musicplayer5.controllers;

import com.spring.musicplayer5.controllers.impl.UserControllerImpl;
import com.spring.musicplayer5.dto.ResponseObject;
import com.spring.musicplayer5.dto.UserDto;
import com.spring.musicplayer5.dto.files.FileInfo;
import com.spring.musicplayer5.dto.login.LoginRequestDto;
import com.spring.musicplayer5.entity.Role;
import com.spring.musicplayer5.entity.User;
import com.spring.musicplayer5.services.FilesStorageService;
import com.spring.musicplayer5.services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.validation.Valid;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserController extends FilesController implements UserControllerImpl {

    @Autowired
    private UserService userService;

    //Add validation for USer at login
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

    @PutMapping("/update_info")
    public ResponseEntity<ResponseObject> updateInfoUser(@RequestBody UserDto userDto) {
        User updateUser = userService.findByUsername(userDto.getUsername())
                .map(user -> {
                    user.setFullName(userDto.getFullName());
                    user.setAge(userDto.getAge());
                    user.setCountry(userDto.getCountry());
                    user.setImage(userDto.getImage());
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

    //Error Delete
    @DeleteMapping
    public ResponseEntity<ResponseObject> deleteUser(@RequestBody LoginRequestDto loginRequestDto) {
        Optional<User> existsUser = userService.findByUsername(loginRequestDto.getUsername());
        if(existsUser.isPresent()) {
            userService.deleteByUsername(loginRequestDto.getUsername());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("OK" , "Delete User Successfully!" , "SUCCESS")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("FAILED" , "Delete User Failed!" , "FAILED")
        );
    }

    @GetMapping
    public ResponseEntity<ResponseObject> getAllUser() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK" , "Get All User!" , userService.findAll())
        );
    }


}
