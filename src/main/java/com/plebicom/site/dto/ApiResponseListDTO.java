package com.plebicom.site.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseListDTO<T> {

    private List<T> payload;
    private String error;

    public ApiResponseListDTO(List<T> payload) {
        this.payload = payload;
    }

    public ApiResponseListDTO(String error) {
        this.error = error;
    }
}
