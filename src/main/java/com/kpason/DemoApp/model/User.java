package com.kpason.DemoApp.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "user_")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String firstName;
    @Column(nullable = false, length = 70)
    private String lastName;
    @Column(nullable = false, length = 80)
    private String email;
    private String phone;
    private String imageUrl;
}
