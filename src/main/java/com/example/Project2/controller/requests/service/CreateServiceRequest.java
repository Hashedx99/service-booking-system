package com.example.Project2.controller.requests.service;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateServiceRequest {
    private String serviceName;
    private String serviceDescription;
    private double servicePrice;
}
