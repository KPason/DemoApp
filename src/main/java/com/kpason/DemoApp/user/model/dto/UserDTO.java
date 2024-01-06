package com.kpason.DemoApp.user.model.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String imageUrl;
    private Timestamp tsCreated;
    private Timestamp tsModified;
}
