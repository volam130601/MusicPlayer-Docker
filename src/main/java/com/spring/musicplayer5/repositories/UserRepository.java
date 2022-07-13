package com.spring.musicplayer5.repositories;

import com.spring.musicplayer5.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsernameAndPassword(String username , String password);
    Optional<User> findByUsername(String username);
    void deleteByUsername(String username);
}
