package com.collaboration.project.dao.impl;

import java.util.List;



import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.collaboration.project.dao.JobDao;
import com.collaboration.project.model.Job;
@Repository("jobDao")
@Transactional
public class JobDaoImpl implements JobDao {
	@Autowired
	SessionFactory sessionFactory;

	@Override
	public boolean postJob(Job job) {
		try {
			sessionFactory.getCurrentSession().save(job);
			return true;
		} catch (Exception e) {
			// TODO: handle exceptio
			System.out.println(e);
			return false;
		}
	}

	@Override
	public boolean updateJob(Job job) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().update(job);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}
	}

	@Override
	public boolean deleteJob(Job job) {
		// TODO Auto-generated method stub
		try {
			sessionFactory.getCurrentSession().delete(job);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}
	}

	@Override
	public Job getJob(Integer jobId) {
		// TODO Auto-generated method stub
		try {
			return sessionFactory.getCurrentSession().createQuery("From Job where jid=:jobid",Job.class).setParameter("jobid", jobId).getSingleResult();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}
	}

	@Override
	public List<Job> getAllJobs() {
		// TODO Auto-generated method stub
		try {
		return	sessionFactory.getCurrentSession().createQuery("From Job",Job.class).getResultList();
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}
	}

}
