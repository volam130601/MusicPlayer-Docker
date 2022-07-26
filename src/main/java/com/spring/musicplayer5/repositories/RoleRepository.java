package com.spring.musicplayer5.repositories;

import com.spring.musicplayer5.entity.Role;
import com.spring.musicplayer5.entity.Track;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
