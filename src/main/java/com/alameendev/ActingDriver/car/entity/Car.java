package com.alameendev.ActingDriver.car.entity;

import com.alameendev.ActingDriver.client.entity.Client;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;

    @Column(name = "carNumber")
    private String carNumber;

    @Column(name = "model")
    private String model;

    @Column(name = "carImageName")
    private String carImageName;

    @ManyToOne
    @JoinColumn(name = "clientId",nullable = false)
    private Client client;
}
