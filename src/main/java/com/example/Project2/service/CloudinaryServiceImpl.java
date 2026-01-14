package com.example.Project2.service;

import com.cloudinary.Cloudinary;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class CloudinaryServiceImpl {

    private final Cloudinary cloudinary;

    public CloudinaryServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String uploadFile(MultipartFile file, String folderName) {
        try {
            Map<String, Object> options = new HashMap<>();
            options.put("folder", folderName);

            Map uploadResult =
                    cloudinary.uploader().upload(file.getBytes(), options);

            String publicId = (String) uploadResult.get("public_id");
            return cloudinary.url().secure(true).generate(publicId);

        } catch (IOException e) {
            throw new RuntimeException("Image upload failed", e);
        }
    }
}