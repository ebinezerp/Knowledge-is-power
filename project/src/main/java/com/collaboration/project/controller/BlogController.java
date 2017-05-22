package com.collaboration.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.collaboration.project.dao.BlogDao;
import com.collaboration.project.dao.UsersDao;
import com.collaboration.project.model.Blog;
import com.collaboration.project.model.Users;

@Controller
public class BlogController {
	
	@Autowired
	BlogDao blogDao;
	
	@Autowired
	UsersDao usersDao;
	
	@RequestMapping("/blog/{id}")
	public ResponseEntity<Map<String, String>> saveBlog(@RequestBody @Valid Blog blog,BindingResult result,@PathVariable Integer id)
	{
		System.out.println("User id in verify method::::::::"+id);
		Map<String, String> map=new HashMap<String, String>();
		if(result.hasErrors())
		{
			System.out.println("validation failed");
			for(FieldError error: result.getFieldErrors()){
				
				map.put(error.getField(), error.getDefaultMessage());
				
			}
				
		return new ResponseEntity<Map<String,String>>(map,HttpStatus.PARTIAL_CONTENT);
		}
		
		 Users user=usersDao.getUser(id);
		 blog.setUsers(user);
		 
		 blogDao.saveBlog(blog);
		 
		 return new ResponseEntity<Map<String,String>>(map,HttpStatus.OK);
		  
		
	}
	
	@GetMapping("/allblogs")
	public ResponseEntity<List<Blog>> allBlogs(){
		System.out.println("entered into all blogs");
		return new ResponseEntity<List<Blog>>(blogDao.getAll(), HttpStatus.OK);
	}

}
