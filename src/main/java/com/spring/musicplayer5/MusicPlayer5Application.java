package com.spring.musicplayer5;

import com.spring.musicplayer5.services.FilesStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import java.nio.file.Path;

@SpringBootApplication
public class MusicPlayer5Application implements CommandLineRunner {
    @Resource
    FilesStorageService filesStorageService;

    public static void main(String[] args) {
        SpringApplication.run(MusicPlayer5Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        filesStorageService.init();
        filesStorageService.createFile("user");
    }
}
