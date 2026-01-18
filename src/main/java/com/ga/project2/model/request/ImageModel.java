package com.ga.project2.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
public class ImageModel {
    private String name;
    private MultipartFile file;
}
