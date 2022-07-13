package com.spring.musicplayer5.validation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class FieldErrorResponse {
    private List<CustomFieldError> fieldErrors;
}
