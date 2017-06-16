package com.collaboration.project.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class FourmPost {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer fourmPostId;
	@Lob
	private String post;
	@ManyToOne(fetch=FetchType.EAGER)
	private Fourm fourm;
	@OneToOne
	private Users users;
	public Integer getFourmPostId() {
		return fourmPostId;
	}
	public void setFourmPostId(Integer fourmPostId) {
		this.fourmPostId = fourmPostId;
	}
	public String getPost() {
		return post;
	}
	public void setPost(String post) {
		this.post = post;
	}
	public Fourm getFourm() {
		return fourm;
	}
	public void setFourm(Fourm fourm) {
		this.fourm = fourm;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	
}
