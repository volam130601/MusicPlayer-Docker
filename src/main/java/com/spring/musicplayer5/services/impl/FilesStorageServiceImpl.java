package com.spring.musicplayer5.services.impl;

import com.spring.musicplayer5.services.FilesStorageService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {
    @Override
    public void init() {
        try {
            File file = new File("src/main/resources/images");
            if(!Files.isDirectory(file.toPath())) {
                Files.createDirectory(file.toPath());
            }
            } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for images!");
        }
    }
    @Override
    public Path createFile(String fileName) {
        try {
            File file = new File("src/main/resources/images/" + fileName);
            if(!Files.isDirectory(file.toPath())) {
                Files.createDirectory(file.toPath());
                return file.toPath();
            }
            return null;
        } catch (IOException e) {
                throw new RuntimeException("Could not initialize folder for "+fileName+"!");
        }
    }

    @Override
    public void save(Path root, MultipartFile file) {
        try {
            Files.copy(file.getInputStream(), root.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }
    @Override
    public Resource load(Path root, String filename) {
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
    @Override
    public void deleteAll(Path root) {
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    @Override
    public Stream<Path> loadAll(Path root) {
        try {
            return Files.walk(root, 1).filter(path -> !path.equals(root)).map(root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }
}