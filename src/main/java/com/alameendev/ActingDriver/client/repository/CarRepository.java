package com.alameendev.ActingDriver.client.repository;


import com.alameendev.ActingDriver.client.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
}
