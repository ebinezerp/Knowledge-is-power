package com.collaboration.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.collaboration.project.dao.UsersDao;
import com.collaboration.project.model.Users;

@Controller
public class UsersController {
	
	@Autowired
	UsersDao usersDao;
	@GetMapping("/verify/{id}")
	public ResponseEntity<Void> verification(@PathVariable Integer id)
	{
		
		try{
		 Users user=usersDao.getUser(id);
		 user.setVerified(true);
		 usersDao.updateUser(user);
		 
		return new ResponseEntity<Void>(HttpStatus.OK);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return new ResponseEntity<Void>(HttpStatus.EXPECTATION_FAILED);
		}
	}
	
	
	@PostMapping("/updateUser")
	public ResponseEntity<Users> updateUser(@RequestBody Users user)
	{
		usersDao.updateUser(user);
		return new ResponseEntity<Users>(user,HttpStatus.OK);
	}
	

}
