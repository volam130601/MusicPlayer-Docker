package com.spring.musicplayer5.controllers;

import com.spring.musicplayer5.services.StorageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
class FilesControllerTest {

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

    private static String imageDirectory = System.getProperty("user.dir") + "/images/";
    @Autowired
    private StorageService storageService;
    @Test
    public void testDirectory() throws IOException {
        String image = "617102bb-9103-4c93-82b6-fc5c689618c5.png";
        String dir = imageDirectory + image;
        Path file = Paths.get(dir);
        System.out.println(Files.deleteIfExists(file));
//        Set<String> listFile = listFilesUsingJavaIO(imageDirectory);
//        for(String s : listFile) {
//            System.out.println(s);
//            if(s.equals(image)) {
//
//                return;
//        }
    }

    public Set<String> listFilesUsingJavaIO(String dir) {
        return Stream.of(new File(dir).listFiles())
                .filter(file -> !file.isDirectory())
                .map(File::getName)
                .collect(Collectors.toSet());
    }
}