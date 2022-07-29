package com.spring.musicplayer5.dto;

import com.spring.musicplayer5.entity.Artist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @AllArgsConstructor
@Builder
@NoArgsConstructor
public class ResponseObject {
    private String status;
    private String message;
    private Object datas;
    private int total;
    private int page;
    private int size;

    public ResponseObject(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseObject(String status , String message, Object datas) {
        this.status = status;
        this.message = message;
        this.datas = datas;
    }

    public ResponseObject(String status, String message, Object datas, int total) {
        this.status = status;
        this.message = message;
        this.datas = datas;
        this.total = total;
    }
}