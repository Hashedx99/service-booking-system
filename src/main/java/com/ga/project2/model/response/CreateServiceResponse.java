package com.ga.project2.model.response;

import com.ga.project2.model.Service;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateServiceResponse {
    private Service service;
}
