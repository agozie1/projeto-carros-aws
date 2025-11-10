package com.example.demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/cars")
class CarController {

    @Autowired
    private CarRepository carRepository;

    public CarController() {

    }

    @GetMapping()
    Iterable<Car> getCars(@RequestParam Optional<Long> carId) {
        return carRepository.findAll();
    }

    @GetMapping("/{id}")
    Optional<Car> getCar(@PathVariable long id) {
        return carRepository.findById(id);
    }

    @PostMapping("/api/cars")
    Car createCar(@RequestBody Car car) {
        return carRepository.save(car);
    }

    @PutMapping("/api/cars/{carId}")
    Optional<Car> updateCar(@RequestBody Car carRequest, @PathVariable long carId) {
        Optional<Car> opt = carRepository.findById(carId);
        if (opt.isPresent()) {
            if (carRequest.getId() == carId) {
                carRepository.save(carRequest);
                return opt;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                "Erro ao alterar dados do carro com id " + carId);
    }

    @DeleteMapping("/api/cars/{id}")
    void deleteCar(@PathVariable long id) {
        carRepository.deleteById(id);
    }
}