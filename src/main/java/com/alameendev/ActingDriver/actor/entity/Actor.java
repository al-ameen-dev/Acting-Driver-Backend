package com.alameendev.ActingDriver.actor.entity;


import com.alameendev.ActingDriver.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Actor")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long actorId;

    @OneToOne
    @JoinColumn(name = "userId",nullable = false)
    private User user;

    @Column(name = "name")
    private String name;

    @Column(name = "contact")
    private String phone;

    @Column(name="biography",length = 750)
    private String biography;

    @Column(name = "profilePictureUrl")
    private String profilePictureUrl;

    @Column(name = "drivingExperience")
    private Integer driverExperience;

    @Column(name = "actingCredentials")
    private String credentials;

    @Column(name = "availabilityStatus")
    @Enumerated(EnumType.STRING)
    private AvailabilityStatus availabilityStatus;

    @Column(name = "rating")
    private Double rating;
}
