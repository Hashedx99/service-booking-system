package com.ga.project2.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageModel {
    private String name;
    private MultipartFile file;

    public ImageModel(String name, MultipartFile file) {
        this.name = name;
        this.file = file;
    }
}
