package com.collaboration.project.dao;

import java.util.List;


import com.collaboration.project.model.Friends;
import com.collaboration.project.model.Users;

public interface FriendsDao {

	boolean addFriend(Friends friends);
	boolean updateFriend(Friends friends);
	List<Friends> getFriends(Integer id); 
	List<Users> suggestFriends(Integer id);
	public List<Friends> getPendingReqs(Integer userId);
	
}
