package com.kpason.DemoApp.user.model.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

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
    @CreationTimestamp
    private Timestamp tsCreated;
    @UpdateTimestamp
    private Timestamp tsModified;
}
