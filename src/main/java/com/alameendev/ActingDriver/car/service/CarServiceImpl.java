package com.alameendev.ActingDriver.car.service;

import com.alameendev.ActingDriver.car.dto.CarDTO;
import com.alameendev.ActingDriver.car.entity.Car;
import com.alameendev.ActingDriver.car.repository.CarRepository;
import com.alameendev.ActingDriver.client.entity.Client;
import com.alameendev.ActingDriver.client.service.ClientService;
import com.alameendev.ActingDriver.exceptions.car.CarNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService{

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final ClientService clientService;

    @Override
    public List<CarDTO> retrieveAllCars() {
        List<Car> cars = carRepository.findAll();
        return cars.stream().map(car->modelMapper.map(car,CarDTO.class)).collect(Collectors.toList());
    }

    @Override
    public CarDTO saveCar(Car car) {
        carRepository.save(car);
        return modelMapper.map(car,CarDTO.class);
    }

    @Override
    public List<CarDTO> retrieveCarsForTheClientById(Long id) {
        List<Car> cars = carRepository.findByClientClientId(id);
        return cars.stream().map(car->modelMapper.map(car,CarDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<CarDTO> retrieveCarsForTheClient() {
        Client client = clientService.getClient();
        List<Car> cars = carRepository.findByClient(client);
        return cars.stream().map(car -> modelMapper.map(car,CarDTO.class)).collect(Collectors.toList());
    }

    @Override
    public CarDTO retrieveCarById(Long id) {
        Car car = carRepository.findById(id).orElseThrow(()->new CarNotFoundException(id));
        return modelMapper.map(car,CarDTO.class);
    }

    @Override
    public CarDTO createCar(CarDTO body) {
        Client client = clientService.getClient();
        Car car = Car.builder().client(client).carNumber(body.getCarNumber()).model(body.getModel()).build();
        carRepository.save(car);
        return modelMapper.map(car,CarDTO.class);
    }

    @Override
    public CarDTO updateCarById(Long id,CarDTO body) {
        Car car = carRepository.findById(body.getCarId()).orElseThrow(()-> new CarNotFoundException(body.getCarId()));
        car.setCarNumber(body.getCarNumber());
        car.setModel(body.getModel());
        return modelMapper.map(car,CarDTO.class);
    }

    @Override
    public void deleteCarById(Long id) {
        carRepository.deleteById(id);
    }
}
