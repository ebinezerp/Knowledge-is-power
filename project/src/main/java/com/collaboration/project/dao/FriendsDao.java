package com.collaboration.project.dao;

import java.util.List;

import com.collaboration.project.model.Friends;

public interface FriendsDao {

	boolean addFriend(Friends friends);
	boolean updateFriend(Friends friends);
	List<Friends> getFriends(Integer id); 
	
}
