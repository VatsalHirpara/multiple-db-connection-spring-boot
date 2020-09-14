package com.nagarro.multipledbpoc.service;

import java.util.List;

import com.nagarro.multipledbpoc.domain.vehicle.Car;

public interface CarService {

	List<Car> getCars();

	Car save(Car car);

}