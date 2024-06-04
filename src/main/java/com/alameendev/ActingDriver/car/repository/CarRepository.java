package com.alameendev.ActingDriver.car.repository;


import com.alameendev.ActingDriver.car.entity.Car;
import com.alameendev.ActingDriver.client.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car,Long> {
    List<Car> findByClientClientId(Long id);
    List<Car> findByClient(Client client);
}
