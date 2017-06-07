package com.collaboration.project.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Friends {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer fid;
	@JsonIgnore
	@OneToOne(cascade=CascadeType.ALL)
	private Users user;
	@JsonIgnore
	@ManyToOne(cascade=CascadeType.ALL)
	private Users friend;
	public Users getFriend() {
		return friend;
	}
	public void setFriend(Users friend) {
		this.friend = friend;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	private String status;
	public Integer getFid() {
		return fid;
	}
	public void setFid(Integer fid) {
		this.fid = fid;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	

}
