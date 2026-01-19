package com.ga.project2.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Data
@AllArgsConstructor
public class CreateServiceRequest {
    private String serviceName;
    private String serviceDescription;
    private double servicePrice;
    private MultipartFile serviceImage;
}
