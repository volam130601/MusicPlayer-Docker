package com.spring.musicplayer5.services.impl;

import com.spring.musicplayer5.entity.Genre;
import com.spring.musicplayer5.repositories.GenreRepository;
import com.spring.musicplayer5.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {
    @Autowired
    private GenreRepository repository;

    @Override
    public List<Genre> getAll() {
        return repository.findAll();
    }

    @Override
    public Genre save(Genre entity) {
        return repository.save(entity);
    }

    @Override
    public Optional<Genre> findById(long id) {
        return repository.findById(id);
    }

    @Override
    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
