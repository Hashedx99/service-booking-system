package com.ga.project2.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateServiceRequest {
    private String serviceName;
    private String serviceDescription;
    private double servicePrice;
}
