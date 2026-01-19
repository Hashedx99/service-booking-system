package com.ga.project2.model.request;

import org.springframework.web.multipart.MultipartFile;

public class CreatePropertyRequest {
    private String name;
    private String description;
    private Double price;
    private boolean active;
    private long UserId;
    private MultipartFile propertyImage;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public long getUserId() {
        return UserId;
    }

    public void setUserId(long userId) {
        UserId = userId;
    }

    public MultipartFile getPropertyImage() {
        return propertyImage;
    }

    public void setPropertyImage(MultipartFile propertyImage) {
        this.propertyImage = propertyImage;
    }
}
