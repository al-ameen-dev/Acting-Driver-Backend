package com.alameendev.ActingDriver.filestorage.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileResponse {
    private String fileName;
    private byte[] fileData;
}
