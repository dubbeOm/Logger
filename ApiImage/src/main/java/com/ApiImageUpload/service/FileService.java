package com.ApiImageUpload.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
public interface FileService {
    public String  uploadImage(String path, MultipartFile file) throws IOException;
}
