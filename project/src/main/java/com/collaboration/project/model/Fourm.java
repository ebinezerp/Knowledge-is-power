package com.collaboration.project.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Fourm {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer fourmId;
	private String fourmName;
	@Lob
	private String fourmDescription;
	@OneToMany(mappedBy="fourm",fetch=FetchType.EAGER)
	private List<FourmPost> fourmPost;
	@OneToMany(mappedBy="fourm")
	private List<Users> users;

	public Integer getFourmId() {
		return fourmId;
	}

	public void setFourmId(Integer fourmId) {
		this.fourmId = fourmId;
	}

	public String getFourmName() {
		return fourmName;
	}

	public void setFourmName(String fourmName) {
		this.fourmName = fourmName;
	}

	public String getFourmDescription() {
		return fourmDescription;
	}

	public void setFourmDescription(String fourmDescription) {
		this.fourmDescription = fourmDescription;
	}

	public List<FourmPost> getFourmPost() {
		return fourmPost;
	}

	public void setFourmPost(List<FourmPost> fourmPost) {
		this.fourmPost = fourmPost;
	}

	

}
