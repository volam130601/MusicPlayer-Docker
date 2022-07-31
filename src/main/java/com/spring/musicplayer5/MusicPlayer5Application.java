package com.spring.musicplayer5;

import com.spring.musicplayer5.services.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MusicPlayer5Application {

    public static void main(String[] args) {
        SpringApplication.run(MusicPlayer5Application.class, args);
    }

    //co cai nay de khoi tao storage
    //@Bean : để giúp cho IoC (Inversion of Container) có thể nạp và quản lý các thể hiện của lớp
    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args -> {
            storageService.init();
        });
    }
}
