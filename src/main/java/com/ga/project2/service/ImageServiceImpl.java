package com.ga.project2.service;

import com.ga.project2.model.Image;
import com.ga.project2.model.request.ImageModel;
import com.ga.project2.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
