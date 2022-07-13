package com.spring.musicplayer5.repositories;

import com.spring.musicplayer5.entity.Role;
import com.spring.musicplayer5.entity.Track;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
}
