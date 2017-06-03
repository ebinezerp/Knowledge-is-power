package com.collaboration.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.collaboration.project.dao.BlogCommentsDao;
import com.collaboration.project.model.BlogComments;

@RestController
public class CommentController {

	@Autowired
	BlogCommentsDao blogCommentsDao;
	
	@PostMapping("/postcomment")
	public ResponseEntity<List<BlogComments>> postComment(@RequestBody BlogComments blogComment)
	{
		blogCommentsDao.postComment(blogComment);
		List<BlogComments> list=blogCommentsDao.allComments(blogComment.getBlog().getBlogId());
		
		return new ResponseEntity<List<BlogComments>>(list, HttpStatus.OK);
	}
	
	@GetMapping("/allcomments/{id}")
	public ResponseEntity<List<BlogComments>> allComments(@PathVariable Integer id)
	{
		
		return new ResponseEntity<List<BlogComments>>(blogCommentsDao.allComments(id), HttpStatus.OK);
	}
}
