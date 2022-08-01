package com.spring.musicplayer5.controllers.impl;

import com.spring.musicplayer5.controllers.UserController;
import com.spring.musicplayer5.dto.ResponseObject;
import com.spring.musicplayer5.dto.UserDto;
import com.spring.musicplayer5.dto.login.LoginRequestDto;
import com.spring.musicplayer5.entity.Role;
import com.spring.musicplayer5.entity.User;
import com.spring.musicplayer5.services.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/user")
public class UserControllerImpl implements UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
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
            return ResponseEntity.ok(
                    new ResponseObject("OK", "Login success!" , exists.get())
            );
        }
        return ResponseEntity.ok(
                new ResponseObject("FAILED", "Username or Password isn't exists or wrong!" , null)
        );
    }

    @Override
    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody LoginRequestDto loginRequestDTO){
        User newUser = new User();
        BeanUtils.copyProperties(loginRequestDTO , newUser);
        Optional<User> foundUser = userService.findByUsername(newUser.getUsername().trim());
        if(!foundUser.isPresent()) {
            List<Role> roles = roleService.findAll();
            if(roles.isEmpty()) {
                roleService.save(Role.builder().name("USER").code("USER").build());
            }
            Role role = Role.builder().id(1).build();
            newUser.setRole(role);
            return ResponseEntity.ok(
                    new ResponseObject("OK", "Register Account is Success!" , userService.save(newUser))
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
        Optional<User> exists = userService.findByUsername(userDto.getUsername());
        if(exists.isPresent()) {
            User user = exists.get();
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

    //Lack of unlock for account
    //////

    //Function haven't built yet.
//    @Override
//    @DeleteMapping("/del_all_not_constraint")
//    public ResponseEntity<ResponseObject> deleteAllNotConstraint() {
//        List<User> userList = userService.findAll();
//        List<String> removeList = new ArrayList<>();
//        return ResponseEntity.status(HttpStatus.OK).body(
//                new ResponseObject("OK" , removeList.isEmpty() ? "No accounts have been deleted!" : "Delete All User Successfully!" , removeList)
//        );
//    }

    @Override
    @GetMapping
    public ResponseEntity<ResponseObject> getAllUser() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK" , "Get All User!" , userService.findAll())
        );
    }

    //File Uploads
    @Autowired
    private StorageService storageService;

    @Override
    @RequestMapping(value = "/image", produces = {MediaType.IMAGE_PNG_VALUE, "application/json"})
    public ResponseEntity<?> uploadImage(@RequestParam("imageFile") MultipartFile file,
                                         @RequestParam("username") String username) throws IOException {
        Optional<User> exsistUser = userService.findByUsername(username);
        if(exsistUser.isPresent()) {
            UUID uuid = UUID.randomUUID();
            String name = uuid.toString();
            User user = exsistUser.get();
            storageService.delete(user.getImage());
            user.setImage(storageService.getStoredFilename(file, name));
            storageService.store(file, user.getImage());
            userService.save(user);
            return new ResponseEntity<>("Upload success", HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Cannot found User!", HttpStatus.BAD_REQUEST);

    }

    @Override
    @GetMapping("/images/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource("image_default.png");;
        if(storageService.findFile(filename)) {
            file = storageService.loadAsResource(filename);
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .contentType(MediaType.IMAGE_JPEG).body(file);
    }

    @Override
    @GetMapping("/files/get_image")
    public ResponseEntity<Resource> getImageByUser(@RequestParam String username) {
        Optional<User> exist = userService.findByUsername(username);
        Resource file = storageService.loadAsResource("image_default.jpg");;
        if(exist.isPresent() && storageService.findFile(exist.get().getImage())) {
            file = storageService.loadAsResource(exist.get().getImage());
        }
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .contentType(MediaType.IMAGE_JPEG).body(file);
    }

}
