package com.collaboration.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author ebinezer
 *
 */
@Entity
public class FourmRequest {
	
@Id
@GeneratedValue(strategy=GenerationType.SEQUENCE)
private Integer requestId;
private Integer userId;
private Integer fourmId;
private String status;
public Integer getRequestId() {
	return requestId;
}
public void setRequestId(Integer requestId) {
	this.requestId = requestId;
}
public Integer getUserId() {
	return userId;
}
public void setUserId(Integer userId) {
	this.userId = userId;
}
public Integer getFourmId() {
	return fourmId;
}
public void setFourmId(Integer fourmId) {
	this.fourmId = fourmId;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}



}
