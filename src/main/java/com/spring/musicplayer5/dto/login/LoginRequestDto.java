package com.spring.musicplayer5.dto.login;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data  @AllArgsConstructor @NoArgsConstructor
public class LoginRequestDto {
    @NotNull(message = "Username is required.")
    @Size(min = 3 , message = "Username should be at least 3 characters.")
    private String username;

    @NotNull(message = "Password is required.")
    @Size(min = 6 , message = "Password should be at least 6 characters.")
    private String password;
}
