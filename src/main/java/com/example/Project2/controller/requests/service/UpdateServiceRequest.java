package com.example.Project2.controller.requests.service;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateServiceRequest {
    private String serviceName; // optional
    private String serviceDescription; // optional
    private String servicePrice; // optional
}
