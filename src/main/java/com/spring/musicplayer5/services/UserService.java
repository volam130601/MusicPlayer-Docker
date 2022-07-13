package com.spring.musicplayer5.services;

import com.spring.musicplayer5.entity.Track;
import com.spring.musicplayer5.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    User save(User entity);
    Optional<User> findByUsernameAndPassword(String username, String password);
    Optional<User> findByUsername(String username);
    void deleteByUsername(String username);

}
