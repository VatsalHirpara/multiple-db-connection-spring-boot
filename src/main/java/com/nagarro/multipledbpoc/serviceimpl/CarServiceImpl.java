package com.nagarro.multipledbpoc.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nagarro.multipledbpoc.domain.vehicle.Car;
import com.nagarro.multipledbpoc.repository.vehicle.CarRepository;
import com.nagarro.multipledbpoc.service.CarService;

@Service
public class CarServiceImpl implements CarService {

	@Autowired
	CarRepository carRepository;

	@Override
	public List<Car> getCars() {
		return carRepository.findAll();
	}

	@Override
	@Transactional
	public Car save(Car car) {
		return carRepository.save(car);
	}
}
