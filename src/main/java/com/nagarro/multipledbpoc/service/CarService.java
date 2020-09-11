package com.nagarro.multipledbpoc.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.nagarro.multipledbpoc.model.car.Car;

public interface CarService {

	List<Car> getCars();

	Car save(Car car);

}