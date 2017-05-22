package com.collaboration.project.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.collaboration.project.dao.BlogDao;
import com.collaboration.project.model.Blog;
@Repository("blogDao")
@Transactional
public class BlogDaoImpl implements BlogDao {
	@Autowired
    SessionFactory sessionFactory;
	@Override
	public boolean saveBlog(Blog blog) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().save(blog);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}
		
	}

	@Override
	public boolean updateBlog(Blog blog) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().update(blog);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}
	}

	@Override
	public Blog getBlog(Integer id) {
		try {
			return sessionFactory.getCurrentSession().createQuery("from Blog where blogId=:id",Blog.class).setParameter("id", id).getSingleResult();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}
	}

	@Override
	public List<Blog> getAll() {
		// TODO Auto-generated method stub
		try {
		  return sessionFactory.getCurrentSession().createQuery("from Blog",Blog.class).getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}
		
	}

}
