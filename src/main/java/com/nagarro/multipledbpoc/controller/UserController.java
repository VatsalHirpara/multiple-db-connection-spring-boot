package com.nagarro.multipledbpoc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.multipledbpoc.model.user.User;
import com.nagarro.multipledbpoc.repository.user.UserRepository;
import com.nagarro.multipledbpoc.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/users")
	public List<User> getUsers() {
		return userService.getUsers();
	}
	
	@PostMapping("/users")
	public User saveUser(@RequestBody User user ) {
		return userService.save(user);
	}
}
