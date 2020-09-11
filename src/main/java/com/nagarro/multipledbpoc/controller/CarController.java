package com.nagarro.multipledbpoc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.multipledbpoc.model.car.Car;
import com.nagarro.multipledbpoc.repository.car.CarRepository;
import com.nagarro.multipledbpoc.service.CarService;

@RestController
public class CarController {

	@Autowired
	CarService carService;
	
	@GetMapping("/cars")
	public List<Car> getBooks() {
		return carService.getCars();
	}

	@PostMapping("/cars")
	public Car saveCar(@RequestBody Car car ) {
		return carService.save(car);
	}
}