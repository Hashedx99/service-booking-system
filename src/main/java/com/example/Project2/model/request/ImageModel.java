package com.example.Project2.model.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageModel {
    private String name;
    private MultipartFile file;
}
