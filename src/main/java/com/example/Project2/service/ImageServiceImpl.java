package com.example.Project2.service;

import com.example.Project2.model.Image;
import com.example.Project2.model.request.ImageModel;
import com.example.Project2.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ImageServiceImpl {

    @Autowired
    private CloudinaryServiceImpl cloudinaryService;
    @Autowired
    private ImageRepository imageRepository;


    public String uploadImage(ImageModel imageModel , String folder) {
        try {
            if (imageModel.getName().isEmpty()) {
                return null;
            }
            if (imageModel.getFile().isEmpty()) {
                return null;
            }
            Image image = new Image();
            image.setName(imageModel.getName());
            image.setUrl(cloudinaryService.uploadFile(imageModel.getFile(), folder));
            if(image.getUrl() == null) {
                return null;
            }
            imageRepository.save(image);
            return image.getUrl();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }
}
