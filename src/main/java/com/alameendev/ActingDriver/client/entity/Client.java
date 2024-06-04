package com.alameendev.ActingDriver.client.entity;

import com.alameendev.ActingDriver.car.entity.Car;
import com.alameendev.ActingDriver.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;

    @OneToOne
    @JoinColumn(name = "userId",nullable = false)
    private User user;

    @Column(name = "name")
    private String name;

    @Column(name = "profilePictureName")
    private String profilePictureName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "client",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Car> cars;

    public void addCar(Car car){
        cars.add(car);
    }

}
