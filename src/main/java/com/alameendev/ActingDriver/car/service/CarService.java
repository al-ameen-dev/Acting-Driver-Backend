package com.alameendev.ActingDriver.car.service;

import com.alameendev.ActingDriver.car.dto.CarDTO;
import com.alameendev.ActingDriver.car.entity.Car;
import com.alameendev.ActingDriver.filestorage.dto.FileResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CarService {
    List<CarDTO> retrieveAllCars();

    CarDTO saveCar(Car car);

    List<CarDTO> retrieveCarsForTheClientById(Long id);

    List<CarDTO> retrieveCarsForTheClient();

    CarDTO retrieveCarById(Long id);

    CarDTO createCar(CarDTO body);

    CarDTO updateCarById(Long id, CarDTO body);

    void deleteCarById(Long id);
    FileResponse getCarImageByCarId(Long id);

    FileResponse uploadCarImageByCarId(Long id, MultipartFile file);

    FileResponse deleteCarImageById(Long id);

    CarDTO createCarByClientId(Long id, CarDTO body);
}
