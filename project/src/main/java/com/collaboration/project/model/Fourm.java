package com.collaboration.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Fourm
{
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Integer fourmId;
	private String fourmName;
	@Lob
	private String formDesc;
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
	public String getFormDesc() {
		return formDesc;
	}
	public void setFormDesc(String formDesc) {
		this.formDesc = formDesc;
	}
	
	
}