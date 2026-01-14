//package com.example.Project2.controller;
//
//import com.example.Project2.model.request.ImageModel;
//import com.example.Project2.repository.ImageRepository;
//import com.example.Project2.service.ImageServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Map;
//
//@RestController
//public class ImageController {
//
//    @Autowired
//    private ImageRepository imageRepository;
//
//    @Autowired
//    private ImageServiceImpl imageService;
//
//    @PostMapping("/upload")
//    public ResponseEntity<Map> upload(ImageModel imageModel) {
//        try {
//            return imageService.uploadImage(imageModel,"");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//}
//
