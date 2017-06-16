package com.collaboration.project.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.collaboration.project.dao.UsersDao;
import com.collaboration.project.model.Users;

@Controller
public class UsersController {
	
	@Autowired
	UsersDao usersDao;
	@Autowired
	HttpServletRequest request;
	
	
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
	
	
	@PostMapping("/imageUpload")
	public ResponseEntity<Void> imageUpload(@RequestParam("image")MultipartFile image,@RequestParam("name")String name)
	{
		System.out.println("entered in image upload");
		 try {
			InputStream is= image.getInputStream();
			byte b[]=new byte[is.available()];
			is.read(b);
			File file=new File(request.getRealPath("//")+"//profile//");
			if(!file.exists())
			{
			    file.mkdirs();	
			}
			System.out.println(file.getAbsolutePath());
			OutputStream outputStream=new FileOutputStream(file.getAbsolutePath()+"//"+name+".jpg");

			   outputStream.write(b);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
