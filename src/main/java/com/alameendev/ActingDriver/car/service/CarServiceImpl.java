package com.alameendev.ActingDriver.car.service;

import com.alameendev.ActingDriver.car.dto.CarDTO;
import com.alameendev.ActingDriver.car.entity.Car;
import com.alameendev.ActingDriver.car.repository.CarRepository;
import com.alameendev.ActingDriver.client.entity.Client;
import com.alameendev.ActingDriver.client.service.ClientService;
import com.alameendev.ActingDriver.exceptions.car.CarNotFoundException;
import com.alameendev.ActingDriver.filestorage.dto.FileResponse;
import com.alameendev.ActingDriver.filestorage.service.FileService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService{

    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final ClientService clientService;
    private final FileService fileService;

    @Value("${file.car-upload-dir}")
    private String uploadLocation;
    @Value("${file.dummyCar}")
    private String dummyCar;

    @Value("${file.dummyImage-dir}")
    private String dummyLocation;

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
        Car car = Car.builder().client(client).carImageName(dummyCar).carNumber(body.getCarNumber()).model(body.getModel()).build();
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

    @Override
    public FileResponse getCarImageByCarId(Long id) {
        Car car = carRepository.findById(id).orElseThrow(()-> new CarNotFoundException(id));
        if(!car.getCarImageName().equals(dummyCar)){
            return fileService.getFile(car.getCarImageName(),uploadLocation);
        }
        return fileService.getFile(car.getCarImageName(),dummyLocation);
    }

    @Override
    public FileResponse uploadCarImageByCarId(Long id, MultipartFile file) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Client client = clientService.getClient();
        Car car = carRepository.findById(id).orElseThrow(()-> new CarNotFoundException(id));
        LocalDateTime localDateTime = LocalDateTime.now();
        String fileName = email+ "-Car" + localDateTime.getYear() + localDateTime.getMonthValue() + localDateTime.getDayOfMonth() +
                localDateTime.getHour() + localDateTime.getMinute() + localDateTime.getSecond() + "-nano" +
                localDateTime.getNano() + "." + StringUtils.getFilenameExtension(file.getOriginalFilename());
        fileService.uploadFile(fileName,file,uploadLocation);
        String oldFile = car.getCarImageName();
        if(!oldFile.equals(dummyCar)){
            fileService.deleteFile(oldFile,uploadLocation,dummyCar);
        }
        car.setCarImageName(fileName);
        carRepository.save(car);
        return fileService.getFile(fileName,uploadLocation);
    }

    @Override
    public FileResponse deleteCarImageById(Long id) {
        Car car = carRepository.findById(id).orElseThrow(()-> new CarNotFoundException(id));
        String fileName = car.getCarImageName();
        if(!fileName.equals(dummyCar)){
            fileService.deleteFile(fileName,uploadLocation,dummyCar);
        }
        car.setCarImageName(dummyCar);
        carRepository.save(car);
        return fileService.getFile(dummyCar,dummyLocation);
    }

    @Override
    public CarDTO createCarByClientId(Long id, CarDTO body) {
        Client client = clientService.retrieveClientById(id);
        Car car = Car.builder()
                .client(client)
                .carNumber(body.getCarNumber())
                .model(body.getModel())
                .build();
        carRepository.save(car);
        return modelMapper.map(car,CarDTO.class);
    }
}
