package com.spring.musicplayer5.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Array;

@Data @AllArgsConstructor
@NoArgsConstructor
public class ResponseObject {
    private String status;
    private String message;
    private Object datas;
    private int total;

    public ResponseObject(String status , String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseObject(String status , String message, Object datas) {
        this.status = status;
        this.message = message;
        this.datas = datas;
    }
}