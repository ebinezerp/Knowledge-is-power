package com.collaboration.project.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.collaboration.project.dao.FriendsDao;
import com.collaboration.project.model.Friends;
@Repository("friendsDao")
@Transactional
public class FriendsDaoImpl implements FriendsDao{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public boolean addFriend(Friends friendShip) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().save(friendShip);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}
	}

	@Override
	public boolean updateFriend(Friends friendShip) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().update(friendShip);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}
	}

	@Override
	public List<Friends> getFriends(Integer id) {
		// TODO Auto-generated method stub
		try {
		return	sessionFactory.getCurrentSession().createQuery("from Friends where user_userid=:id",Friends.class).setParameter("id", id).getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}

}
