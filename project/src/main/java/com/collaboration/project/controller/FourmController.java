package com.collaboration.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.collaboration.project.dao.FourmDao;
import com.collaboration.project.model.Fourm;

@RestController
public class FourmController {
	
	@Autowired
	FourmDao fourmDao;
	
	@PostMapping("/postFourm")
	public ResponseEntity<List<Fourm>> postFourm(@RequestBody Fourm fourm)
	{
		fourmDao.postFourm(fourm);
		
		return new ResponseEntity<List<Fourm>>(fourmDao.getAllFourms(),HttpStatus.OK);
	}

	
	@GetMapping("/allFourms")
	public ResponseEntity<List<Fourm>> getAllFourms()
	{
		return new ResponseEntity<List<Fourm>>(fourmDao.getAllFourms(),HttpStatus.OK);
	}
}
