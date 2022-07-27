package com.spring.musicplayer5.services.impl;

import com.spring.musicplayer5.entity.Role;
import com.spring.musicplayer5.repositories.RoleRepository;
import com.spring.musicplayer5.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository repository;

    @Override
    public List<Role> findAll() {
        return repository.findAll();
    }

    @Override
    public Role save(Role entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<Role> findById(long id) {
        return findById(id);
    }

    @Override
    public void deleteById(long id) {

    }
}
