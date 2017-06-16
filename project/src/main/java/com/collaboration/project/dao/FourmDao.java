package com.collaboration.project.dao;

import java.util.List;

import com.collaboration.project.model.Fourm;

public interface FourmDao {
	
	boolean postFourm(Fourm fourm);
	
	List<Fourm> getAllFourms();
	

}
