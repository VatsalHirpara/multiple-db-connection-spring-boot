package com.nagarro.multipledbpoc.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nagarro.multipledbpoc.model.user.User;
import com.nagarro.multipledbpoc.repository.user.UserRepository;
import com.nagarro.multipledbpoc.service.UserService;

@Service
public class UserServiceImpl implements UserService  {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	@Override
	@Transactional
	public User save(User user) {
		return userRepository.save(user);
	}
}
