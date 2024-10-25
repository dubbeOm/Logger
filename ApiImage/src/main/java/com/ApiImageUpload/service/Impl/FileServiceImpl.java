package com.ApiImageUpload.service.Impl;

import com.ApiImageUpload.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadImage(String path, MultipartFile file) throws IOException {
        // File name
        String name = file.getOriginalFilename();

        // Full file path
        String filePath = path + File.separator + name;

        // Create folder if not exists
        File f = new File(path);
        if (!f.exists()) {
            f.mkdirs();  // Use mkdirs to create parent directories if needed
        }

        // Check if the file already exists to prevent overwriting
        Path path1 = Paths.get(filePath);
        if (Files.exists(path1)) {
            throw new IOException("File already exists: " + name);
        }

        // Copy file
        try {
            Files.copy(file.getInputStream(), path1);
        } catch (IOException e) {
            throw new IOException("Error occurred while uploading file: " + name, e);
        }

        return name;
    }
}
