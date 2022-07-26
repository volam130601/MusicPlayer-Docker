package com.spring.musicplayer5.services;

import com.spring.musicplayer5.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> findAll();
    Role save(Role entity);
    Optional<Role> findById(long id);
    void deleteById(long id);
}
