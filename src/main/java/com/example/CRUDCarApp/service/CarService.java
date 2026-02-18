package com.example.CRUDCarApp.service;

import com.example.CRUDCarApp.dto.CarDTO;
import com.example.CRUDCarApp.model.Car;
import com.example.CRUDCarApp.repo.CarRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {

    private final CarRepo carRepo;
    private final ModelMapper modelMapper;

    public CarService(CarRepo carRepo, ModelMapper modelMapper) {
        this.carRepo = carRepo;
        this.modelMapper = modelMapper;
    }

    public CarDTO getById(Long id) { // returns CarDTO
        Car car = carRepo.findById(id).orElseThrow(()->  //  Optional contains car -> gives car object
                new RuntimeException("Car not found"));  // if car don't exist -> crash with error (stops the method completely)

        return modelMapper.map(car, CarDTO.class); // take the car entity and convert it into CarDTO, return it
    }

    public List<CarDTO> getAll() {
        return carRepo.findAll().stream().map(car -> modelMapper.map(car, CarDTO.class)).toList();
    }

    public CarDTO create(CarDTO carDTO) {
        Car car = modelMapper.map(carDTO, Car.class);
        Car savedCar = carRepo.save(car);

        return modelMapper.map(savedCar, CarDTO.class);
    }

    public CarDTO updateById(Long id, CarDTO newCarData) {
        Car oldCarData = carRepo.findById(id).orElseThrow(()-> new RuntimeException("Car not found"));

        oldCarData.setMake(newCarData.getMake());
        oldCarData.setModel(newCarData.getModel());
        oldCarData.setType(newCarData.getType());
        Car updatedCarData = carRepo.save(oldCarData);

        return modelMapper.map(updatedCarData,CarDTO.class);
    }

    public CarDTO deleteById(Long id) {
        Car carData = carRepo.findById(id).orElseThrow(()-> new RuntimeException("Car not found"));
        carRepo.delete(carData);

        return modelMapper.map(carData, CarDTO.class);
    }
}