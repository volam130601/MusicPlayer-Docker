package com.spring.musicplayer5.services;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;

public interface StorageService {
    boolean findFile(String storedFilename);

    void init();

    void delete(String storedFilename) throws IOException;

    Path load(String filename);

    Resource loadAsResource(String filename);

    void store(MultipartFile file, String storedFilename);

    String getStoredFilename(MultipartFile file, String id);

}
