package com.collaboration.project.dao.impl;

import java.util.List;



import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.collaboration.project.dao.UsersDao;
import com.collaboration.project.model.Users;

@Repository("usersDao")
@Transactional
public class UsersDaoImpl implements UsersDao{
	
@Autowired
SessionFactory sessionFactory;

@Override
public boolean createUser(Users user) {
	try {
		sessionFactory.getCurrentSession().save(user);
		return true;
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println(e);
		return false;
	}
}

@Override
public boolean updateUser(Users user) {
	try {
		sessionFactory.getCurrentSession().update(user);
		return true;
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println(e);
		return false;
	}
}

@Override
public boolean deleteUser(Users user) {
	try {
		sessionFactory.getCurrentSession().delete(user);;
		return true;
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println(e);
		return false;
	}
}

@Override
public Users getUser(Integer userId) {
	try {
		return sessionFactory.getCurrentSession().createQuery("From Users where userId=:id",Users.class).setParameter("id", userId).getSingleResult();
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println(e);
		return null;
	}
}

@Override
public List<Users> getAll() {
	try {
		return sessionFactory.getCurrentSession().createQuery("From Users",Users.class).getResultList();
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println("helllo"+e);
		return null;
	}
}

@Override
public boolean login(Users users) {
	// TODO Auto-generated method stub
	try{
		System.out.println("user mail in login method of usersDao"+users.getEmail());
	Users user=	sessionFactory.getCurrentSession().createQuery("From Users where email=:email and password=:password",Users.class)
										.setParameter("email", users.getEmail())
										.setParameter("password", users.getPassword())
										.getSingleResult();
	if(user!=null)
		return true;
	else
		return false;
	}catch (Exception e) {
		// TODO: handle exception
		System.out.println(e);
		return false;
	}
	
}

@Override
public Users getUserByEmail(String email) {
	// TODO Auto-generated method stub
	try{
		System.out.println(" email in get user by mail method"+email);
	return	sessionFactory.getCurrentSession().createQuery("From Users where email=:email",Users.class).setParameter("email", email).getSingleResult();
	}catch(Exception e)
	{
		System.out.println(e);
		return null;
	}
	
}

@Override
public boolean verified(Users user) {
	// TODO Auto-generated method stub
	 try {
		sessionFactory.getCurrentSession().update(user);
		return true;
	} catch (Exception e) {
		// TODO: handle exception
		System.out.println(e);
		return false;
	}
	
}


}
