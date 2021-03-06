package com.collaboration.project.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.collaboration.project.dao.FourmDao;
import com.collaboration.project.model.Fourm;

@Repository("fourmDao")
@Transactional
public class FourmDaoImpl implements FourmDao {
	
	@Autowired
	SessionFactory sessionFactory;

	public boolean postFourm(Fourm fourm) {
		// TODO Auto-generated method stub
		try {
		       sessionFactory.getCurrentSession().save(fourm);
		       return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}
	}

	@Override
	public List<Fourm> getAllFourms() {
		// TODO Auto-generated method stub
		try {
		return	sessionFactory.getCurrentSession().createQuery("From Fourm",Fourm.class).getResultList();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}
	}

	@Override
	public Fourm getFourm(Integer fourmId) {
		// TODO Auto-generated method stub
		try {
			return sessionFactory.getCurrentSession().createQuery("From Fourm where fourmId=:fourmid",Fourm.class)
					.setParameter("fourmid", fourmId).getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return null;
		}
	}

	@Override
	public Boolean getPermission(Integer fourmId, Integer userId) {
		// TODO Auto-generated method stub
		try {
		  Fourm f=	(Fourm)sessionFactory.getCurrentSession().createSQLQuery("select * from fourm where fourmId in (select fourmId from fourmrequest where fourmId=? and userId=? and status=?)")
			.addEntity(Fourm.class)
			.setInteger(0, fourmId)
			.setInteger(1, userId)
			.setString(2, "accepted")
			.getSingleResult();
		  if(f!=null)
		  {
			  return true;
		  }
		  else
		  {
			  return false;
		  }
			
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e);
			return false;
		}
	}

}
