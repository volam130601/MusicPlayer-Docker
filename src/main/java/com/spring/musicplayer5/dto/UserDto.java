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
    @NotNull(message = "Username is required.")
    private String username;

    @NotNull(message = "Password is required.")
    private String password;
    private String fullName;
    private String country;
    private Integer age;
    private Object role;
    private String image;
    private boolean isLocked;
    private String new_password;
}
