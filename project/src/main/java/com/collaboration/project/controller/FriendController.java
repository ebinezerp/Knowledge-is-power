package com.collaboration.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.collaboration.project.dao.FriendsDao;
import com.collaboration.project.model.Friends;
import com.collaboration.project.model.Users;

@RestController
public class FriendController {
	
	@Autowired
	FriendsDao friendsDao;
	
     @PostMapping("/addfriend")
     public ResponseEntity<Friends> addFriend(@RequestBody Friends friends)
     {
    	   friends.setStatus("pending");
    	   friendsDao.addFriend(friends);
    	   
    	   
    	   
    	   return new ResponseEntity<Friends>(friends,HttpStatus.OK);
    	   
     }
     
     
     @RequestMapping("/updatefriend")
     public ResponseEntity<Friends> update(@RequestBody Friends friends)
     {
    	 friends.setStatus("approved");
    	 friendsDao.updateFriend(friends);
    	 return new ResponseEntity<Friends>(friends,HttpStatus.ACCEPTED);
     }
     
     
     @PostMapping("/getfriends")
     public ResponseEntity<List<Friends>> getFriends(@RequestBody Users user)
     {
    	 System.out.println(user.getUserId());
    	List<Friends> friends= friendsDao.getFriends(user.getUserId());
    	 System.out.println("firends list"+friends);
    	 
    	return new ResponseEntity<List<Friends>>(friends, HttpStatus.OK) ;
     }

}
