package com.nagarro.multipledbpoc.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nagarro.multipledbpoc.model.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
