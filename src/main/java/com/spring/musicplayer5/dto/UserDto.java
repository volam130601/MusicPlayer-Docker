package com.spring.musicplayer5.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data @AllArgsConstructor @NoArgsConstructor
@Builder
public class UserDto {
    private String username;
    private String password;
    private String fullName;
    private String birthday;
    private String country;
    private String image;
    private Integer phone;
    private String email;
    private boolean isLocked;
    private String new_password;
}
