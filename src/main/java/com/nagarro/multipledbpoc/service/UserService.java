package com.nagarro.multipledbpoc.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.nagarro.multipledbpoc.model.user.User;

public interface UserService {

	List<User> getUsers();

	User save(User user);

}