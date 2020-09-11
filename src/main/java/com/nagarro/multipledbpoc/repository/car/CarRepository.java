package com.nagarro.multipledbpoc.repository.car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.multipledbpoc.model.car.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer>{
}
