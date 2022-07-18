package com.spring.musicplayer5.controllers;

import com.spring.musicplayer5.services.FilesStorageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

@SpringBootTest
class FilesControllerTest {
    @Autowired
    FilesStorageService filesStorageService;

    @Test
    public void testCreateDirectory() throws IOException, URISyntaxException {
//        File file = new File("src/main/resources/images");
//        System.out.println(file.getAbsolutePath());
//        Files.createDirectory(file.toPath());
//        File file2 = new File("src/main/resources/images/"+"login");
//        System.out.println(file2.getAbsolutePath());
//        Files.createDirectory(file2.toPath());
        String fileName = "user";
        if(!Files.isDirectory(Path.of("src/main/resources/images/"+fileName))) {
            File file2 = new File("src/main/resources/images/"+fileName);
            Files.createDirectory(file2.toPath());
        }
    }

//    @Test
//    public void deleteAllFile() {
//        //Delete All User
//        Path root = Path.of("src/main/resources/images/user/luffy-gold.jpg");
//        filesStorageService.deleteAll(root);
//    }

    @Test
    public void splitString() {
        String temp = "/api/user/files";
        String arr[] = temp.split("/");
        for (String s : arr) {
            System.out.println(s);
        }
    }
    @Test
    public void filterString() {
        String temp = "http://localhost:8080/api/user/files/upload";
        System.out.println(temp.substring(0,temp.length() - 6));
        System.out.println(temp.replace("localhost",  "172.16.75.26"));
    }
    @Test
    public void getIpAddress() {
        try {
            System.out.println("IP Address : " + Inet4Address.getLocalHost().getHostAddress());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}