package com.collaboration.project.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.collaboration.project.dao.BlogCommentsDao;
import com.collaboration.project.model.BlogComments;
@Transactional
@Repository("blogCommentsDao")
public class BlogCommentsDaoImpl implements BlogCommentsDao {
	
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public boolean postComment(BlogComments blogComment) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().save(blogComment);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}
	}

	@Override
	public List<BlogComments> allComments(Integer blogId) {
		// TODO Auto-generated method stub
		try {
		return	sessionFactory.getCurrentSession().createQuery("From BlogComments where BLOG_BLOGID=:id",BlogComments.class).setParameter("id", blogId).getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}
	}

}
