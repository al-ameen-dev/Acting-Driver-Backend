package com.alameendev.ActingDriver.car.service;

import com.alameendev.ActingDriver.car.dto.CarDTO;
import com.alameendev.ActingDriver.car.entity.Car;

import java.util.List;

public interface CarService {
    public List<CarDTO> retrieveAllCars();

    public CarDTO saveCar(Car car);

    public List<CarDTO> retrieveCarsForTheClientById(Long id);

    public List<CarDTO> retrieveCarsForTheClient();

    public CarDTO retrieveCarById(Long id);

    public CarDTO createCar(CarDTO body);

    public CarDTO updateCarById(Long id,CarDTO body);

    public void deleteCarById(Long id);
}
