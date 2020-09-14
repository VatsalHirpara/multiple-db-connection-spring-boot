package com.nagarro.multipledbpoc.repository.vehicle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.multipledbpoc.domain.vehicle.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Integer>{
}
