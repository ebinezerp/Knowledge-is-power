package com.collaboration.project.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.collaboration.project.model.Users;
@Component
public interface UsersDao {
	
	boolean createUser(Users user);
	boolean updateUser(Users user);
	boolean deleteUser(Users user);
	Users getUser(Integer userId);
    Users getUserByEmail(String email);
	List<Users> getAll();	
	boolean login(Users users);
	boolean verified(Users user);
}
