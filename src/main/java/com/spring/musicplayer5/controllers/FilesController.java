package com.spring.musicplayer5.controllers;

import com.spring.musicplayer5.dto.ResponseObject;
import com.spring.musicplayer5.dto.files.FileInfo;
import com.spring.musicplayer5.services.FilesStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
//@RestController
//@RequestMapping("/files")
//FilesController is parent class
public class FilesController {
    @Autowired
    FilesStorageService storageService;

//    private /*final*/ Path root/* = Path.of("src/main/resources/images/user")*/;
    private Path root;

    public void setRoot(String fileName) {
        this.root = Path.of("src/main/resources/images/"+fileName);;
    }

    @PostMapping("/files/upload")
    public ResponseEntity<ResponseObject> uploadFile(@RequestParam("file") MultipartFile file,HttpServletRequest request) {
        String message = "";
        try {
            splitFileName(request);
            storageService.save(root, file);
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject("OK",message));
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseObject("ERROR",message));
        }
    }
    @GetMapping("/files")
    public ResponseEntity<List<FileInfo>> getListFiles(HttpServletRequest request) {
        splitFileName(request);
        List<FileInfo> fileInfos = storageService.loadAll(root).map(path -> {
            String filename = path.getFileName().toString();
            String url = request.getRequestURL().toString() + "/"+filename;
            return new FileInfo(filename, url);
        }).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename, HttpServletRequest request) {
        splitFileName(request);
        Resource file = storageService.load(root, filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .contentType(MediaType.IMAGE_JPEG).body(file);
    }

    public void splitFileName(HttpServletRequest request) {
        String temp = request.getRequestURI();
        String arr[] = temp.split("/");
        setRoot(arr[2]);
    }

    @DeleteMapping("/files/del-image")
    public void deleteImage(@RequestParam String fileName, HttpServletRequest request) {
        splitFileName(request);
        this.root = root.resolve(fileName);
        storageService.deleteAll(root);
        // Notice for Client with JSON
    }
    //Delete All Images in File
}
