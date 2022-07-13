package com.spring.musicplayer5.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface FilesStorageService {
    public void init();
    Path createFile(String fileName);
    void save(Path root, MultipartFile file);
    Resource load(Path root, String filename);
    void deleteAll(Path root);
    Stream<Path> loadAll(Path root);
}
