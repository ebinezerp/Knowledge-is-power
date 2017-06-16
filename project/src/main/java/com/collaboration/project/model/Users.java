package com.collaboration.project.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Users implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer userId;

	@NotBlank(message = "Should not be blank")
	@Size(min = 3, max = 15)
	private String firstName;
	@NotNull(message = "Should not be null")
	@Size(min = 3, max = 15)
	private String lastName;
	@Email(message = "enter valid email address")
	private String email;
	@NotBlank(message = "Mobile is vaid")
	@Size(min = 10, max = 10, message = "enter valid mobile number")
	private String mobile;
	@NotBlank
	@Size()
	private String role;

	@NotBlank
	@Size(min = 4, max = 8)
	private String userName;
	@NotBlank
	@Size(min = 5, max = 8)
	private String password;
	@Transient
	private String cpassword;

	private boolean verified;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="users",cascade=CascadeType.ALL)
	private List<Blog> blogs;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="friend",fetch=FetchType.EAGER)
	private List<Friends> friends;
	@OneToMany()
	private List<Fourm> fourm;

	public List<Friends> getFriends() {
		return friends;
	}

	public void setFriends(List<Friends> friends) {
		this.friends = friends;
	}

	public List<Blog> getBlogs() {
		return blogs;
	}

	public void setBlogs(List<Blog> blogs) {
		this.blogs = blogs;
	}

	public boolean isVerified() {
		return verified;
	}

	public void setVerified(boolean verified) {
		this.verified = verified;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCpassword() {
		return cpassword;
	}

	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
