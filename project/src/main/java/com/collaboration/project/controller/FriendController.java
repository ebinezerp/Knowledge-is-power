package com.collaboration.project.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.mail.search.IntegerComparisonTerm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.collaboration.project.dao.FriendsDao;
import com.collaboration.project.dao.UsersDao;
import com.collaboration.project.model.Friends;
import com.collaboration.project.model.Users;

@RestController
public class FriendController {
	
	@Autowired
	FriendsDao friendsDao;
	
	@Autowired
	UsersDao usersDao;
	
     @PostMapping("/addfriend")
     public ResponseEntity<List<Users>> addFriend(@RequestBody Friends friends)
     {
    	  System.out.println("firend id :::::::::::"+friends.getFriendId());
    	   friends.setStatus("pending");
    	   friendsDao.addFriend(friends);
    	   List users= friendsDao.suggestFriends(friends.getUserId());
    	     
      	 return new ResponseEntity<List<Users>>(users, HttpStatus.OK);
    	   
     }
     
     
     @RequestMapping("/updatefriend/{fid}/{uid}")
     public ResponseEntity<List<Friends>> update(@RequestBody String status,@PathVariable("fid") Integer fid,@PathVariable("uid") Integer uid )
     {
    	 Friends friend=friendsDao.getFriend(fid, uid);
    	 friend.setStatus("approved");
    	 friendsDao.updateFriend(friend);
    	 List<Friends> friends=friendsDao.getFriends(uid);
    	 return new ResponseEntity<List<Friends>>(friends,HttpStatus.ACCEPTED);
     }
     
     
     @PostMapping("/getfriends")
     public ResponseEntity<Collection<Users>> getFriends(@RequestBody Users user)
     {
    	 System.out.println(user.getUserId());
    	List<Friends> friends= friendsDao.getFriends(user.getUserId());
    	Set<Users> friendList=new HashSet();
    	for(Friends f:friends)
    	{
    		Users fuser=usersDao.getUser(f.getFriendId());
    		friendList.add(fuser);
    	}
    	for(Friends f:friends)
    	{
    		Users fuser=usersDao.getUser(f.getUserId());
    		friendList.add(fuser);
    	}
    	 System.out.println("firends list"+friendList);
    	 
    	return new ResponseEntity<Collection<Users>>(friendList, HttpStatus.OK) ;
     }
     
     
     @GetMapping("/suggestedFriends/{id}")
     public ResponseEntity<List<Users>> suggestedFriends(@PathVariable("id") Integer id)
     {
    	 System.out.println("user Id for suggestedFriends:::::"+id);
    	List<Users> users= friendsDao.suggestFriends(id);
     
    	 return new ResponseEntity<List<Users>>(users, HttpStatus.OK);
     }
     
     @PostMapping("/getFriendrequests")
     public ResponseEntity<List<Users>> getFriendsRequest(@RequestBody Integer userId)
     {
    	 System.out.println("getFriendsrequest");
    	 System.out.println("sizeoflistfriends"+friendsDao.getPendingReqs(userId).size());
    	 List<Users> users=new ArrayList<>();
    	 int i=0;
    	 for(Friends friend:friendsDao.getPendingReqs(userId))
    	 {
    		 System.out.println(i+"\n");
    		 i++;
    		users.add(usersDao.getUser(friend.getUserId()));
    	 }
    	 return new ResponseEntity<List<Users>>(users,HttpStatus.OK);
     }

@PostMapping("/updateStatus/{friendId}/{userId}")
public ResponseEntity<List<Friends>> updateStatus(@RequestBody String status,@PathVariable("friendId") Integer friendId,@PathVariable("userId")Integer userId)
{
	 System.out.println(friendId+"    "+userId);
	  Friends frnd=   friendsDao.getFriend(friendId, userId);
	  System.out.println("thid is friend object  "+frnd);
	  frnd.setStatus("accepted");
	  friendsDao.updateFriend(frnd);
	  
	  
	 return new ResponseEntity<List<Friends>>(friendsDao.getFriends(userId), HttpStatus.OK);
}
}
