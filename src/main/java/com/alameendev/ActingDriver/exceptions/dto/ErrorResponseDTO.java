package com.alameendev.ActingDriver.exceptions.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ErrorResponseDTO {
    private String errorMessage;
}
