package com.nagarro.multipledbpoc;

import java.awt.print.Book;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.multipledbpoc.model.car.Car;
import com.nagarro.multipledbpoc.model.user.User;
import com.nagarro.multipledbpoc.repository.car.CarRepository;
import com.nagarro.multipledbpoc.repository.user.UserRepository;

@SpringBootApplication
@RestController
public class MultipleDbPocApplication {

	@Autowired
	UserRepository userRepository;

	@Autowired
	CarRepository carRepository;

	@PostConstruct
	public void addData() {
		userRepository.saveAll(Stream.of(new User(1, "vatsal", "Rajkot"), new User(2, "vinay", "Gurugram"))
				.collect(Collectors.toList()));
		
		carRepository.saveAll(Stream.of(new Car(1, "Mruti Swift", "black"), new Car(2, "Mruti baleno", "white"))
				.collect(Collectors.toList()));
	}
	
	
	@GetMapping("/users")
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@GetMapping("/cars")
	public List<Car> getBooks() {
		return carRepository.findAll();
	}
    
	public static void main(String[] args) {
		SpringApplication.run(MultipleDbPocApplication.class, args);
	}

}
