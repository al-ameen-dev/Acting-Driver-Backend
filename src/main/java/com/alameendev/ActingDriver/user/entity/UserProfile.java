package com.alameendev.ActingDriver.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "UserProfile")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profileId")
    private Long id;

    @OneToOne
    @JoinColumn(name = "userid",nullable = false)
    private User user;

    @Column(name = "role")
    private String role;

    @Column(name = "phoneNo")
    private String phone;
}
