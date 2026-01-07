package com.example.Project2.model.response;

import com.example.Project2.model.Service;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateServiceResponse {
    private Service service;
}
