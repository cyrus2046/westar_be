package com.westar.demo.be.controller;

import com.westar.demo.be.model.Car;
import com.westar.demo.be.model.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/api")
class CarController {

    private final Logger log = LoggerFactory.getLogger(CarController.class);
    private CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping("/cars")
    Collection<Car> cars() {
        return carRepository.findAll();
    }

    @GetMapping("/car/{id}")
    ResponseEntity<?> getGroup(@PathVariable Long id) {
        Optional<Car> car = carRepository.findById(id);
        return car.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/car")
    ResponseEntity<Car> createCar(@Valid @RequestBody Car car) throws URISyntaxException {
        log.info("Request to create car: {}", car);
        Car result = carRepository.save(car);
        return ResponseEntity.created(new URI("/api/car/" + result.getId()))
                .body(result);
    }

    @PutMapping("/car")
    ResponseEntity<Car> updateCar(@Valid @RequestBody Car car) {
        log.info("Request to update car: {}", car);
        Car result = carRepository.save(car);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/car/{id}")
    public ResponseEntity<?> deleteGroup(@PathVariable Long id) {
        log.info("Request to delete car: {}", id);
        carRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}