package com.example.Project2.model.request;

import lombok.Getter;

@Getter
public class ChangePasswordRequest {
    private String newPass;
    private String oldPass;
}
