package com.alameendev.ActingDriver.utility;

import org.springframework.http.MediaType;

public class ContentTypeRetriever {

    public MediaType getContentType(String fileName) {
        MediaType contentType = MediaType.APPLICATION_OCTET_STREAM;
        if(fileName.endsWith(".jpg") || fileName.endsWith(".jpeg")){
            contentType = MediaType.IMAGE_JPEG;
        }else if(fileName.endsWith(".png")){
            contentType = MediaType.IMAGE_PNG;
        }else if(fileName.endsWith(".gif")){
            contentType = MediaType.IMAGE_GIF;
        }
        return contentType;
    }
}
