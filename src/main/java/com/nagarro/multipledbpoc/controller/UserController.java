package com.nagarro.multipledbpoc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.multipledbpoc.model.user.User;
import com.nagarro.multipledbpoc.repository.user.UserRepository;

@RestController
public class UserController {

	@Autowired
	UserRepository userRepository;

	@GetMapping("/users")
	public List<User> getUsers() {
		return userRepository.findAll();
	}
	
	@PostMapping("/users")
	public User saveCar(@RequestBody User user ) {
		return userRepository.save(user);
	}
}
