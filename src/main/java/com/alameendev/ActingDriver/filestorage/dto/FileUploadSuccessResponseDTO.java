package com.alameendev.ActingDriver.filestorage.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadSuccessResponseDTO {

    private String filePath;
    private boolean isUploaded;

}
