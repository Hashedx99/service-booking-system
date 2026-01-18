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

    /**
     * Processes and uploads an image to a cloud storage provider (Cloudinary) and
     * persists the metadata to the database.
     *
     * @param imageModel The DTO containing the image name and the MultipartFile data.
     * @param folder     The target folder/category name in the cloud storage where the image will be stored.
     * @return The public URL of the uploaded image if successful;
     * returns {@code null} if the input is invalid, the upload fails, or an exception occurs.
     */
    public String uploadImage(ImageModel imageModel, String folder) {
        try {
            // if the name or file is missing return null
            if (imageModel.getName().isEmpty() || imageModel.getFile().isEmpty()) {
                return null;
            }

            // create image instance
            Image image = new Image();

            // upload the image
            var imageUrl = cloudinaryService.uploadFile(imageModel.getFile(), folder);

            // if no URL (failed to upload the image) return null
            if (imageUrl == null) {
                return null;
            }

            // set the image fields
            image.setName(imageModel.getName()); // set the image name
            image.setUrl(imageUrl); // set the image url

            // save the new entity
            imageRepository.save(image);

            // return the uploaded image url
            return image.getUrl();
        } catch (Exception e) {
            // log the error message
            System.err.println(e.getMessage());

            // if failed to upload the image return null
            return null;
        }


    }
}
