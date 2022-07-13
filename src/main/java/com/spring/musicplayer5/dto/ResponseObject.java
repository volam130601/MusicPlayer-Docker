package com.spring.musicplayer5.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseObject {
    private String status;
    private String message;
    private Object datas;

    public ResponseObject(String status , String message) {
        this.status = status;
        this.message = message;
    }
}