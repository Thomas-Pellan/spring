package com.plebicom.site.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseDTO<T> {

    private T payload;
    private String error;

    public ApiResponseDTO(T payload) {
        this.payload = payload;
    }

    public ApiResponseDTO(String error) {
        this.error = error;
    }
}
